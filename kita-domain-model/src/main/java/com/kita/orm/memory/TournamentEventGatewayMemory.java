package com.kita.orm.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.kita.TournamentEvent;
import com.kita.orm.TournamentEventGateway;

/**
 * @since   14.05.2018
 *
 */
public class TournamentEventGatewayMemory implements TournamentEventGateway {

	private TournamentEventGatewayMemory() {

	}

	public static TournamentEventGatewayMemory newInstance() {
		return new TournamentEventGatewayMemory();
	}

	@Override
	public List<TournamentEvent> getAll() {
		List<TournamentEvent> eventsAsList = new ArrayList<>();

		eventsAsList.addAll(MemorySingleton.getInstance().getTournamentEvents().values());

		return eventsAsList;
	}

	@Override
	public void set(TournamentEvent tournamentEvent) {
		MemorySingleton.getInstance().getTournamentEvents().put(tournamentEvent.getUuid(), tournamentEvent);
	}

	@Override
	public TournamentEvent get(UUID uuid) {
		return MemorySingleton.getInstance().getTournamentEvents().get(uuid);
	}
}