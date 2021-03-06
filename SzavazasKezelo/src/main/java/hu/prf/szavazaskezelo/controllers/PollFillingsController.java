package hu.prf.szavazaskezelo.controllers;

import hu.prf.szavazaskezelo.beans.PollFillingsFacade;
import hu.prf.szavazaskezelo.controllers.util.JsfUtil;
import hu.prf.szavazaskezelo.controllers.util.PaginationHelper;
import hu.prf.szavazaskezelo.entitites.PollFillings;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name = "pollFillingsController")
@RequestScoped
public class PollFillingsController implements Serializable {

    private PollFillings current;
    private DataModel items = null;
    @EJB
    private hu.prf.szavazaskezelo.beans.PollFillingsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
       private PieChartModel pieModel;
    
    @ManagedProperty("#{param.poll_answer_id}")
    private String poll_answer_id;
    
    @ManagedProperty("#{param.poll_question_id}")
    private String poll_question_id;

    public PollFillingsController() {
        
    }

    public PollFillings getSelected() {
        if (current == null) {
            current = new PollFillings();
            selectedItemIndex = -1;
        }
        return current;
    }
    
    
    public String getpoll_answer_id(){
        return poll_answer_id;
    }
    
    public void setpoll_answer_id(String poll_answer_id){
        this.poll_answer_id = poll_answer_id;
    }
    
    public String getpoll_question_id(){
        return poll_question_id;
    }
    
    public void setpoll_question_id(String poll_question_id){
        this.poll_question_id = poll_question_id;
    }

    private PollFillingsFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "header_poll_filling_navigation";
    }

    public String prepareView() {
        current = (PollFillings) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        
        createPieModel();
        return "View";
    }

    public String prepareCreate() {
        current = new PollFillings();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PollFillingsCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (PollFillings) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PollFillingsUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (PollFillings) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PollFillingsDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }
    
    
    private void createPieModel() {
        pieModel = new PieChartModel();
        
       
        
        pieModel.set(current.getPollAnswersId().getAnswer(), current.getPollQuestionId().getPollAnswersSet().size());
        pieModel.set("Brand 2", 0);
        pieModel.set("Brand 3", 0);
        pieModel.set("Brand 4", 0);

    }
    
     public PieChartModel getPieModel() {
        return pieModel;
    }

    @FacesConverter(forClass = PollFillings.class)
    public static class PollFillingsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PollFillingsController controller = (PollFillingsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pollFillingsController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PollFillings) {
                PollFillings o = (PollFillings) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + PollFillings.class.getName());
            }
        }

    }

}
