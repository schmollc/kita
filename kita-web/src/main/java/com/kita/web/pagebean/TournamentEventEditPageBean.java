package com.kita.web.pagebean;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.kita.TournamentEvent;
import com.kita.attributes.EventDay;
import com.kita.attributes.Eventname;
import com.kita.web.bridge.TournamentEventBridge;
import com.kita.web.bridge.TournamentEventBridgeImpl;
import com.kita.web.local.I18N;

/**
 * @since 15.03.2018
 *
 */
@ManagedBean(name = "tournamentEventEditPageBean")
@SessionScoped
public class TournamentEventEditPageBean implements Serializable {
	private static final long serialVersionUID = 453304395884163605L;

	private TournamentEventBridge tournamentEventBridge;

	TournamentEvent workingTournamentEvent = null;

	public TournamentEventEditPageBean() {
		tournamentEventBridge = new TournamentEventBridgeImpl();
	}

	public void openDialogFor(UUID uuid) {
		workingTournamentEvent = getTournamentEvent(uuid);
		openDialog();
	}

	private TournamentEvent getTournamentEvent(UUID uuid) {
		return getBridge().get(uuid);
	}

	public void openDialogForCreateRelayEvent() {
		prepareNewTournamentEvent();
		openDialog();
	}

	void prepareNewTournamentEvent() {
		workingTournamentEvent = TournamentEvent.newInstance();
	}

	void openDialog() {
		Map<String, Object> options = new DialogOptionsBuilder().width(400).height(200).build();
		RequestContext.getCurrentInstance().openDialog(NavigationConstants.TOURNAMENT_EVENT_EDIT_DIALOG_ID, options, null);
	}

	void closeDialog() {
		RequestContext.getCurrentInstance().closeDialog(null);
	}

	public void cancel() {
		closeDialog();
	}

	public void save() {
		persistRelayEvent();
		closeDialog();
	}

	void showMessageNotImplementedYet() {
		showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.NOT_IMPLEMENTD_YET);
	}

	void showMessage(Severity severityInfo, String summary, String textMessage) {
		FacesMessage message = new FacesMessage(severityInfo, summary, textMessage);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public EventDay getEventDay() {
		return workingTournamentEvent.getEventDay();
	}

	public void setEventDay(EventDay aEventDay) {
		workingTournamentEvent.setEventDay(aEventDay);
	}

	public Eventname getEventname() {
		return workingTournamentEvent.getName();
	}

	public void setEventname(Eventname anEventname) {
		workingTournamentEvent.setName(anEventname);
	}

	void persistRelayEvent() {
		getBridge().set(workingTournamentEvent);
	}

	private TournamentEventBridge getBridge() {
		return tournamentEventBridge;
	}

	public void onEditClosed(@SuppressWarnings("unused") SelectEvent event) {
		refreshGui();
	}

	private void refreshGui() {
		workingTournamentEvent = getTournamentEvent(workingTournamentEvent.getUuid());
	}
}