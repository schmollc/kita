package com.kita.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.attributes.Forename.ForenameNullObject;

/**
 * Wissen und nichts tun ist wie nicht wissen.
 *  - Dalai Lama
 *
 * @since 17.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ForenameTest {

	@Test
	public void testIsSerializable() {
		String dummyString = "";
		Forename sut = Forename.newInstance(dummyString);

		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Class is not Serializable!", condition);
	}

	@Test
	public void testCreateInstance() {
		Forename sut = Forename.newInstance();

		assertNotNull("Not a valid instance!", sut);

		boolean condition = sut.getClass() == ForenameNullObject.class;

		assertTrue("Instance is not correct!", condition);
	}

	@Test
	public void testCreateInstance_ForParameter() {
		Forename forename = Forename.newInstance("Forename");

		assertNotNull(forename);

		boolean condition = forename.getClass() == Forename.class;
		assertTrue("Instance not correct!", condition);
		//Oder
		String toString = forename.toString();
		assertEquals("Forename", toString);
	}

	@Test
	public void testCreateInstance_ForNullValue() {
		Forename sut = Forename.newInstance(null);

		assertNotNull(sut);

		boolean actual = sut.getClass() == ForenameNullObject.class;

		assertTrue("Instance not correct!", actual);
	}

	@Test
	public void testCreateInstance_ForEmptyValue() {
		Forename sut = Forename.newInstance("");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = sut.getClass() == ForenameNullObject.class;

		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testCreateInstance_ForBlankValue() {
		Forename sut = Forename.newInstance("   ");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = sut.getClass() == ForenameNullObject.class;

		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testSortByForename() {
		Forename name1 = Forename.newInstance("Justus");
		Forename name2 = Forename.newInstance("Peter");

		int actual = Forename.sortByForename(name1, name2);

		int expected = -6;
		assertEquals("[position] not correct!", expected, actual);
	}

	@Test
	public void testSortByForename_ForSameNames() {
		Forename name1 = Forename.newInstance("Justus");
		Forename name2 = Forename.newInstance("justus");

		int actual = Forename.sortByForename(name1, name2);

		int expected = 0;
		assertEquals("[position] not correct!", expected, actual);
	}

	@Test
	public void testToString() {
		String expectedcondition = "Marty";
		Forename sut = Forename.newInstance(expectedcondition);

		String actualcondition = sut.toString();

		assertEquals("String representation is not correct!", expectedcondition, actualcondition);
	}

	@Test
	public void testToString_ForEmptyValue() {
		String expected = "";
		Forename sut = Forename.newInstance(expected);

		String actual = sut.toString();

		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testToString_ForBlankValue() {
		Forename sut = Forename.newInstance("   ");

		String actual = sut.toString();

		assertEquals("String representation is not correct!", "", actual);
	}

	@Test
	public void testToString_ForNullValue() {
		Forename sut = Forename.newInstance(null);

		String actual = sut.toString();

		assertEquals("String representation is not correct!", "", actual);
	}

	@Test
	public void testSamenessOfTwoNullForenames() {
		Forename sut = Forename.newInstance(null);
		Forename otherNullForename = Forename.newInstance(null);

		assertSame("Two ForenameNullObjects are not the same!", sut, otherNullForename);
	}

	@Test
	public void testIsEmpty_ForValueEmpty() {
		Forename sut = Forename.newInstance("");

		boolean condition = sut.isEmpty();

		assertTrue("[condition] for isEmpty is not correct!", condition);
	}

	@Test
	public void testIsEmpty_ForValueFilled() {
		String comment = "Justus";
		Forename sut = Forename.newInstance(comment);

		boolean condition = sut.isEmpty();

		assertFalse("[condition] for isEmpty is not correct!", condition);
	}

	@Test
	public void testIsEmpty_ForValueFilledWithBlank() {
		Forename sut = Forename.newInstance("    ");

		boolean condition = sut.isEmpty();

		assertTrue("[condition] for isEmpty is not correct!", condition);
	}

	@Test
	public void testIsEmpty_ForValueNull() {
		Forename sut = Forename.newInstance(null);

		boolean condition = sut.isEmpty();

		assertTrue("[condition] for isEmpty is not correct!", condition);
	}

	@Test
	public void testHashCode() {
		Forename sut = Forename.newInstance("Forename");

		int hashCode = sut.hashCode();

		assertEquals(531705222, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEquals_WithMyself() {
		Forename sut = Forename.newInstance("Name");

		boolean condition = sut.equals(sut);

		assertTrue(condition);
	}

	@Test
	public void testEquals_WithNull() {
		Forename sut = Forename.newInstance("Name");

		boolean condition = sut.equals(null);

		assertFalse(condition);
	}

	@Test
	public void testEquals_WithNotCompatibleClass() {
		Forename sut = Forename.newInstance("Name");

		@SuppressWarnings("unlikely-arg-type")
		boolean condition = sut.equals(new String());

		assertFalse(condition);
	}

	@Test
	public void testEquals_WithDiffrentValues() {
		Forename sut = Forename.newInstance("Name1");

		Forename secondSut = Forename.newInstance("Name2");

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);

	}

	@Test
	public void testEquals_WithSameValues() {
		Forename sut = Forename.newInstance("Name");

		Forename secondSut = Forename.newInstance("Name");

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}

	@Test
	public void testEquals_WithValueIsNull() {
		Forename sut = Forename.newInstance("dummy");
		sut.value = null;

		Forename secondSut = Forename.newInstance("dummy");

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);

	}

	@Test
	public void testEquals_WithBothValuesAreNull() {
		Forename sut = Forename.newInstance("dummy");
		sut.value = null;

		Forename secondSut = Forename.newInstance("dummy");
		secondSut.value = null;

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}
}