package com.kita.web.pagebean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import com.kita.Person;
import com.kita.attributes.Email;
import com.kita.attributes.Forename;
import com.kita.attributes.Kampfname;
import com.kita.attributes.Surename;
import com.kita.web.bridge.PersonBridge;
import com.kita.web.bridge.PersonBridgeImpl;
import com.kita.web.local.I18N;

/**
 * @since 22.05.2018
 *
 */
@ManagedBean
@SessionScoped
public class PersonBrowsePageBean implements Serializable {
	private static final long serialVersionUID = -7684007777613912395L;

	private PersonBridge personBridge = null;
	private List<Person> searchResult = new ArrayList<>();

	private List<Person> selectedPersons;

	@ManagedProperty(value = "#{personEditPageBean}")
	private PersonEditPageBean personEditPageBean;

	public PersonBrowsePageBean() {
		personBridge = new PersonBridgeImpl();
		refreshPersons();
	}

	private PersonBridge getPersonBridge() {
		return personBridge;
	}

	void refreshPersons() {
		searchResult = getPersonBridge().all();
	}

	public List<Person> getPersons() {
		return searchResult;
	}

	public int sortByForename(Forename name1, Forename name2) {
		return Forename.sortByForename(name1, name2);
	}

	public int sortBySurename(Surename name1, Surename name2) {
		return Surename.sortBySurename(name1, name2);
	}

	public int sortByKampfname(Kampfname name1, Kampfname name2) {
		return Kampfname.sortByKampfname(name1, name2);
	}

	public int sortByEmail(Email name1, Email name2) {
		return Email.sortByEmail(name1, name2);
	}

	public Integer getNumberOfResults() {
		return searchResult == null ? 0 : searchResult.size();
	}

	public boolean isRowSelectedForOneRow() {
		return getSelectedPersons() != null && getSelectedPersons().size() == 1;
	}

	public List<Person> getSelectedPersons() {
		return selectedPersons;
	}

	public void setSelectedPersons(List<Person> someSelectedPersons) {
		selectedPersons = someSelectedPersons;
	}

	public void add(@SuppressWarnings("unused") ActionEvent actionEvent) {
		getPersonEditPageBean().openDialogForCreatePerson();
	}

	public void edit(@SuppressWarnings("unused") ActionEvent actionEvent) {
		if (isRowSelectedForOneRow()) {
			UUID uuid = getSelectedPerson().getUuid();
			getPersonEditPageBean().openDialogFor(uuid);
		} else {
			showMessageErrorNoRowSelected();
		}
	}

	private Person getSelectedPerson() {
		return selectedPersons.get(0);
	}

	public void onEditClosed(@SuppressWarnings("unused") SelectEvent selectEvent) {
		refreshPersons();
	}

	public PersonEditPageBean getPersonEditPageBean() {
		return personEditPageBean;
	}

	public void setPersonEditPageBean(PersonEditPageBean aPersonEditPageBean) {
		personEditPageBean = aPersonEditPageBean;
	}

	void showMessageErrorNoRowSelected() {
		showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.SELECT_A_ROW);
	}

	void showMessage(Severity severity, String summary, String textMessage) {
		FacesMessage message = new FacesMessage(severity, summary, textMessage);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}