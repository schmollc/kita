package com.kita;

import com.kita.attributes.Forename;

/**
 * @since   18.05.2018
 *
 */
public class Person {

	private Forename forename;

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

	@Override
	public String toString() {
		return "" + getForename();
	}
}
