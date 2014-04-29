package hu.prf.szavazaskezelo.controllers;

import hu.prf.szavazaskezelo.beans.PollQuestionsFacade;
import hu.prf.szavazaskezelo.beans.PollsFacade;
import hu.prf.szavazaskezelo.controllers.util.JsfUtil;
import hu.prf.szavazaskezelo.controllers.util.PaginationHelper;
import hu.prf.szavazaskezelo.entitites.PollQuestions;
import hu.prf.szavazaskezelo.entitites.Polls;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "pollQuestionsController")
@SessionScoped
public class PollQuestionsController implements Serializable {

    private PollQuestions current;
    private DataModel items = null;
    @EJB
    private hu.prf.szavazaskezelo.beans.PollQuestionsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    private String poll_question_id;
    private String poll_id;

    public PollQuestionsController() {
    }

    public PollQuestions getSelected() {
        poll_question_id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("poll_question_id");
        if (poll_question_id != null) {
            current = (PollQuestions) getFacade().find(Long.parseLong(poll_question_id));
        }

        if (current == null) {
            current = new PollQuestions();
            selectedItemIndex = -1;
        }

        return current;
    }

    private PollQuestionsFacade getFacade() {
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
        return "header_poll_question_navigation";
    }

    public String prepareView() {
        current = (PollQuestions) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new PollQuestions();
        selectedItemIndex = -1;
        return "Create";
    }

    public String preparePollQuestionCreate() {
        current = new PollQuestions();

        poll_id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("poll_id");
        if (poll_id != null) {
            current.setPollId(getFacade().findByPollId(Long.parseLong(poll_id)));

        }
        selectedItemIndex = -1;
        return "poll_question_create_navigation";
    }

    public String preparePollQuestionEdit() {
        if (poll_question_id != null) {
            current = getFacade().find(Long.parseLong(poll_question_id));
        }
        //selectedItemIndex = -1;
        return "poll_question_edit_navigation";
    }

    public String getpoll_question_id() {
        return poll_question_id;
    }

    public void setpoll_question_id(String poll_question_id) {
        this.poll_question_id = poll_question_id;
    }

    public String getpoll_id() {
        return poll_id;
    }

    public void setpoll_id(String poll_id) {
        this.poll_id = poll_id;
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PollQuestionsCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String createPartial() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PollQuestionsCreated"));
            return "poll_navigation";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (PollQuestions) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PollQuestionsUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (PollQuestions) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PollQuestionsDeleted"));
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

    @FacesConverter(forClass = PollQuestions.class)
    public static class PollQuestionsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PollQuestionsController controller = (PollQuestionsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pollQuestionsController");
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
            if (object instanceof PollQuestions) {
                PollQuestions o = (PollQuestions) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + PollQuestions.class.getName());
            }
        }

    }

}
