package com.kita.orm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.Person;
import com.kita.attributes.Forename;
import com.kita.attributes.Nickname;
import com.kita.attributes.Surename;

/**
 * Betrachte das, was du tust, als die wichtigste Sache der Welt.
 * Sei dir aber bewusst, dass es andere nicht für die wichtigste Sache der Welt halten.
 *  - Bruce Springsteen
 *
 * @since   18.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class PersonGatewayTest {

	public abstract PersonGateway getSut();

	@Test
	public void testGet_ForExistingEntry() {
		Person firstMember = createJustusJonas();
		getSut().set(firstMember);
		getSut().set(createPeterShaw());

		Person result = getSut().get(firstMember.getUuid());

		assertEquals("[Forename] not correct.", firstMember.getForename(), result.getForename());
		assertEquals("[Surename] not correct.", firstMember.getSurename(), result.getSurename());
	}

	@Test
	public void testGet_ForNonExistingEntry() {
		getSut().set(createJustusJonas());
		getSut().set(createPeterShaw());

		Person result = getSut().get(UUID.randomUUID());

		assertNull(result);
	}

	@Test
	public void testGetAll() {
		getSut().set(createJustusJonas());
		getSut().set(createPeterShaw());

		List<Person> result = getSut().getAll();
		assertEquals(2, result.size());
	}

	@Test
	public void testRemove() {
		Person member = createJustusJonas();
		getSut().set(member);
		UUID uuid = member.getUuid();

		assertEquals("Error for create member. ", 1, getSut().getAll().size());

		getSut().remove(uuid);

		Person removedPerson = getSut().get(uuid);

		assertNull("Expected invalid instance.", removedPerson);
	}

	@Test
	public void testSet_ForExistingPerson() {
		// ARRANGE
		Person person = createJustusJonas();
		UUID uuidFromPerson = person.getUuid();

		getSut().set(person);
		getSut().set(createPeterShaw());

		Person updatePerson = getSut().get(uuidFromPerson);

		Forename newForename = Forename.newInstance("Bob");
		updatePerson.setForename(newForename);
		Surename newSurename = Surename.newInstance("Andrews");
		updatePerson.setSurename(newSurename);
		Nickname newNickname = Nickname.newInstance("Goalkeeper");
		updatePerson.setNickname(newNickname);

		// ACT
		getSut().set(updatePerson);

		// ASSERT
		Person checkPerson = getSut().get(uuidFromPerson);
		assertNotNull(checkPerson);

		assertEquals("[Forename] not correct.", newForename, checkPerson.getForename());
		assertEquals("[Surename] not correct.", newSurename, checkPerson.getSurename());
		assertEquals("[Nickname] not correct.", newNickname, checkPerson.getNickname());

	}

	private Person createJustusJonas() {
		return createPerson("Justus", "Jonas", "Erster Detektiv");
	}

	private Person createPeterShaw() {
		return createPerson("Peter", "Shaw", "Zweiter Detektiv");
	}

	private Person createPerson(String forename, String surename, String nickname) {
		Person person = Person.newInstance();

		person.setForename(Forename.newInstance(forename));
		person.setSurename(Surename.newInstance(surename));
		person.setNickname(Nickname.newInstance(nickname));

		return person;
	}
}