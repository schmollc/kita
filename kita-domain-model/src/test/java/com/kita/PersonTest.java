package com.kita;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.attributes.Forename;

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
	public void testToString() {
		Person sut = Person.newInstance();
		//		sut.setSurename(Surename.newInstance("Jonas"));
		String expected = "Justus";
		sut.setForename(Forename.newInstance(expected));

		String actual = sut.toString();

		assertEquals("[toString] not correct!", expected, actual);
	}

}