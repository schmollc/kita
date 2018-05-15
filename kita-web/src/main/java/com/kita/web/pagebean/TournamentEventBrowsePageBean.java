package com.kita.web.pagebean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
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
import com.kita.attributes.EventDay;
import com.kita.attributes.Eventname;
import com.kita.web.local.I18N;

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

	@ManagedProperty(value = "#{tournamentEventEditPageBean}")
	private TournamentEventEditPageBean tournamentEventEditPageBean;

	@PostConstruct
	public void init() {
		refreshTournamentEvents();
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
		getTournamentEventEditPageBean().openDialogForCreateRelayEvent();
	}

	public void edit(@SuppressWarnings("unused") ActionEvent actionEvent) {
		showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.NOT_IMPLEMENTD_YET);
	}

	public void onEditClosed(@SuppressWarnings("unused") SelectEvent event) {
		refreshTournamentEvents();
	}

	private void refreshTournamentEvents() {
		searchResult = new ArrayList<>();
		EventDay eventDayFirstEvent = EventDay.newInstance(LocalDate.of(2018, Month.JANUARY, 31));
		Eventname eventnameFirstEvent = Eventname.newInstance("1. Kicker Turnier");
		searchResult.add(TournamentEvent.newInstance(eventnameFirstEvent, eventDayFirstEvent));

		EventDay eventDaySecondEvent = EventDay.newInstance(LocalDate.of(2018, Month.APRIL, 16));
		Eventname eventnameSecondEvent = Eventname.newInstance("2. Kicker Turnier");
		searchResult.add(TournamentEvent.newInstance(eventnameSecondEvent, eventDaySecondEvent));

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