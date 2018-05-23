package com.kita;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.attributes.Forename;
import com.kita.attributes.Kampfname;
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
	public void testIsSerializable() {
		Person sut = Person.newInstance();

		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;
		assertTrue("Class not Serializable!", condition);
	}

	@Test
	public void testNewInstance() {
		Person sut = Person.newInstance();

		assertNotNull("Not a valid instance!", sut);

		Object object = sut.getUuid();
		assertNotNull("[uuid] is not correct!", object);
	}

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
	public void testKampfname() {
		Person sut = Person.newInstance();
		Kampfname expected = Kampfname.newInstance("Lokomotive C&A");

		sut.setKampfname(expected);

		Kampfname actual = sut.getKampfname();
		assertEquals("[kampfname] not correct!", expected, actual);
	}

	@Test
	public void testToString() {
		Person sut = Person.newInstance();

		sut.setForename(Forename.newInstance("Justus"));
		sut.setSurename(Surename.newInstance("Jonas"));
		sut.setKampfname(Kampfname.newInstance("Lokomotive C&A"));

		String actual = sut.toString();

		String expected = "Justus Jonas - Lokomotive C&A";
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

	@Test
	public void testSetKampfname_ForNullValue() {
		Person sut = Person.newInstance();

		sut.setKampfname(null);

		Kampfname actual = sut.getKampfname();
		assertNotNull("Person must not return [kampfname] equals 'null'!", actual);
	}

	@Test
	public void testHashCode() {
		Person sut = Person.newInstance();
		sut.setUuid(UUID.fromString("2697d710-8967-4b2d-9ab2-8fc50ddc6138"));

		int hashCode = sut.hashCode();

		assertEquals(949908191, hashCode);

		sut.setUuid(null);

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEqualsWithMyself() {
		Person sut = Person.newInstance();

		boolean actual = sut.equals(sut);

		assertTrue(actual);
	}

	@Test
	public void testEqualsWithNull() {
		Person sut = Person.newInstance();

		boolean actual = sut.equals(null);

		assertFalse(actual);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		Person sut = Person.newInstance();

		@SuppressWarnings("unlikely-arg-type")
		boolean actual = sut.equals(new String());

		assertFalse(actual);
	}

	@Test
	public void testEqualsWithValueIsNull() {
		Person sut = Person.newInstance();
		sut.setUuid(null);
		Person secondSut = Person.newInstance();

		boolean actual = sut.equals(secondSut);

		assertFalse(actual);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		Person sut = Person.newInstance();
		sut.setUuid(null);
		Person secondSut = Person.newInstance();
		secondSut.setUuid(null);

		boolean actual = sut.equals(secondSut);

		assertTrue(actual);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		Person sut = Person.newInstance();
		Person secondSut = Person.newInstance();

		boolean actual = sut.equals(secondSut);

		assertFalse(actual);
	}

	@Test
	public void testEqualsWithSameValues() {
		Person sut = Person.newInstance();
		Person secondSut = Person.newInstance();
		sut.setUuid(secondSut.getUuid());

		boolean actual = sut.equals(secondSut);

		assertTrue(actual);
	}

}