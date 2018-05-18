package com.kita;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.attributes.Forename;
import com.kita.attributes.Surename;

/**
 * Discipline is the best tool.
 * - Anonymous
 *
 * @since   18.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonTest {

	@Test
	public void testUUID() {
		Person sut = Person.newInstance();
		UUID expected = UUID.randomUUID();

		sut.setUuid(expected);

		UUID actual = sut.getUuid();
		assertEquals("[uuid] not correct!", expected, actual);
	}

	@Test
	public void testSurename() {
		Person sut = Person.newInstance();
		Surename expected = Surename.newInstance("Justus");

		sut.setSurename(expected);

		Surename actual = sut.getSurename();
		assertEquals("[surename] not correct!", expected, actual);
	}

	@Test
	public void testForename() {
		Person sut = Person.newInstance();
		Forename expected = Forename.newInstance("Jonas");

		sut.setForename(expected);

		Forename actual = sut.getForename();
		assertEquals("[forename] not correct!", expected, actual);
	}

	@Test
	public void testToString() {
		Person sut = Person.newInstance();

		sut.setForename(Forename.newInstance("Justus"));
		sut.setSurename(Surename.newInstance("Jonas"));

		String actual = sut.toString();

		String expected = "Justus Jonas";
		assertEquals("[toString] not correct!", expected, actual);
	}

	@Test
	public void testSetForename_ForNullValue() {
		Person sut = Person.newInstance();

		sut.setForename(null);

		Forename actual = sut.getForename();
		assertNotNull("Person must not return [forename] equals 'null'!", actual);
	}

	@Test
	public void testSetSurename_ForNullValue() {
		Person sut = Person.newInstance();

		sut.setSurename(null);

		Surename actual = sut.getSurename();
		assertNotNull("Person must not return [surename] equals 'null'!", actual);
	}

}