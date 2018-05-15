package com.kita.web.pagebean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import com.kita.TournamentEvent;
import com.kita.web.bridge.TournamentEventBridge;
import com.kita.web.bridge.TournamentEventBridgeImpl;

/**
 * @since 13.05.2018
 *
 */
@ManagedBean
@SessionScoped
public class TournamentEventBrowsePageBean implements Serializable {
	private static final long serialVersionUID = -5267117640909056783L;

	private TournamentEvent selectedTournamentEvent = null;

	private List<TournamentEvent> searchResult = new ArrayList<>();

	private TournamentEventBridge tournamentEventBridge;

	@ManagedProperty(value = "#{tournamentEventEditPageBean}")
	private TournamentEventEditPageBean tournamentEventEditPageBean;

	@PostConstruct
	public void init() {
		refreshTournamentEvents();
	}

	public TournamentEventBrowsePageBean() {
		tournamentEventBridge = new TournamentEventBridgeImpl();
	}

	private TournamentEventBridge getTournamentEventBridge() {
		return tournamentEventBridge;
	}

	public List<TournamentEvent> getTournamentEvents() {
		return searchResult;
	}

	public Integer getNumberOfResults() {
		return searchResult == null ? 0 : searchResult.size();
	}

	public TournamentEvent getSelectedTournamentEvent() {
		return selectedTournamentEvent;
	}

	public void setSelectedTournamentEvent(TournamentEvent aTournamentEvent) {
		selectedTournamentEvent = aTournamentEvent;
	}

	public TournamentEventEditPageBean getTournamentEventEditPageBean() {
		return tournamentEventEditPageBean;
	}

	public void setTournamentEventEditPageBean(TournamentEventEditPageBean aTournamentEventEditPageBean) {
		tournamentEventEditPageBean = aTournamentEventEditPageBean;
	}

	public void add(@SuppressWarnings("unused") ActionEvent actionEvent) {
		getTournamentEventEditPageBean().openDialogForCreateTournamentEvent();
	}

	public void edit(@SuppressWarnings("unused") ActionEvent actionEvent) {
		TournamentEvent selectedEvent = getSelectedTournamentEvent();
		getTournamentEventEditPageBean().openDialogFor(selectedEvent.getUuid());

	}

	public void onEditClosed(@SuppressWarnings("unused") SelectEvent selectEvent) {
		refreshTournamentEvents();
	}

	private void refreshTournamentEvents() {
		searchResult = getTournamentEventBridge().all();
	}

	public void cancelEditDialog() {
		// TODO: -medium- Wieso ruft man im AddPart.. Dialog HIER eine Methode auf um dann wieder DORT ein cancel zu machen?
		//		getAddParticipantPageBean().cancel();
	}

	void showMessage(Severity severity, String summary, String textMessage) {
		FacesMessage message = new FacesMessage(severity, summary, textMessage);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}