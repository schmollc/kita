package com.kita;

import java.util.UUID;

import com.kita.attributes.Email;
import com.kita.attributes.Forename;
import com.kita.attributes.Nickname;
import com.kita.attributes.Surename;

/**
 * @since   22.05.2018
 *
 */
public class PersonBuilder {

	private UUID uuid = UUID.randomUUID();
	private Surename surename = Surename.newInstance("Surename");
	private Forename forename = Forename.newInstance("Forename");
	private Nickname nickname = Nickname.newInstance("Nickname");
		
	private Email email = Email.newInstance("Email@google.com");

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

	public PersonBuilder withNickname(Nickname aNickname) {
		nickname = aNickname;
		return this;
	}

	public PersonBuilder withEmail(Email anEmail) {
		email = anEmail;
		return this;
	}
	
	public PersonBuilder withEmail(String anEmail) {
		email = Email.newInstance(anEmail);
		return this;
	}

	public Person build() {
		Person person = Person.newInstance();

		person.setUuid(uuid);
		person.setEmail(email);
		person.setSurename(surename);
		person.setForename(forename);
		person.setNickname(nickname);
		return person;
	}
}