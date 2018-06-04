package com.kita.web.bridge;

import java.util.List;
import java.util.UUID;

import com.kita.Person;
import com.kita.orm.GatewayType;
import com.kita.orm.Validation;

/**
 * This Bridge could handle the REST - Serivce
 * Actual its only a layer for the PersonGateway.
 *
 * @since 22.05.2018
 *
 */
public interface PersonBridge {

	List<Person> all();

	Validation persistPerson(Person person);

	Person get(UUID uuid);

	void remove(Person person);

	GatewayType getGatewayType();
}