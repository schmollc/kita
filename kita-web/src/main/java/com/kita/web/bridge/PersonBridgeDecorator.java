package com.kita.web.bridge;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.kita.Person;
import com.kita.orm.Validation;
import com.kita.services.PersonBridgeService;

/**
 * @since 22.05.2018
 *
 */
public class PersonBridgeDecorator implements Serializable, PersonBridge {
	private static final long serialVersionUID = -564518438845267226L;

	// FCoI
	private PersonBridgeService personBridgeService = PersonBridgeService.newInstance();

	private PersonBridgeDecorator() {

	}

	public static PersonBridge newInstance() {
		return new PersonBridgeDecorator();
	}

	private PersonBridgeService getPersonBridgeImpl() {
		return personBridgeService;
	}

	@Override
	public List<Person> all() {
		return getPersonBridgeImpl().all();
	}

	@Override
	public Validation persistPerson(Person person) {
		return getPersonBridgeImpl().persistPerson(person);
	}

	@Override
	public Person get(UUID uuid) {
		return getPersonBridgeImpl().get(uuid);
	}

	@Override
	public void remove(Person person) {
		getPersonBridgeImpl().remove(person);
	}
}