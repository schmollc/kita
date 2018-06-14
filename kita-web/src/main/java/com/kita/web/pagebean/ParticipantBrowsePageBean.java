package com.kita.web.pagebean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.kita.Participant;
import com.kita.Person;
import com.kita.TournamentEvent;
import com.kita.attributes.Forename;
import com.kita.attributes.Surename;
import com.kita.orm.I18N;
import com.kita.web.bridge.PersonBridge;
import com.kita.web.bridge.PersonBridgeDecorator;
import com.kita.web.bridge.TournamentEventBridge;
import com.kita.web.bridge.TournamentEventBridgeDecorator;

/**
 * @since 11.06.2018
 *
 */
@ManagedBean
@SessionScoped
public class ParticipantBrowsePageBean implements Serializable {
	private static final long serialVersionUID = -7684007777613912395L;

	private TournamentEventBridge tournamentEventBridge = null;
	private PersonBridge personBridge = null;

	private List<Participant> searchResult = new ArrayList<>();
	private List<Person> persons = new ArrayList<>();

	private List<Participant> selectedParticipants = new ArrayList<>();
	private List<Person> selectedPersons = new ArrayList<>();

	// TODO -small- Diskutieren. Sollte es "ActiveTournamentEvent" oder nur TournamentEvent heissen?
	// Spielt es an dieser Stelle eine Rolle das es sich um das aktive handelt?
	private TournamentEvent activeTournamentEvent = null;

	public ParticipantBrowsePageBean() {
		tournamentEventBridge = TournamentEventBridgeDecorator.newInstance();
		personBridge = PersonBridgeDecorator.newInstance();
	}

	@PostConstruct
	public void init() {
		setActiveTournamentEvent();
		refreshParticipants();
		refreshPersons();
	}

	private void setActiveTournamentEvent() {
		Optional<TournamentEvent> tournamentEvent = getTournamentEventBridge().getActive();
		if (tournamentEvent.isPresent()) {
			activeTournamentEvent = tournamentEvent.get();
		}
	}

	void refreshParticipants() {
		searchResult = new ArrayList<>(getActiveTournamentEvent().getParticipants());
	}

	void refreshPersons() {
		persons = new ArrayList<>(getPersonBridge().all());
	}

	private TournamentEventBridge getTournamentEventBridge() {
		return tournamentEventBridge;
	}

	private PersonBridge getPersonBridge() {
		return personBridge;
	}

	public void add(@SuppressWarnings("unused") ActionEvent actionEvent) {
		if (!isPersonRowSelected()) {
			showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.SELECT_A_PERSON);
		} else {
			for (Person each : getSelectedPersons()) {
				Participant participant = Participant.newInstance(each.getForename(), each.getSurename());
				getActiveTournamentEvent().addParticipant(participant);
				persistChange();
			}
			refreshParticipants();
			refreshPersons();
		}
	}

	private boolean isPersonRowSelected() {
		return getSelectedPersons() != null && !getSelectedPersons().isEmpty();
	}

	private TournamentEvent getActiveTournamentEvent() {
		return activeTournamentEvent;
	}

	private void persistChange() {
		getTournamentEventBridge().set(getActiveTournamentEvent());
	}

	public void remove(@SuppressWarnings("unused") ActionEvent actionEvent) {
		if (!isParticipantRowSelected()) {
			showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.SELECT_A_PARTICIPANT);
		} else {
			for (Participant each : getSelectedParticipants()) {
				getActiveTournamentEvent().removeParticipant(each);
				persistChange();
			}
			refreshParticipants();
			refreshPersons();
		}
	}

	private boolean isParticipantRowSelected() {
		return getSelectedParticipants() != null && !getSelectedParticipants().isEmpty();
	}

	public String getActiveTournamentEventLabel() {
		Optional<TournamentEvent> selectedEvent = getTournamentEventBridge().getActive();
		if (selectedEvent.isPresent()) {
			return selectedEvent.get().toString();
		}
		return "";
	}

	public void onEditClosed(@SuppressWarnings("unused") SelectEvent event) {
		refreshParticipants();
	}

	public List<Participant> getParticipants() {
		return searchResult;
	}

	public List<Participant> getSelectedParticipants() {
		return selectedParticipants;
	}

	public void setSelectedParticipants(List<Participant> someSelectedParticipants) {
		selectedParticipants = someSelectedParticipants;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public List<Person> getSelectedPersons() {
		return selectedPersons;
	}

	public void setSelectedPersons(List<Person> someSelectedPersons) {
		selectedPersons = someSelectedPersons;
	}

	public int sortByForename(Forename name1, Forename name2) {
		return Forename.sortByForename(name1, name2);
	}

	public int sortBySurename(Surename name1, Surename name2) {
		return Surename.sortBySurename(name1, name2);
	}

	public Integer getNumberOfResults() {
		return searchResult == null ? 0 : searchResult.size();
	}

	void showMessageErrorNoRowSelected() {
		showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.SELECT_A_ROW);
	}

	void showMessage(Severity severity, String summary, String textMessage) {
		FacesMessage message = new FacesMessage(severity, summary, textMessage);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	void showDialog(Severity severity, String summary, String textMessage) {
		FacesMessage message = new FacesMessage(severity, summary, textMessage);
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}
}