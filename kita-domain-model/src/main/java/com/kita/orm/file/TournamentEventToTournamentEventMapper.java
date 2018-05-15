package com.kita.orm.file;

import java.io.Serializable;

import com.kita.TournamentEvent;

/**
 * @since 14.05.2018
 *
 */
public class TournamentEventToTournamentEventMapper implements Serializable {
	private static final long serialVersionUID = -5578719393317440847L;

	private TournamentEventToTournamentEventMapper() {
		super();
	}

	public static TournamentEventToTournamentEventMapper newInstance() {
		return new TournamentEventToTournamentEventMapper();
	}

	public void mapTournamentEventToTournamentEvent(TournamentEvent source, TournamentEvent target) {
		if (source == null) {
			throw new IllegalArgumentException("[source] must not be 'null'!");
		}
		if (target == null) {
			throw new IllegalArgumentException("[target] must not be 'null'!");
		}

		target.setDay(source.getDay());
		target.setName(source.getName());
	}

}
