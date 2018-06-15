package com.kita.web.pagebean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.kita.Team;
import com.kita.TournamentEvent;
import com.kita.attributes.Teamname;
import com.kita.web.bridge.TournamentEventBridge;
import com.kita.web.bridge.TournamentEventBridgeDecorator;

/**
 * @since 14.06.2018
 *
 */
@ManagedBean
@SessionScoped
public class TournamentPageBean implements Serializable {
	private static final long serialVersionUID = -5267117640909056783L;
	private Team selectedTeam = null;

	private List<Team> teams = new ArrayList<>();

	private TournamentEventBridge tournamentEventBridge;

	public TournamentPageBean() {
		tournamentEventBridge = TournamentEventBridgeDecorator.newInstance();
	}

	private TournamentEventBridge getTournamentEventBridge() {
		return tournamentEventBridge;
	}

	public String getActiveTournamentEventLabel() {
		Optional<TournamentEvent> tournamentEvent = getTournamentEventBridge().getActive();
		if (tournamentEvent.isPresent()) {
			return tournamentEvent.get().toString();
		}
		return "";
	}

	public void start(@SuppressWarnings("unused") ActionEvent actionEvent) {
		// Shuffle und setzen der Teams
	}

	void showMessage(Severity severity, String summary, String textMessage) {
		FacesMessage message = new FacesMessage(severity, summary, textMessage);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public int sortByName(Teamname name1, Teamname name2) {
		return Teamname.sortByName(name1, name2);
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> aTeams) {
		teams = aTeams;
	}

	public Team getSelectedTeam() {
		return selectedTeam;
	}

	public void setSelectedTeam(Team aSelectedTeam) {
		selectedTeam = aSelectedTeam;
	}
}