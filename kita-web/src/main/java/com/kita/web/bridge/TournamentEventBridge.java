package com.kita.web.bridge;

import java.util.List;
import java.util.UUID;

import com.kita.TournamentEvent;

/**
 * @since 14.05.2018
 *
 */
public interface TournamentEventBridge {
	List<TournamentEvent> all();

	void set(TournamentEvent aTournamentEvent);

	TournamentEvent get(UUID anUuid);
}
