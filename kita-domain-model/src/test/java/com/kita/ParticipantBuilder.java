package com.kita;

import java.util.UUID;

import com.kita.attributes.Forename;
import com.kita.attributes.Surename;

/**
 * @since   11.06.2018
 *
 */
public class ParticipantBuilder {

	private Surename surename = Surename.newInstance("Surename");
	private Forename forename = Forename.newInstance("Forename");
	private UUID uuidPerson = UUID.randomUUID();

	public ParticipantBuilder withSurename(Surename aSurename) {
		surename = aSurename;
		return this;
	}

	public ParticipantBuilder withSurename(String aSurename) {
		surename = Surename.newInstance(aSurename);
		return this;
	}

	public ParticipantBuilder withForename(Forename aForename) {
		forename = aForename;
		return this;
	}

	public ParticipantBuilder withForename(String aForename) {
		forename = Forename.newInstance(aForename);
		return this;
	}

	public ParticipantBuilder withUUIDPerson(UUID anUUID) {
		uuidPerson = anUUID;
		return this;
	}

	public Participant build() {
		Participant participant = Participant.newInstance(forename, surename, uuidPerson);
		return participant;
	}
}