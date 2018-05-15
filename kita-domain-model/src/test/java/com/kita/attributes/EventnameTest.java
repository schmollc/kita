package com.kita.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.attributes.Eventname.EventnameNullObject;

/**
 * Der Mann, der den Berg abtrug, war derselbe,
 * der anfing, kleine Steine wegzutragen.
 *  - Konfuzius
 *
 * @since 13.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventnameTest {

	@Test
	public void testIsSerializable() {
		String dummyString = "";
		Eventname sut = Eventname.newInstance(dummyString);

		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Class not Serializable!", condition);
	}

	@Test
	public void testNewInstance() {
		Eventname sut = Eventname.newInstance();

		assertNotNull("Not a valid instance!", sut);

		boolean condition = sut.getClass() == EventnameNullObject.class;

		assertTrue("Instance not correct!", condition);
	}

	@Test
	public void testNewInstance_WithParameter() {
		Eventname sut = Eventname.newInstance("eventname");

		assertNotNull(sut);

		boolean condition = sut.getClass() == Eventname.class;

		assertTrue("Instance not correct!", condition);
	}

	@Test
	public void testIsEmpty_WithValueEmpty() {
		Eventname sut = Eventname.newInstance("");

		boolean condition = sut.isEmpty();

		assertTrue("[condition] for isEmpty not correct!", condition);
	}

	@Test
	public void testIsEmpty_WithValueFilled() {
		String comment = "Marathon Düsseldorf";
		Eventname sut = Eventname.newInstance(comment);

		boolean condition = sut.isEmpty();

		assertFalse("[condition] for isEmpty not correct!", condition);
	}

	@Test
	public void testIsEmpty_WithValueFilledWithBlank() {
		Eventname sut = Eventname.newInstance("    ");

		boolean condition = sut.isEmpty();

		assertTrue("[condition] for isEmpty not correct!", condition);
	}

	@Test
	public void testIsEmpty_WithValueNull() {
		Eventname sut = Eventname.newInstance(null);

		boolean condition = sut.isEmpty();

		assertTrue("[condition] for isEmpty not correct!", condition);
	}

	@Test
	public void testNewInstance_WithNullValue() {
		Eventname sut = Eventname.newInstance(null);

		assertNotNull(sut);

		boolean actual = sut.getClass() == EventnameNullObject.class;

		assertTrue("Instance not correct!", actual);
	}

	@Test
	public void testNewInstance_WithEmptyValue() {
		Eventname sut = Eventname.newInstance("");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = sut.getClass() == EventnameNullObject.class;

		assertTrue("Instance not correct!", actual);
	}

	@Test
	public void testNewInstance_WithBlankValue() {
		Eventname sut = Eventname.newInstance("   ");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = sut.getClass() == EventnameNullObject.class;

		assertTrue("Instance not correct!", actual);
	}

	@Test
	public void testSamenessOfTwoNullEventname() {
		Eventname sut = Eventname.newInstance(null);
		Eventname otherNullEventname = Eventname.newInstance(null);

		assertSame("Two EventnameNullObjects are not the same!", sut, otherNullEventname);
	}

	@Test
	public void testToString() {
		String expected = "MetroGroup Marathon Düsseldorf";

		Eventname sut = Eventname.newInstance(expected);

		String actual = sut.toString();

		assertEquals("toString() not correct!", expected, actual);
	}

	@Test
	public void testToString_WithEmptyValue() {
		String expected = "";
		Eventname sut = Eventname.newInstance(expected);

		String actual = sut.toString();

		assertEquals("String representation not correct!", expected, actual);
	}

	@Test
	public void testToString_WithBlankValue() {
		String expected = "";
		Eventname sut = Eventname.newInstance("   ");

		String actual = sut.toString();

		assertEquals("String representation not correct!", expected, actual);
	}

	@Test
	public void testToString_WithNullValue() {
		String expected = "";
		Eventname sut = Eventname.newInstance(null);

		String actual = sut.toString();

		assertEquals("String representation not correct!", expected, actual);
	}

	@Test
	public void testHashCode() {
		Eventname sut = Eventname.newInstance("Name");

		int hashCode = sut.hashCode();

		assertEquals(2420426, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);

	}

	@Test
	public void testEquals_WithMyself() {
		Eventname sut = Eventname.newInstance("Name");

		boolean condition = sut.equals(sut);

		assertTrue(condition);
	}

	@Test
	public void testEquals_WithNull() {
		Eventname sut = Eventname.newInstance("Name");

		boolean condition = sut.equals(null);

		assertFalse(condition);
	}

	@Test
	public void testEquals_WithNotCompatibleClass() {
		Eventname sut = Eventname.newInstance("Name");

		@SuppressWarnings("unlikely-arg-type")
		boolean condition = sut.equals(new String());

		assertFalse(condition);
	}

	@Test
	public void testEquals_WithDiffrentValues() {
		Eventname sut = Eventname.newInstance("Name1");

		Eventname secondSut = Eventname.newInstance("Name2");

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);

	}

	@Test
	public void testEquals_WithSameValues() {
		Eventname sut = Eventname.newInstance("Name");

		Eventname secondSut = Eventname.newInstance("Name");

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}

	@Test
	public void testEquals_WithValueIsNull() {
		Eventname sut = Eventname.newInstance("dummy");
		sut.value = null;

		Eventname secondSut = Eventname.newInstance("dummy");

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);

	}

	@Test
	public void testEquals_WithBothValuesAreNull() {
		Eventname sut = Eventname.newInstance("dummy");
		sut.value = null;

		Eventname secondSut = Eventname.newInstance("dummy");
		secondSut.value = null;

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}

	@Test
	public void testSortByName() {
		Eventname name1 = Eventname.newInstance("Kicker Turnier");
		Eventname name2 = Eventname.newInstance("Turnier Kicker");

		int position = Eventname.sortByName(name1, name2);

		assertEquals("[sortByName] not correct!", -9, position);
	}

	@Test
	public void testSortByName_WithSameNames() {
		Eventname name1 = Eventname.newInstance("Kicher Turnier");
		Eventname name2 = Eventname.newInstance("Kicher Turnier");

		int position = Eventname.sortByName(name1, name2);

		assertEquals("[sortByName] not correct!", 0, position);
	}

}