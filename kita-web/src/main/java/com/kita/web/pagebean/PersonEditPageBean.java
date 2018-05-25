package com.kita.web.pagebean;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import com.kita.Person;
import com.kita.attributes.Email;
import com.kita.attributes.Forename;
import com.kita.attributes.Kampfname;
import com.kita.attributes.Surename;
import com.kita.web.bridge.PersonBridge;
import com.kita.web.bridge.PersonBridgeImpl;

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
		personBridge = new PersonBridgeImpl();
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
		persistPerson();
		closeDialog();
	}

	void persistPerson() {
		getBridge().persistPerson(workingPerson);
	}

	void closeDialog() {
		RequestContext.getCurrentInstance().closeDialog(workingPerson);
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

	public Kampfname getKampfname() {
		return workingPerson.getKampfname();
	}

	public void setKampfname(Kampfname aKampfname) {
		workingPerson.setKampfname(aKampfname);
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