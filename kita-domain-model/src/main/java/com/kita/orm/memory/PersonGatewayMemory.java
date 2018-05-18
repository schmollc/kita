package com.kita.orm.memory;

import java.util.List;
import java.util.UUID;

import com.kita.Person;
import com.kita.orm.PersonGateway;

/**
 * @since   18.05.2018
 *
 */
public class PersonGatewayMemory implements PersonGateway {

	private PersonGatewayMemory() {

	}

	public static PersonGateway newInstance() {
		return new PersonGatewayMemory();
	}

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