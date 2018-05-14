package com.kita.orm.file;

import java.util.List;

import com.kita.TournamentEvent;
import com.kita.orm.TournamentEventGateway;

/**
 * @since 14.05.2018
 *
 */
public class TournamentEventGatewayFile implements TournamentEventGateway {

	private TournamentEventToTournamentEventMapper tournamentEventToTournamentEventMapper = TournamentEventToTournamentEventMapper.newInstance();

	private TournamentEventGatewayFile() {
	}

	public static TournamentEventGateway newInstance() {
		return new TournamentEventGatewayFile();
	}

	private TournamentEventGatewayFile(String aFilename) {
		FileSingleton.getInstance().setFileName(aFilename);
	}

	public static TournamentEventGatewayFile newInstance(String aFilename) {
		return new TournamentEventGatewayFile(aFilename);
	}

	@Override
	public List<TournamentEvent> getAll() {
		return FileSingleton.getInstance().getTournamentEvents();
	}

	@Override
	public void set(TournamentEvent updateTournamentEvent) {
		List<TournamentEvent> someTournamentEvents = getAll();

		if (someTournamentEvents.contains(updateTournamentEvent)) {
			for (TournamentEvent eachTournamentEvent : someTournamentEvents) {
				if (updateTournamentEvent.equals(eachTournamentEvent)) {
					getTournamentEventToTournamentEventMapper().mapTournamentEventToTournamentEvent(updateTournamentEvent, eachTournamentEvent);
					break;
				}
			}
		} else {
			someTournamentEvents.add(updateTournamentEvent);
		}

		FileSingleton.getInstance().setTournamentEvents(someTournamentEvents);

	}

	private TournamentEventToTournamentEventMapper getTournamentEventToTournamentEventMapper() {
		return tournamentEventToTournamentEventMapper;
	}

	void clear() {
		FileSingleton.getInstance().clear();
	}
}
