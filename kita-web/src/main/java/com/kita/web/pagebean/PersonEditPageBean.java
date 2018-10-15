package com.kita.web.pagebean;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.kita.Person;
import com.kita.attributes.Email;
import com.kita.attributes.Forename;
import com.kita.attributes.Nickname;
import com.kita.attributes.Surename;
import com.kita.orm.I18N;
import com.kita.orm.Validation;
import com.kita.web.bridge.PersonBridge;
import com.kita.web.bridge.PersonBridgeDecorator;

/**
 * @since 22.05.2018
 *
 */
@ManagedBean(name = "personEditPageBean")
@SessionScoped
public class PersonEditPageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private PersonBridge personBridge;

	Person workingPerson = null;

	public PersonEditPageBean() {
		personBridge = PersonBridgeDecorator.newInstance();
	}

	public void openDialogForCreatePerson() {
		prepareNewPerson();
		openDialog();
	}

	void prepareNewPerson() {
		workingPerson = createNewPerson();
	}

	Person createNewPerson() {
		Person person = Person.newInstance();
		return person;
	}

	void openDialog() {
		Map<String, Object> options = new DialogOptionsBuilder().height(220).build();
		RequestContext.getCurrentInstance().openDialog(NavigationConstants.PERSON_PERSON_DIALOG_ID, options, null);
	}

	public void openDialogFor(UUID uuid) {
		workingPerson = getPerson(uuid);
		openDialog();
	}

	Person getPerson(UUID uuid) {
		return getBridge().get(uuid);
	}

	public void save() {
		Validation validation = persistPerson();
		if (validation.success()) {
			closeDialog();
		} else {
			showError();
		}
	}

	Validation persistPerson() {
		Validation validation = getBridge().persistPerson(workingPerson);
		return validation;
	}

	void closeDialog() {
		RequestContext.getCurrentInstance().closeDialog(workingPerson);
	}

	void showError() {
		// TODO - REL-313 - wie im ObjectConverter sollte die Nachricht aus dem ValidationResult Object kommen!
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, I18N.EMAIL_NOT_UNIQUE, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void saveAndNext() {
		persistPerson();
		prepareNewPerson();
	}

	public void cancel() {
		closeDialog();
	}

	public Forename getForename() {
		return workingPerson.getForename();
	}

	public void setForename(Forename aForename) {
		workingPerson.setForename(aForename);
	}

	public Surename getSurename() {
		return workingPerson.getSurename();
	}

	public void setSurename(Surename aSurename) {
		workingPerson.setSurename(aSurename);
	}

	public Nickname getNickname() {
		return workingPerson.getNickname();
	}

	public void setNickname(Nickname aNickname) {
		workingPerson.setNickname(aNickname);
	}

	public Email getEmail() {
		return workingPerson.getEmail();
	}

	public void setEmail(Email anEmail) {
		workingPerson.setEmail(anEmail);
	}

	private PersonBridge getBridge() {
		return personBridge;
	}
}