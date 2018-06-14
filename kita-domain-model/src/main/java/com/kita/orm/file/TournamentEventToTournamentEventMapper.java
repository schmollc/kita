package com.kita.orm.file;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.kita.Participant;
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
		target.setActive(source.isActive());

		List<Participant> allParticipant = new ArrayList<>();

		for (Participant each : target.getParticipants()) {
			allParticipant.add(each);
		}

		for (Participant each : allParticipant) {
			target.removeParticipant(each);
		}

		for (Participant each : source.getParticipants()) {
			target.addParticipant(each);
		}

	}
}