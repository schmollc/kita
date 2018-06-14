package com.kita;

import java.io.Serializable;
import java.util.UUID;

import com.kita.attributes.Forename;
import com.kita.attributes.Surename;

/**
 * @since 11.06.2018
 *
 */
public class Participant implements Serializable {
	private static final long serialVersionUID = -4324815928394137004L;

	private UUID uuid;
	private UUID uuidPerson;
	private Forename forename = Forename.newInstance();
	private Surename surename = Surename.newInstance();

	private Participant() {
		uuid = UUID.randomUUID();
	}

	private Participant(Forename aForename, Surename aSurename, UUID anUuidPerson) {
		uuid = UUID.randomUUID();
		uuidPerson = anUuidPerson;
		forename = aForename;
		surename = aSurename;
	}

	public static Participant newInstance() {
		return new Participant();
	}

	public static Participant newInstance(Forename aForename, Surename aSurename, UUID anUuidPerson) {
		return new Participant(aForename, aSurename, anUuidPerson);
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID anUuid) {
		uuid = anUuid;
	}

	public void setUuidPerson(UUID anUuidPerson) {
		uuidPerson = anUuidPerson;
	}

	public UUID getUuidPerson() {
		return uuidPerson;
	}

	public Forename getForename() {
		return forename;
	}

	public Surename getSurename() {
		return surename;
	}

	@Override
	public String toString() {
		return getForename() + " " + getSurename();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Participant other = (Participant) obj;
		if (uuid == null) {
			if (other.uuid != null) {
				return false;
			}
		} else if (!uuid.equals(other.uuid)) {
			return false;
		}
		return true;
	}
}
