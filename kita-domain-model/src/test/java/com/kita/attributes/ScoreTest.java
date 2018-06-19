package com.kita.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.attributes.Score.ScoreNullObject;

/**
 * Gestern war ich klug und wollte die Welt ändern.
 * Heute bin ich weise und ändere mich selbst.
 *  - Anonymous
 *
 *  @since 15.06.2018
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScoreTest {

	@Test
	public void testIsSerializable() {
		String dummyString = "";
		Score sut = Score.newInstance(dummyString);

		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Class is not Serializable!", condition);
	}

	@Test
	public void testCreateInstance() {
		Score sut = Score.newInstance();

		assertNotNull("Not a valid instance!", sut);

		boolean condition = sut.getClass() == ScoreNullObject.class;

		assertTrue("Instance is not correct!", condition);
	}

	@Test
	public void testCreateInstance_ForParameter() {
		Score forename = Score.newInstance("Score");

		assertNotNull(forename);

		boolean condition = forename.getClass() == Score.class;
		assertTrue("Instance not correct!", condition);
		//Oder
		String toString = forename.toString();
		assertEquals("Score", toString);
	}

	@Test
	public void testCreateInstance_ForNullValue() {
		Score sut = Score.newInstance(null);

		assertNotNull(sut);

		boolean actual = sut.getClass() == ScoreNullObject.class;

		assertTrue("Instance not correct!", actual);
	}

	@Test
	public void testCreateInstance_ForEmptyValue() {
		Score sut = Score.newInstance("");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = sut.getClass() == ScoreNullObject.class;

		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testCreateInstance_ForBlankValue() {
		Score sut = Score.newInstance("   ");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = sut.getClass() == ScoreNullObject.class;

		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testAddScore() {
		Score sut = Score.newInstance("2");

		sut.addScore();

		String expected = "3";

		assertEquals("[addScore] not correct!", expected, sut.toString());

	}

	@Test
	public void testSortByScore() {
		Score name1 = Score.newInstance("1");
		Score name2 = Score.newInstance("2");

		int actual = Score.sortByScore(name1, name2);

		int expected = -1;
		assertEquals("[position] not correct!", expected, actual);
	}

	@Test
	public void testSortByScore_ForSameNames() {
		Score name1 = Score.newInstance("4");
		Score name2 = Score.newInstance("4");

		int actual = Score.sortByScore(name1, name2);

		int expected = 0;
		assertEquals("[position] not correct!", expected, actual);
	}

	@Test
	public void testToString() {
		String expectedc = "9";
		Score sut = Score.newInstance(expectedc);

		String actual = sut.toString();

		assertEquals("String representation is not correct!", expectedc, actual);
	}

	@Test
	public void testToString_ForEmptyValue() {
		String expected = "";
		Score sut = Score.newInstance(expected);

		String actual = sut.toString();

		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testToString_ForBlankValue() {
		Score sut = Score.newInstance("   ");

		String actual = sut.toString();

		assertEquals("String representation is not correct!", "", actual);
	}

	@Test
	public void testToString_ForNullValue() {
		Score sut = Score.newInstance(null);

		String actual = sut.toString();

		assertEquals("String representation is not correct!", "", actual);
	}

	@Test
	public void testSamenessOfTwoNullScores() {
		Score sut = Score.newInstance(null);
		Score otherNullScore = Score.newInstance(null);

		assertSame("Two ScoreNullObjects are not the same!", sut, otherNullScore);
	}

	@Test
	public void testIsEmpty_ForValueEmpty() {
		Score sut = Score.newInstance("");

		boolean condition = sut.isEmpty();

		assertTrue("[condition] for isEmpty is not correct!", condition);
	}

	@Test
	public void testIsEmpty_ForValueFilled() {
		String comment = "Justus";
		Score sut = Score.newInstance(comment);

		boolean condition = sut.isEmpty();

		assertFalse("[condition] for isEmpty is not correct!", condition);
	}

	@Test
	public void testIsEmpty_ForValueFilledWithBlank() {
		Score sut = Score.newInstance("    ");

		boolean condition = sut.isEmpty();

		assertTrue("[condition] for isEmpty is not correct!", condition);
	}

	@Test
	public void testIsEmpty_ForValueNull() {
		Score sut = Score.newInstance(null);

		boolean condition = sut.isEmpty();

		assertTrue("[condition] for isEmpty is not correct!", condition);
	}

	@Test
	public void testHashCode() {
		Score sut = Score.newInstance("1");

		int hashCode = sut.hashCode();

		assertEquals(80, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEquals_WithMyself() {
		Score sut = Score.newInstance("1");

		boolean condition = sut.equals(sut);

		assertTrue(condition);
	}

	@Test
	public void testEquals_WithNull() {
		Score sut = Score.newInstance("1");

		boolean condition = sut.equals(null);

		assertFalse(condition);
	}

	@Test
	public void testEquals_WithNotCompatibleClass() {
		Score sut = Score.newInstance("1");

		@SuppressWarnings("unlikely-arg-type")
		boolean condition = sut.equals(new String());

		assertFalse(condition);
	}

	@Test
	public void testEquals_WithDiffrentValues() {
		Score sut = Score.newInstance("1");

		Score secondSut = Score.newInstance("2");

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);

	}

	@Test
	public void testEquals_WithSameValues() {
		Score sut = Score.newInstance("1");

		Score secondSut = Score.newInstance("1");

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}

	@Test
	public void testEquals_WithValueIsNull() {
		Score sut = Score.newInstance("dummy");
		sut.value = null;

		Score secondSut = Score.newInstance("dummy");

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);

	}

	@Test
	public void testEquals_WithBothValuesAreNull() {
		Score sut = Score.newInstance("dummy");
		sut.value = null;

		Score secondSut = Score.newInstance("dummy");
		secondSut.value = null;

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}
}
