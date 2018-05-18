package com.kita;

import java.util.UUID;

import com.kita.attributes.Forename;
import com.kita.attributes.Surename;

/**
 * @since   18.05.2018
 *
 */
public class Person {

	private Surename surename = Surename.newInstance();
	private Forename forename = Forename.newInstance();
	private UUID uuid;

	private Person() {

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

	@Override
	public String toString() {
		return getForename() + " " + getSurename();
	}
}
