package com.kita.orm.file;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.kita.TournamentEvent;

/**
 * @since 14.05.2018
 *
 */
public class BigData implements Serializable {
	private static final long serialVersionUID = -2243746937351737132L;

	private List<TournamentEvent> tournamentEvents = new ArrayList<>();

	private BigData() {
	}

	public static BigData newInstance() {
		return new BigData();
	}

	public List<TournamentEvent> getTournamentEvents() {
		return tournamentEvents;
	}

	public void setTournamentEvents(List<TournamentEvent> someTournamentEvents) {
		tournamentEvents = someTournamentEvents;
	}
}
