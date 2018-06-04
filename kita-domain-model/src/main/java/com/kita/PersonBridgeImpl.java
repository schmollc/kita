package com.kita;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

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
public class PersonBridgeImpl implements Serializable {
	private static final long serialVersionUID = -564518438845267226L;

	private PersonBridgeImpl() {

	}

	public static PersonBridgeImpl newInstance() {
		return new PersonBridgeImpl();
	}

	public List<Person> all() {
		return getGateway().getAll();
	}

	public Validation persistPerson(Person person) {
		Validation validationResult = Validation.newInstance();
		if (doesEmailExist(person.getEmail())) {
		} else {
			getGateway().set(person);
		}
		return validationResult;
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

	boolean doesEmailExist(Email email) {
		for (Person each : all()) {
			if (each.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}
}