package com.kita.orm;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.Person;
import com.kita.attributes.Forename;
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
	public void testGetForExistingEntry() {
		Person firstMember = createJustusJonas();
		getSut().set(firstMember);
		getSut().set(createPeterShaw());

		Person result = getSut().get(firstMember.getUuid());

		assertEquals("[Forename] not correct.", firstMember.getForename(), result.getForename());
		assertEquals("[Surename] not correct.", firstMember.getSurename(), result.getSurename());
	}

	@Test
	public void testGetForNonExistingEntry() {
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
		Person firstMember = createJustusJonas();
		UUID uuidFromFirstMember = firstMember.getUuid();

		getSut().set(firstMember);
		getSut().set(createPeterShaw());

		Person updateMember = getSut().get(uuidFromFirstMember);

		Forename newForename = Forename.newInstance("Bob");
		updateMember.setForename(newForename);
		Surename newSurename = Surename.newInstance("Andrews");
		updateMember.setSurename(newSurename);

		// ACT
		getSut().set(updateMember);

		// ASSERT
		Person checkPerson = getSut().get(uuidFromFirstMember);
		assertNotNull(checkPerson);

		assertEquals("[Forename] not correct.", newForename, checkPerson.getForename());
		assertEquals("[Surename] not correct.", newSurename, checkPerson.getSurename());

	}

	private Person createJustusJonas() {
		return createPerson("Justus", "Jonas");
	}

	private Person createPeterShaw() {
		return createPerson("Peter", "Shaw");
	}

	private Person createPerson(String forename, String surename) {
		Person person = Person.newInstance();

		person.setForename(Forename.newInstance(forename));
		person.setSurename(Surename.newInstance(surename));
		return person;
	}

}