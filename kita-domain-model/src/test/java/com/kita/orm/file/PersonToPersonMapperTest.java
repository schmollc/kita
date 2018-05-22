package com.kita.orm.file;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.kita.Person;
import com.kita.attributes.Forename;
import com.kita.attributes.Surename;

/**
 * Things I hate
 * 1. No Tests
 * 2. Lists
 * 3. Irony
 * 4. Lists
 * 5. Repetition
 * F. Inconsisty
 *  -  (Un)known TDD Developer
 *
 * @since 18.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonToPersonMapperTest {

	private PersonToPersonMapper sut = PersonToPersonMapper.newInstance();

	private Person source = Person.newInstance();
	private Person target = Person.newInstance();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test
	public void testMapPersonToPerson_ForSourceIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[source] must not be 'null'!");

		sut.mapPersonToPerson(null, target);
	}

	@Test
	public void testMapPersonToPerson_ForTargetIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[target] must not be 'null'!");

		sut.mapPersonToPerson(source, null);
	}

	@Test
	public void testMapPersonToPerson_Surename() {
		Surename expected = Surename.newInstance("Justus");
		source.setSurename(expected);

		sut.mapPersonToPerson(source, target);

		Surename actual = target.getSurename();
		assertEquals("Mapping of [surename] is not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToPerson_Forename() {
		Forename expected = Forename.newInstance("Jonas");
		source.setForename(expected);

		sut.mapPersonToPerson(source, target);

		Forename actual = target.getForename();
		assertEquals("Mapping of [forename] is not correct!", expected, actual);
	}
}