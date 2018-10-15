package com.kita;

import java.io.Serializable;
import java.util.UUID;

import com.kita.attributes.Email;
import com.kita.attributes.Forename;
import com.kita.attributes.Nickname;
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

	private Nickname nickname;

	private Email email;

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

	public void setNickname(Nickname aNickname) {
		if (aNickname == null) {
			nickname = Nickname.newInstance();
		} else {
			nickname = aNickname;
		}
	}

	public Nickname getNickname() {
		return nickname;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email anEmail) {
		if (anEmail == null) {
			email = Email.newInstance();
		} else {
			email = anEmail;
		}
	}

	public boolean isSame(Person person) {
		if (getEmail().equals(person.getEmail())) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return getNickname() + " [" + getForename() + " " + getSurename() + "]";
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