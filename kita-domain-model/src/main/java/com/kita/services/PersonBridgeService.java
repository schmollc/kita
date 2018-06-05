package com.kita.services;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.kita.Person;
import com.kita.Settings;
import com.kita.attributes.Email;
import com.kita.orm.GatewayType;
import com.kita.orm.PersonGateway;
import com.kita.orm.PersonGatewayFactory;
import com.kita.orm.Validation;

/**
 * Only a simple Wrapper for the Gateway.
 *
 * @since 22.05.2018
 *
 */
public class PersonBridgeService implements Serializable {
	private static final long serialVersionUID = -564518438845267226L;

	private PersonBridgeService() {

	}

	public static PersonBridgeService newInstance() {
		return new PersonBridgeService();
	}

	public List<Person> all() {
		return getGateway().getAll();
	}

	public Validation persistPerson(Person person) {
		Validation validationResult;
		if (doesEmailExist(person)) {
			validationResult = Validation.failure();
		} else {
			validationResult = Validation.ok();
			getGateway().set(person);
		}
		return validationResult;
	}


	boolean doesEmailExist(Person personToCheck) {
		for (Person person : all()) {
			if (!personToCheck.equals(person)) {
				if (emailsEqual(personToCheck, person)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean emailsEqual(Person personToCheck, Person person) {
		Email email = person.getEmail();
		return email.equals(personToCheck.getEmail());
	}
	
	public Person get(UUID uuid) {
		return getGateway().get(uuid);
	}

	public void remove(Person person) {
		getGateway().remove(person.getUuid());
	}

	public GatewayType getGatewayType() {
		return Settings.getGatewayType();
	}

	public PersonGateway getGateway() {
		return PersonGatewayFactory.get(getGatewayType());
	}
}