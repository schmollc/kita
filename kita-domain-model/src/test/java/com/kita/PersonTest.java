package com.kita;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.attributes.Email;
import com.kita.attributes.Forename;
import com.kita.attributes.Nickname;
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
	public void testNickname() {
		Person sut = Person.newInstance();
		Nickname expected = Nickname.newInstance("Torkoenig");

		sut.setNickname(expected);

		Nickname actual = sut.getNickname();
		assertEquals("[Nickname] not correct!", expected, actual);
	}

	@Test
	public void testEmail() {
		Person sut = Person.newInstance();
		Email expected = Email.newInstance("Justus.Jonas@rockyBeach.com");

		sut.setEmail(expected);

		Email actual = sut.getEmail();
		assertEquals("[email] not correct!", expected, actual);
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
	public void testSetNickname_ForNullValue() {
		Person sut = Person.newInstance();

		sut.setNickname(null);

		Nickname actual = sut.getNickname();
		assertNotNull("Person must not return [nickname] equals 'null'!", actual);
	}

	@Test
	public void testIsTheSame_WithSameEmail() {
		Person sut = Person.newInstance();

		sut.setEmail(Email.newInstance("Justus.Jonas@rockyBeach.com"));

		Person secondSut = Person.newInstance();

		secondSut.setEmail(Email.newInstance("Justus.Jonas@rockyBeach.com"));

		boolean condition = sut.isSame(secondSut);

		assertTrue("[isSame] not correct!", condition);
	}

	@Test
	public void testIsTheSame_WithNotSameValues() {
		Person sut = Person.newInstance();

		sut.setEmail(Email.newInstance("Justus.Jonas@rockyBeach.com"));

		Person secondSut = Person.newInstance();

		secondSut.setEmail(Email.newInstance("Peter.Shaw@rockyBeach.com"));

		boolean condition = sut.isSame(secondSut);

		assertFalse("[isSame] not correct!", condition);
	}

	@Test
	public void testToString() {
		Person sut = Person.newInstance();

		sut.setForename(Forename.newInstance("Justus"));
		sut.setSurename(Surename.newInstance("Jonas"));
		sut.setNickname(Nickname.newInstance("Torkoenig"));

		String actual = sut.toString();

		String expected = "Torkoenig [Justus Jonas]";
		assertEquals("[toString] not correct!", expected, actual);
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