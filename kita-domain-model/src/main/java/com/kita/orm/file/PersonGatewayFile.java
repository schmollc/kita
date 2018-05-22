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

	private PersonToPersonMapper personToPersonMapper = PersonToPersonMapper.newInstance();

	private PersonGatewayFile() {

	}

	private PersonGatewayFile(String aFileName) {
		FileSingleton.getInstance().setFileName(aFileName);
	}

	public static PersonGateway newInstance() {
		return new PersonGatewayFile();
	}

	public static PersonGateway newInstance(String aFilename) {
		return new PersonGatewayFile(aFilename);
	}

	@Override
	public List<Person> getAll() {
		return FileSingleton.getInstance().getPersons();
	}

	@Override
	public Person get(UUID uuid) {
		for (Person person : getAll()) {
			if (uuid.equals(person.getUuid())) {
				return person;
			}
		}
		return null;
	}

	@Override
	public void set(Person updatePerson) {
		List<Person> somePersons = getAll();

		if (somePersons.contains(updatePerson)) {
			for (Person person : somePersons) {
				if (updatePerson.equals(person)) {
					getPersonToPersonMapper().mapPersonToPerson(updatePerson, person);
					break;
				}
			}
		} else {
			somePersons.add(updatePerson);
		}
		FileSingleton.getInstance().setPersons(somePersons);
	}

	@Override
	public void remove(UUID uuid) {
		Person personToRemove = Person.newInstance();
		personToRemove.setUuid(uuid);
		List<Person> all = getAll();
		all.remove(personToRemove);
		FileSingleton.getInstance().setPersons(all);
	}

	private PersonToPersonMapper getPersonToPersonMapper() {
		return personToPersonMapper;
	}

}