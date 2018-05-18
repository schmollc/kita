package com.kita.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.attributes.Surename.SurenameNullObject;

/**
 * Jede lange Reise beginnt mit dem ersten Schritt
 *  - Laotse
 *
 * @since   18.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SurenameTest {

	@Test
	public void testIsSerializable() {
		String dummyString = "";
		Surename sut = Surename.newInstance(dummyString);

		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;
		assertTrue("Class not Serializable!", condition);
	}

	@Test
	public void testNewInstance() {
		Surename sut = Surename.newInstance();

		assertNotNull("Not a valid instance!", sut);

		boolean actual = (sut.getClass() == SurenameNullObject.class);
		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testNewInstance_ForValidValue() {
		String expected = "Jonas";
		Surename surename = Surename.newInstance(expected);

		String actual = surename.value;
		assertEquals("[value] not correct!", expected, actual);
	}

	@Test
	public void testNewInstance_ForBlankValue() {
		Surename sut = Surename.newInstance("   ");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = (sut.getClass() == SurenameNullObject.class);
		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testNewInstance_ForEmptyValue() {
		Surename sut = Surename.newInstance("");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = (sut.getClass() == SurenameNullObject.class);
		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testNewInstance_ForNullValue() {
		Surename sut = Surename.newInstance(null);

		assertNotNull("Not a valid instance!", sut);

		boolean actual = (sut.getClass() == SurenameNullObject.class);
		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testIsEmpty_ForValidValue() {
		Surename sut = Surename.newInstance("Jonas");

		boolean condition = sut.isEmpty();

		assertFalse("[condition] for isEmpty is not correct!", condition);
	}

	@Test
	public void testIsEmpty_ForNullObject() {
		Surename sut = Surename.newInstance();

		boolean condition = sut.isEmpty();

		assertTrue("[condition] for isEmpty is not correct!", condition);
	}

	@Test
	public void testSortBySurename() {
		Surename name1 = Surename.newInstance("Jonas");
		Surename name2 = Surename.newInstance("Shaw");

		int actual = Surename.sortBySurename(name1, name2);

		int expected = -9;
		assertEquals("[position] not correct!", expected, actual);
	}

	@Test
	public void testSortBySurename_ForSameNames() {
		Surename name1 = Surename.newInstance("Jonas");
		Surename name2 = Surename.newInstance("jonas");

		int actual = Surename.sortBySurename(name1, name2);

		int expected = 0;
		assertEquals("[position] not correct!", expected, actual);
	}

	@Test
	public void testToString_ForValidValue() {
		String expected = "Jonas";
		Surename surename = Surename.newInstance(expected);

		String actual = surename.toString();
		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testToString_ForNullObject() {
		String expected = "";
		Surename surename = Surename.newInstance();

		String actual = surename.toString();
		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testHashCode() {
		Surename sut = Surename.newInstance("Surename");

		int hashCode = sut.hashCode();
		assertEquals(-1551509409, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();
		assertEquals(31, hashCode);
	}

	@Test
	public void testEqualsWithMyself() {
		Surename sut = Surename.newInstance("Surename");

		boolean condition = sut.equals(sut);

		assertTrue(condition);
	}

	@Test
	public void testEqualsWithNull() {
		Surename sut = Surename.newInstance("Surename");

		boolean condition = sut.equals(null);

		assertFalse(condition);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		Surename sut = Surename.newInstance("Surename");

		@SuppressWarnings("unlikely-arg-type")
		boolean condition = sut.equals(new String());

		assertFalse(condition);
	}

	@Test
	public void testEqualsWithValueIsNull() {
		Surename sut = Surename.newInstance("Surename");
		sut.value = null;
		Surename secondSut = Surename.newInstance("Surename");

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		Surename sut = Surename.newInstance("Surename");
		sut.value = null;
		Surename secondSut = Surename.newInstance("Surename");
		secondSut.value = null;

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		Surename sut = Surename.newInstance("Surename");
		Surename secondSut = Surename.newInstance("NotSurename");

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);
	}

	@Test
	public void testEqualsWithSameValues() {
		Surename sut = Surename.newInstance("Surename");
		Surename secondSut = Surename.newInstance("Surename");

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}
}