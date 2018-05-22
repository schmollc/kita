package com.kita;

import java.io.Serializable;
import java.util.UUID;

import com.kita.attributes.Forename;
import com.kita.attributes.Kampfname;
import com.kita.attributes.Surename;

/**
 * @since   18.05.2018
 *
 */
public class Person implements Serializable {
	private static final long serialVersionUID = 1080782640277848814L;

	private Surename surename = Surename.newInstance();
	private Forename forename = Forename.newInstance();
	private UUID uuid;

	private Kampfname kampfname;

	private Person() {
		uuid = UUID.randomUUID();
	}

	public static Person newInstance() {
		return new Person();
	}

	public void setUuid(UUID anUuid) {
		uuid = anUuid;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setForename(Forename aForename) {
		if (aForename == null) {
			forename = Forename.newInstance();
		} else {
			forename = aForename;
		}
	}

	public Forename getForename() {
		return forename;
	}

	public void setSurename(Surename aSurename) {
		if (aSurename == null) {
			surename = Surename.newInstance();
		} else {
			surename = aSurename;
		}
	}

	public Surename getSurename() {
		return surename;
	}

	public void setKampfname(Kampfname aKampfname) {
		if (aKampfname == null) {
			kampfname = Kampfname.newInstance();
		} else {
			kampfname = aKampfname;
		}
	}

	public Kampfname getKampfname() {
		return kampfname;
	}

	@Override
	public String toString() {
		return getForename() + " " + getSurename() + " - " + getKampfname();
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
		Person other = (Person) obj;
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