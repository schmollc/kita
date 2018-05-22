package com.kita.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.attributes.Kampfname.KampfnameNullObject;

/**
 * Verlasse dich auf nichts.
 *  - Miyamoto Musashi
 *
 * @since   18.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KampfnameTest {

	@Test
	public void testIsSerializable() {
		String dummyString = "";
		Kampfname sut = Kampfname.newInstance(dummyString);

		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;
		assertTrue("Class not Serializable!", condition);
	}

	@Test
	public void testNewInstance() {
		Kampfname sut = Kampfname.newInstance();

		assertNotNull("Not a valid instance!", sut);

		boolean actual = (sut.getClass() == KampfnameNullObject.class);
		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testNewInstance_ForValidValue() {
		String expected = "Jonas";
		Kampfname sut = Kampfname.newInstance(expected);

		String actual = sut.value;
		assertEquals("[value] not correct!", expected, actual);
	}

	@Test
	public void testNewInstance_ForBlankValue() {
		Kampfname sut = Kampfname.newInstance("   ");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = (sut.getClass() == KampfnameNullObject.class);
		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testNewInstance_ForEmptyValue() {
		Kampfname sut = Kampfname.newInstance("");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = (sut.getClass() == KampfnameNullObject.class);
		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testNewInstance_ForNullValue() {
		Kampfname sut = Kampfname.newInstance(null);

		assertNotNull("Not a valid instance!", sut);

		boolean actual = (sut.getClass() == KampfnameNullObject.class);
		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testIsEmpty_ForValidValue() {
		Kampfname sut = Kampfname.newInstance("Jonas");

		boolean condition = sut.isEmpty();

		assertFalse("[condition] for isEmpty is not correct!", condition);
	}

	@Test
	public void testIsEmpty_ForNullObject() {
		Kampfname sut = Kampfname.newInstance();

		boolean condition = sut.isEmpty();

		assertTrue("[condition] for isEmpty is not correct!", condition);
	}

	@Test
	public void testSortByKampfname() {
		Kampfname name1 = Kampfname.newInstance("Jonas");
		Kampfname name2 = Kampfname.newInstance("Shaw");

		int actual = Kampfname.sortByKampfname(name1, name2);

		int expected = -9;
		assertEquals("[position] not correct!", expected, actual);
	}

	@Test
	public void testSortByKampfname_ForSameNames() {
		Kampfname name1 = Kampfname.newInstance("Jonas");
		Kampfname name2 = Kampfname.newInstance("jonas");

		int actual = Kampfname.sortByKampfname(name1, name2);

		int expected = 0;
		assertEquals("[position] not correct!", expected, actual);
	}

	@Test
	public void testToString_ForValidValue() {
		String expected = "Jonas";
		Kampfname sut = Kampfname.newInstance(expected);

		String actual = sut.toString();
		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testToString_ForNullObject() {
		String expected = "";
		Kampfname sut = Kampfname.newInstance();

		String actual = sut.toString();
		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testHashCode() {
		Kampfname sut = Kampfname.newInstance("Kampfname");

		int hashCode = sut.hashCode();
		assertEquals(391470615, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();
		assertEquals(31, hashCode);
	}

	@Test
	public void testEqualsWithMyself() {
		Kampfname sut = Kampfname.newInstance("Kampfname");

		boolean condition = sut.equals(sut);

		assertTrue(condition);
	}

	@Test
	public void testEqualsWithNull() {
		Kampfname sut = Kampfname.newInstance("Kampfname");

		boolean condition = sut.equals(null);

		assertFalse(condition);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		Kampfname sut = Kampfname.newInstance("Kampfname");

		@SuppressWarnings("unlikely-arg-type")
		boolean condition = sut.equals(new String());

		assertFalse(condition);
	}

	@Test
	public void testEqualsWithValueIsNull() {
		Kampfname sut = Kampfname.newInstance("Kampfname");
		sut.value = null;
		Kampfname secondSut = Kampfname.newInstance("Kampfname");

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		Kampfname sut = Kampfname.newInstance("Kampfname");
		sut.value = null;
		Kampfname secondSut = Kampfname.newInstance("Kampfname");
		secondSut.value = null;

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		Kampfname sut = Kampfname.newInstance("Kampfname");
		Kampfname secondSut = Kampfname.newInstance("NotKampfname");

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);
	}

	@Test
	public void testEqualsWithSameValues() {
		Kampfname sut = Kampfname.newInstance("Kampfname");
		Kampfname secondSut = Kampfname.newInstance("Kampfname");

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}
}