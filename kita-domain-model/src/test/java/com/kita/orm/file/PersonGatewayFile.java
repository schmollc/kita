package com.kita.orm.file;

import java.util.List;
import java.util.UUID;

import com.kita.Person;
import com.kita.orm.PersonGateway;

/**
 * @since   18.05.2018
 *
 */
public class PersonGatewayFile implements PersonGateway {

	@Override
	public List<Person> getAll() {
		return null;
	}

	@Override
	public Person get(UUID aUuid) {
		return null;
	}

	@Override
	public void set(Person aPerson) {
	}

	@Override
	public void remove(UUID aUuid) {
	}
}