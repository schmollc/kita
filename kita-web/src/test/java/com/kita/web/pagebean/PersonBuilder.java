package com.kita.web.pagebean;

import java.util.UUID;

import com.kita.Person;
import com.kita.attributes.Forename;
import com.kita.attributes.Surename;

/**
 * @since   22.05.2018
 *
 */
public class PersonBuilder {

	private UUID uuid = UUID.randomUUID();
	private Surename surename = Surename.newInstance("Surename");
	private Forename forename = Forename.newInstance("Forename");

	public PersonBuilder withUuid(UUID anUuid) {
		uuid = anUuid;
		return this;
	}

	public PersonBuilder withSurename(Surename aSurename) {
		surename = aSurename;
		return this;
	}

	public PersonBuilder withForename(Forename aForename) {
		forename = aForename;
		return this;
	}

	public Person build() {
		Person person = Person.newInstance();
		person.setUuid(uuid);
		person.setSurename(surename);
		person.setForename(forename);

		return person;
	}
}