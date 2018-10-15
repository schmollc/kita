package com.kita.attributes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.attributes.Nickname.NicknameNullObject;


/**
 * Verlasse dich auf nichts.
 *  - Miyamoto Musashi
 *
 * @since   14.10.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NicknameTest {

	@Test
	public void testIsSerializable() {
		String dummyString = "";
		Nickname sut = Nickname.newInstance(dummyString);

		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;
		assertTrue("Class not Serializable!", condition);
	}

	@Test
	public void testNewInstance() {
		Nickname sut = Nickname.newInstance();

		assertNotNull("Not a valid instance!", sut);

		boolean actual = (sut.getClass() == NicknameNullObject.class);
		assertTrue("Instance is not correct!", actual);
	}
	@Test
	public void testNewInstance_ForValidValue() {
		String expected = "Torkoenig";
		Nickname sut = Nickname.newInstance(expected);

		String actual = sut.value;
		assertEquals("[value] not correct!", expected, actual);
	}

	@Test
	public void testNewInstance_ForNullValue() {
		Nickname sut = Nickname.newInstance(null);

		assertNotNull("Not a valid instance!", sut);

		boolean actual = (sut.getClass() == NicknameNullObject.class);
		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testNewInstance_ForEmptyValue() {
		Nickname sut = Nickname.newInstance("");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = (sut.getClass() == NicknameNullObject.class);
		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testNewInstance_ForBlankValue() {
		Nickname sut = Nickname.newInstance("   ");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = (sut.getClass() == NicknameNullObject.class);
		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testIsEmpty_ForValidValue() {
		Nickname sut = Nickname.newInstance("Torkoenig");

		boolean condition = sut.isEmpty();

		assertFalse("[condition] for isEmpty is not correct!", condition);
	}

	@Test
	public void testIsEmpty_ForNullObject() {
		Nickname sut = Nickname.newInstance();

		boolean condition = sut.isEmpty();

		assertTrue("[condition] for isEmpty is not correct!", condition);
	}

	@Test
	public void testSortByNickname() {
		Nickname name1 = Nickname.newInstance("Torkoenig");
		Nickname name2 = Nickname.newInstance("Champ");

		int actual = Nickname.sortByNickname(name1, name2);

		int expected = 17;
		assertEquals("[sortByNickname] not correct!", expected, actual);
	}

	@Test
	public void testSortByNickname_ForSameNames() {
		Nickname name1 = Nickname.newInstance("Torkoenig");
		Nickname name2 = Nickname.newInstance("Torkoenig");

		int actual = Nickname.sortByNickname(name1, name2);

		int expected = 0;
		assertEquals("[sortByNickname] not correct!", expected, actual);
	}

	@Test
	public void testSortByNickname_ForSameNamesWithDiffrentCamelCase() {
		Nickname name1 = Nickname.newInstance("Torkoenig");
		Nickname name2 = Nickname.newInstance("torKoenig");

		int actual = Nickname.sortByNickname(name1, name2);

		int expected = 0;
		assertEquals("[sortByNickname] not correct!", expected, actual);
	}

	@Test
	public void testToString_ForValidValue() {
		String expected = "Jonas";
		Nickname sut = Nickname.newInstance(expected);

		String actual = sut.toString();
		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testToString_ForNullObject() {
		String expected = "";
		Nickname sut = Nickname.newInstance();

		String actual = sut.toString();
		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testHashCode() {
		Nickname sut = Nickname.newInstance("Torkoenig");

		int hashCode = sut.hashCode();
		assertEquals(-58748159, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();
		assertEquals(31, hashCode);
	}
	@Test
	public void testEqualsWithMyself() {
		Nickname sut = Nickname.newInstance("Torkoenig");

		boolean condition = sut.equals(sut);

		assertTrue(condition);
	}

	@Test
	public void testEqualsWithNull() {
		Nickname sut = Nickname.newInstance("Torkoenig");

		boolean condition = sut.equals(null);

		assertFalse(condition);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		Nickname sut = Nickname.newInstance("Torkoenig");

		@SuppressWarnings("unlikely-arg-type")
		boolean condition = sut.equals(new String());

		assertFalse(condition);
	}

	@Test
	public void testEqualsWithValueIsNull() {
		Nickname sut = Nickname.newInstance("Torkoenig");
		sut.value = null;
		Nickname secondSut = Nickname.newInstance("Torkoenig");

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		Nickname sut = Nickname.newInstance("Torkoenig");
		sut.value = null;
		Nickname secondSut = Nickname.newInstance("Torkoenig");
		secondSut.value = null;

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		Nickname sut = Nickname.newInstance("Torkoenig");
		Nickname secondSut = Nickname.newInstance("NotTorkoenig");

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);
	}

	@Test
	public void testEqualsWithSameValues() {
		Nickname sut = Nickname.newInstance("Torkoenig");
		Nickname secondSut = Nickname.newInstance("Torkoenig");

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}
}