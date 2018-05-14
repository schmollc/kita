package com.kita.orm.memory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.kita.TournamentEvent;

/**
 * https://de.wikibooks.org/wiki/Muster:_Java:_Singleton
 *
 * @since 14.05.2018
 *
 */
public class MemorySingleton {

	private static final String TOURNAMENT_EVENT = "TournamentEvent";

	private Map<String, Map> bigData = new HashMap<>();

	private static final class InstanceHolder {
		static final MemorySingleton INSTANCE = new MemorySingleton();
	}

	private MemorySingleton() {
		Map<UUID, TournamentEvent> tournamentEvents = new HashMap<UUID, TournamentEvent>();
		bigData.put(TOURNAMENT_EVENT, tournamentEvents);
	}

	public static MemorySingleton getInstance() {
		return InstanceHolder.INSTANCE;
	}

	public Map<UUID, TournamentEvent> getTournamentEvents() {
		return bigData.get(TOURNAMENT_EVENT);
	}
}