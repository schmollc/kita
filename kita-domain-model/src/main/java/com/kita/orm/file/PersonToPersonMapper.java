package com.kita.orm.file;

import com.kita.Person;

/**
 * @since 18.05.2018
 *
 */
public class PersonToPersonMapper {

	private PersonToPersonMapper() {
	}

	public static PersonToPersonMapper newInstance() {
		return new PersonToPersonMapper();
	}

	public void mapPersonToPerson(Person source, Person target) {
		if (source == null) {
			throw new IllegalArgumentException("[source] must not be 'null'!");
		}
		if (target == null) {
			throw new IllegalArgumentException("[target] must not be 'null'!");
		}

		target.setSurename(source.getSurename());
		target.setForename(source.getForename());
		target.setKampfname(source.getKampfname());
	}
}