package com.kita;

import com.kita.attributes.Forename;
import com.kita.attributes.Surename;

/**
 * @since   18.05.2018
 *
 */
public class Person {

	private Forename forename;
	private Surename surename;

	private Person() {

	}

	public static Person newInstance() {
		return new Person();
	}

	public void setForename(Forename aForename) {
		forename = aForename;
	}

	private Forename getForename() {
		return forename;
	}

	public void setSurename(Surename aSurename) {
		surename = aSurename;
	}

	private Surename getSurename() {
		return surename;
	}

	@Override
	public String toString() {
		return getForename() + " " + getSurename();
	}
}
