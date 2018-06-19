package com.kita;

import java.io.Serializable;
import java.util.UUID;

import com.kita.attributes.Teamname;

/**
 * @since 14.06.2018
 */
public class Team implements Serializable {
	private static final long serialVersionUID = -3320694948956746442L;

	private UUID uuid;
	private Participant firstParticipant;
	private Participant secondParticipant;
	private Teamname name;

	private Team() {
		uuid = UUID.randomUUID();
	}

	private Team(Participant aFirstParticipant, Participant aSecondParticipant) {
		uuid = UUID.randomUUID();
		firstParticipant = aFirstParticipant;
		secondParticipant = aSecondParticipant;
	}

	public static Team newInstance() {
		return new Team();
	}

	public static Team newInstance(Participant aFirstParticipant, Participant aSecondParticipant) {
		return new Team(aFirstParticipant, aSecondParticipant);
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID anUuid) {
		uuid = anUuid;
	}

	public void setName(Teamname aName) {
		name = aName;
	}

	public Teamname getName() {
		return name;
	}

	public Participant getFirstParticipant() {
		return firstParticipant;
	}

	public Participant getSecondParticipant() {
		return secondParticipant;
	}

	@Override
	public String toString() {
		return name.toString();
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
		Team other = (Team) obj;
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