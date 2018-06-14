package com.kita.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.attributes.Teamname.TeamnameNullObject;

/**
 * The time to write good code is at the time you are writing it.
 *  - Daniel Read
 *
 * @since 14.06.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TeamnameTest {

	@Test
	public void testIsSerializable() {
		String dummyString = "";
		Teamname sut = Teamname.newInstance(dummyString);

		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Class not Serializable!", condition);
	}

	@Test
	public void testNewInstance() {
		Teamname sut = Teamname.newInstance();

		assertNotNull("Not a valid instance!", sut);

		boolean condition = sut.getClass() == TeamnameNullObject.class;

		assertTrue("Instance not correct!", condition);
	}

	@Test
	public void testNewInstance_WithParameter() {
		Teamname sut = Teamname.newInstance("Teamname");

		assertNotNull(sut);

		boolean condition = sut.getClass() == Teamname.class;

		assertTrue("Instance not correct!", condition);
	}

	@Test
	public void testIsEmpty_WithValueEmpty() {
		Teamname sut = Teamname.newInstance("");

		boolean condition = sut.isEmpty();

		assertTrue("[condition] for isEmpty not correct!", condition);
	}

	@Test
	public void testIsEmpty_WithValueFilled() {
		String comment = "Marathon Düsseldorf";
		Teamname sut = Teamname.newInstance(comment);

		boolean condition = sut.isEmpty();

		assertFalse("[condition] for isEmpty not correct!", condition);
	}

	@Test
	public void testIsEmpty_WithValueFilledWithBlank() {
		Teamname sut = Teamname.newInstance("    ");

		boolean condition = sut.isEmpty();

		assertTrue("[condition] for isEmpty not correct!", condition);
	}

	@Test
	public void testIsEmpty_WithValueNull() {
		Teamname sut = Teamname.newInstance(null);

		boolean condition = sut.isEmpty();

		assertTrue("[condition] for isEmpty not correct!", condition);
	}

	@Test
	public void testNewInstance_WithNullValue() {
		Teamname sut = Teamname.newInstance(null);

		assertNotNull(sut);

		boolean actual = sut.getClass() == TeamnameNullObject.class;

		assertTrue("Instance not correct!", actual);
	}

	@Test
	public void testNewInstance_WithEmptyValue() {
		Teamname sut = Teamname.newInstance("");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = sut.getClass() == TeamnameNullObject.class;

		assertTrue("Instance not correct!", actual);
	}

	@Test
	public void testNewInstance_WithBlankValue() {
		Teamname sut = Teamname.newInstance("   ");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = sut.getClass() == TeamnameNullObject.class;

		assertTrue("Instance not correct!", actual);
	}

	@Test
	public void testSamenessOfTwoNullTeamname() {
		Teamname sut = Teamname.newInstance(null);
		Teamname otherNullTeamname = Teamname.newInstance(null);

		assertSame("Two TeamnameNullObjects are not the same!", sut, otherNullTeamname);
	}

	@Test
	public void testSortByName() {
		Teamname name1 = Teamname.newInstance("Wonder Womens");
		Teamname name2 = Teamname.newInstance("Avengers");

		int position = Teamname.sortByName(name1, name2);

		assertEquals("[sortByName] not correct!", 22, position);
	}

	@Test
	public void testSortByName_WithSameNames() {
		Teamname name1 = Teamname.newInstance("Torjaeger");
		Teamname name2 = Teamname.newInstance("Torjaeger");

		int position = Teamname.sortByName(name1, name2);

		assertEquals("[sortByName] not correct!", 0, position);
	}

	@Test
	public void testToString() {
		String expected = "MetroGroup Marathon Düsseldorf";

		Teamname sut = Teamname.newInstance(expected);

		String actual = sut.toString();

		assertEquals("toString() not correct!", expected, actual);
	}

	@Test
	public void testToString_WithEmptyValue() {
		String expected = "";
		Teamname sut = Teamname.newInstance(expected);

		String actual = sut.toString();

		assertEquals("String representation not correct!", expected, actual);
	}

	@Test
	public void testToString_WithBlankValue() {
		String expected = "";
		Teamname sut = Teamname.newInstance("   ");

		String actual = sut.toString();

		assertEquals("String representation not correct!", expected, actual);
	}

	@Test
	public void testToString_WithNullValue() {
		String expected = "";
		Teamname sut = Teamname.newInstance(null);

		String actual = sut.toString();

		assertEquals("String representation not correct!", expected, actual);
	}

	@Test
	public void testHashCode() {
		Teamname sut = Teamname.newInstance("Name");

		int hashCode = sut.hashCode();

		assertEquals(2420426, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);

	}

	@Test
	public void testEquals_WithMyself() {
		Teamname sut = Teamname.newInstance("Name");

		boolean condition = sut.equals(sut);

		assertTrue(condition);
	}

	@Test
	public void testEquals_WithNull() {
		Teamname sut = Teamname.newInstance("Name");

		boolean condition = sut.equals(null);

		assertFalse(condition);
	}

	@Test
	public void testEquals_WithNotCompatibleClass() {
		Teamname sut = Teamname.newInstance("Name");

		@SuppressWarnings("unlikely-arg-type")
		boolean condition = sut.equals(new String());

		assertFalse(condition);
	}

	@Test
	public void testEquals_WithDiffrentValues() {
		Teamname sut = Teamname.newInstance("Name1");

		Teamname secondSut = Teamname.newInstance("Name2");

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);

	}

	@Test
	public void testEquals_WithSameValues() {
		Teamname sut = Teamname.newInstance("Name");

		Teamname secondSut = Teamname.newInstance("Name");

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}

	@Test
	public void testEquals_WithValueIsNull() {
		Teamname sut = Teamname.newInstance("dummy");
		sut.value = null;

		Teamname secondSut = Teamname.newInstance("dummy");

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);

	}

	@Test
	public void testEquals_WithBothValuesAreNull() {
		Teamname sut = Teamname.newInstance("dummy");
		sut.value = null;

		Teamname secondSut = Teamname.newInstance("dummy");
		secondSut.value = null;

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}
}