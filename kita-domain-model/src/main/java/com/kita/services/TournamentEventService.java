package com.kita.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.kita.Participant;
import com.kita.Settings;
import com.kita.Team;
import com.kita.TournamentEvent;
import com.kita.attributes.Teamname;
import com.kita.orm.GatewayType;
import com.kita.orm.TournamentEventGateway;
import com.kita.orm.TournamentEventGatewayFactory;

/**
 * Only a simple Wrapper for the Gateway.
 *
 * @since 11.06.2018
 *
 */
public class TournamentEventService implements Serializable {
	private static final long serialVersionUID = 3854904742256165114L;

	private TournamentEventService() {

	}

	public static TournamentEventService newInstance() {
		return new TournamentEventService();
	}

	public TournamentEvent get(UUID anUuid) {
		return getGateway().get(anUuid);
	}

	public void set(TournamentEvent aTournamentEvent) {
		getGateway().set(aTournamentEvent);
	}

	public List<TournamentEvent> getAll() {
		return getGateway().getAll();
	}

	public GatewayType getGatewayType() {
		return Settings.getGatewayType();
	}

	public TournamentEventGateway getGateway() {
		return TournamentEventGatewayFactory.get(getGatewayType());
	}

	public List<Team> getTeams(List<Participant> someParticipants) {
		Collections.shuffle(someParticipants);
		List<Team> teams = createTeamsFrom(someParticipants);
		setDefaultNames(teams);
		return teams;
	}

	List<Team> createTeamsFrom(List<Participant> someParticipants) {
		List<Team> someTeams = new ArrayList<>();
		for (int i = 0; i < someParticipants.size() - 2; i = i + 2) {
			Participant first = someParticipants.get(i);
			Participant second = someParticipants.get(i + 1);
			someTeams.add(Team.newInstance(first, second));
		}
		Participant first = null;
		Participant second = null;

		if (someParticipants.size() % 2 == 0) {
			first = someParticipants.get(someParticipants.size() - 2);
			second = someParticipants.get(someParticipants.size() - 1);
		} else {
			first = someParticipants.get(someParticipants.size() - 1);
		}
		someTeams.add(Team.newInstance(first, second));
		return someTeams;
	}

	private void setDefaultNames(List<Team> someTeams) {
		for (int i = 0; i < someTeams.size(); i++) {
			Teamname name = Teamname.newInstance("Team " + (i + 1));
			Team team = someTeams.get(i);
			team.setName(name);
		}
	}
}
