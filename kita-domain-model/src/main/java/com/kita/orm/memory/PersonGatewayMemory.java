package com.kita.orm.memory;

import java.util.ArrayList;
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
	public Person get(UUID uuid) {
		return MemorySingleton.getInstance().getPersons().get(uuid);
	}

	@Override
	public void set(Person person) {
		MemorySingleton.getInstance().getPersons().put(person.getUuid(), person);
	}

	@Override
	public void remove(UUID uuid) {
		MemorySingleton.getInstance().getPersons().remove(uuid);
	}

	@Override
	public List<Person> getAll() {
		ArrayList<Person> personsAsList = new ArrayList<Person>(MemorySingleton.getInstance().getPersons().values());
		return personsAsList;
	}
}