package com.kita.orm;

import java.util.List;

import com.kita.TournamentEvent;

/**
 * @since 14.05.2018
 *
 */
public interface TournamentEventGateway {
	List<TournamentEvent> getAll();

	void set(TournamentEvent relayEvent);

}
