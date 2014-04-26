/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.prf.szavazaskezelo.beans;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Ano4Ever
 */
public class DestroyButtonBean {
    public void destroyWorld(ActionEvent actionEvent){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rendszerhiba",  "Próbáld újra később.");
		
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}                    