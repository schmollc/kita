package com.kita.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.attributes.EventDay.EventDayNullObject;

/**
 * Niemand, der seine Arbeit tatsächlich versteht würde sich einen Experten nennen.
 *  - Henry Ford
 *
 * @since   13.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventDayTest {

	@Test
	public void testIsSerializable() {
		EventDay sut = EventDay.today();

		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Class not Serializable!", condition);
	}

	@Test
	public void testToday() {
		EventDay sut = EventDay.today();
		boolean condition = sut.isToday();

		assertTrue("Default day of event not today!", condition);
	}

	@Test
	public void testNewInstance() {
		LocalDate expected = getDefinedLocalDateInThePast();
		EventDay sut = EventDay.newInstance(expected);

		LocalDate actual = sut.getValue();
		assertEquals("Day of event does not match!", expected, actual);
	}

	private LocalDate getDefinedLocalDateInThePast() {
		return LocalDate.of(2001, Month.NOVEMBER, 21);
	}

	@Test
	public void testNewInstance_WithNullValue() {
		EventDay sut = EventDay.newInstance(null);

		assertNotNull(sut);

		boolean condition = sut.getClass() == EventDayNullObject.class;

		assertTrue("Instance not correct!", condition);

	}

	@Test
	public void testIsInThePast() {
		LocalDate dateOfEventInThePast = getDefinedLocalDateInThePast();
		EventDay sut = EventDay.newInstance(dateOfEventInThePast);

		boolean condition = sut.isInThePast();
		assertTrue("Day of event not in the past!", condition);
	}

	@Test
	public void testIsToday() {
		LocalDate dateOfEventToday = LocalDate.now();
		EventDay sut = EventDay.newInstance(dateOfEventToday);

		boolean condition = sut.isToday();
		assertTrue("Day of event not today!", condition);
	}

	@Test
	public void testIsInTheFuture() {
		LocalDate dateOfEventOneWeekInTheFuture = LocalDate.now().plusDays(7);
		EventDay sut = EventDay.newInstance(dateOfEventOneWeekInTheFuture);

		boolean condition = sut.isInTheFuture();
		assertTrue("Day of event not in the future!", condition);
	}

	@Test
	public void testIsEmpty_WithValueFilled() {
		LocalDate dateOfEvent = getDefinedLocalDateInThePast();
		EventDay sut = EventDay.newInstance(dateOfEvent);

		boolean condition = sut.isEmpty();

		assertFalse("[condition] for isEmpty not correct!", condition);
	}

	@Test
	public void testIsEmpty_WithValueNull() {
		EventDay sut = EventDay.newInstance(null);

		boolean condition = sut.isEmpty();

		assertTrue("[condition] for isEmpty not correct!", condition);
	}

	@Test
	public void testSortByDay() {
		EventDay day1 = EventDay.newInstance(LocalDate.now());
		EventDay day2 = EventDay.newInstance(LocalDate.of(2011, Month.APRIL, 23));

		int actual = EventDay.sortByDay(day1, day2);
		int expected = 7;

		assertEquals("[sortByDay] not correct!", expected, actual);
	}

	@Test
	public void testSortByDay_WithSameDates() {
		LocalDate now = LocalDate.now();
		EventDay day1 = EventDay.newInstance(now);
		EventDay day2 = EventDay.newInstance(now);

		int actual = EventDay.sortByDay(day1, day2);
		int expected = 0;

		assertEquals("[sortByDay] not correct!", expected, actual);
	}

	@Test
	public void testToString() {
		LocalDate dateOfEvent = getDefinedLocalDateInThePast();
		EventDay sut = EventDay.newInstance(dateOfEvent);
		String expected = "2001-11-21";

		String actual = sut.toString();

		assertEquals("toString() not correct!", expected, actual);
	}

	@Test
	public void testToString_WithNullValue() {
		EventDay sut = EventDay.newInstance(null);

		String actual = sut.toString();

		String expected = "";
		assertEquals("String representation not correct!", expected, actual);
	}

	@Test
	public void testHashCode() {
		EventDay sut = EventDay.newInstance(getDefinedLocalDateInThePast());

		int hashCode = sut.hashCode();

		assertEquals(4098804, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEquals_WithMyself() {
		EventDay sut = EventDay.newInstance(LocalDate.now());

		boolean condition = sut.equals(sut);

		assertTrue(condition);
	}

	@Test
	public void testEquals_WithNull() {
		EventDay sut = EventDay.newInstance(LocalDate.now());

		boolean condition = sut.equals(null);

		assertFalse(condition);
	}

	@Test
	public void testEquals_WithNotCompatibleClass() {
		EventDay sut = EventDay.newInstance(LocalDate.now());

		@SuppressWarnings("unlikely-arg-type")
		boolean condition = sut.equals(new String());

		assertFalse(condition);
	}

	@Test
	public void testEquals_WithValueIsNull() {
		EventDay sut = EventDay.newInstance(LocalDate.now());
		sut.value = null;
		EventDay secondSut = EventDay.newInstance(LocalDate.now());

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);
	}

	@Test
	public void testEquals_WithBothValuesAreNull() {
		EventDay sut = EventDay.newInstance(LocalDate.now());
		sut.value = null;
		EventDay secondSut = EventDay.newInstance(LocalDate.now());
		secondSut.value = null;

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}

	@Test
	public void testEquals_WithTwoDiffrentValues() {
		EventDay sut = EventDay.newInstance(LocalDate.now());
		EventDay secondSut = EventDay.newInstance(getDefinedLocalDateInThePast());

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);
	}

	@Test
	public void testEquals_WithSameValues() {
		EventDay sut = EventDay.newInstance(LocalDate.now());
		EventDay secondSut = EventDay.newInstance(LocalDate.now());

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}
}