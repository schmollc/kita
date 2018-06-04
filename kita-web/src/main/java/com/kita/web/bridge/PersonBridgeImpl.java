package com.kita.web.bridge;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.kita.Person;
import com.kita.Settings;
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
public class PersonBridgeImpl implements Serializable, PersonBridge {
	private static final long serialVersionUID = -564518438845267226L;

	@Override
	public List<Person> all() {
		return getGateway().getAll();
	}

	@Override
	public Validation persistPerson(Person aPerson) {
		getGateway().set(aPerson);
		return Validation.newInstance();
	}

	@Override
	public Person get(UUID uuid) {
		return getGateway().get(uuid);
	}

	@Override
	public void remove(Person person) {
		getGateway().remove(person.getUuid());
	}

	@Override
	public GatewayType getGatewayType() {
		return Settings.getGatewayType();
	}

	public PersonGateway getGateway() {
		return PersonGatewayFactory.get(getGatewayType());
	}
}