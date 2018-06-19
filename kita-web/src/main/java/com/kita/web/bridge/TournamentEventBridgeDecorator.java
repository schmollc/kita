package com.kita.web.bridge;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.kita.Participant;
import com.kita.Team;
import com.kita.TournamentEvent;
import com.kita.services.TournamentEventService;

/**
 * @since 14.05.2018
 *
 */
public class TournamentEventBridgeDecorator implements Serializable, TournamentEventBridge {
	private static final long serialVersionUID = 1436811809418403317L;

	// FCoI
	private TournamentEventService tournamentEventService = TournamentEventService.newInstance();

	private TournamentEventBridgeDecorator() {

	}

	public static TournamentEventBridge newInstance() {
		return new TournamentEventBridgeDecorator();
	}

	private TournamentEventService getTournamentEventService() {
		return tournamentEventService;
	}

	@Override
	public List<TournamentEvent> all() {
		return getTournamentEventService().getAll();
	}

	@Override
	public void set(TournamentEvent aTournamentEvent) {
		getTournamentEventService().set(aTournamentEvent);
	}

	@Override
	public TournamentEvent get(UUID anUuid) {
		return getTournamentEventService().get(anUuid);
	}

	@Override
	public Optional<TournamentEvent> getActive() {
		for (TournamentEvent each : all()) {
			if (each.isActive()) {
				return Optional.of(each);
			}
		}
		return Optional.empty();
	}

	@Override
	public void setActive(TournamentEvent aTournamentEvent) {
		deactivedLastTournament();
		activateActualTournament(aTournamentEvent);
	}

	private void deactivedLastTournament() {
		Optional<TournamentEvent> actualActiveTournamentEvent = getActive();
		if (actualActiveTournamentEvent.isPresent()) {
			TournamentEvent tournamentEvent = actualActiveTournamentEvent.get();
			tournamentEvent.setActive(false);
			set(tournamentEvent);
		}
	}

	private void activateActualTournament(TournamentEvent aTournamentEvent) {
		aTournamentEvent.setActive(true);
		set(aTournamentEvent);
	}

	@Override
	public List<Team> getTeams(List<Participant> someParticipants) {
		return getTournamentEventService().getTeams(someParticipants);
	}

	@Override
	public void startActiveTournament() {
		TournamentEvent tournamentEvent = getActive().get();
		tournamentEvent.start();
		set(tournamentEvent);
	}
}