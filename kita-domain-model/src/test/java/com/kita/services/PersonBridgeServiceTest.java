package com.kita.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import static org.mockito.Matchers.*;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.kita.Person;
import com.kita.PersonBuilder;
import com.kita.attributes.Email;
import com.kita.attributes.Forename;
import com.kita.orm.Validation;

/**
 * @since 04.06.2018
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
public class PersonBridgeServiceTest {
	private static final String EMAIL_JUSTUS = "Justus.Jonas@RockyBeach.com";

	private static final String EMAIL_PETER = "Peter.Shaw@RockyBeach.com";

	private static final String EMAIL_BOB = "Bob.Andrews@RockyBeach.com";

	@Spy
	private PersonBridgeService sut;


	@Test
	public void testIsSerializable() {
		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Class not Serializable!", condition);
	}

	
	@Test
	public void testDoesEmailExist_ForEmailDoesNotExist() {
		List<Person> somePersons = new ArrayList<>();
		somePersons.add(new PersonBuilder().withEmail(Email.newInstance(EMAIL_JUSTUS)).build());
		somePersons.add(new PersonBuilder().withEmail(Email.newInstance(EMAIL_PETER)).build());

		doReturn(somePersons).when(sut).all();

		Person bobAndrews  = new PersonBuilder().withEmail(Email.newInstance(EMAIL_BOB)).build();
		
		boolean condition = sut.doesEmailExist(bobAndrews);

		assertFalse("[doesEmailExist] not correct!", condition);
	}

	@Test
	public void testDoesEmailExist_ForEmailExistingForPerson() {
		List<Person> somePersons = new ArrayList<>();
		somePersons.add(new PersonBuilder().withEmail(Email.newInstance(EMAIL_JUSTUS)).build());
		somePersons.add(new PersonBuilder().withEmail(Email.newInstance(EMAIL_PETER)).build());
		
		Person bobAndrews = new PersonBuilder().withEmail(Email.newInstance(EMAIL_BOB)).build();
		somePersons.add(bobAndrews);

		doReturn(somePersons).when(sut).all();

		boolean condition = sut.doesEmailExist(bobAndrews);

		assertFalse("[doesEmailExist] not correct!", condition);
	}

	@Test
	public void testDoesEmailExist_ForEmailExistingForAnotherPerson() {
		List<Person> somePersons = new ArrayList<>();
		somePersons.add(new PersonBuilder().withEmail(Email.newInstance(EMAIL_JUSTUS)).build());
		somePersons.add(new PersonBuilder().withEmail(Email.newInstance(EMAIL_PETER)).build());
		Person bobAndrews = new PersonBuilder().withEmail(Email.newInstance(EMAIL_BOB)).build();
		somePersons.add(bobAndrews);

		doReturn(somePersons).when(sut).all();

		Person notBobAndrews  = new PersonBuilder().withEmail(Email.newInstance(EMAIL_BOB)).build();

		boolean condition = sut.doesEmailExist(notBobAndrews);

		assertTrue("[doesEmailExist] not correct!", condition);
	}

	@Test
	public void testPersistPerson_ForEmailExist() {
		doReturn(true).when(sut).doesEmailExist(any(Person.class));
		Person dummyPerson = new PersonBuilder().build();

		Validation validation = sut.persistPerson(dummyPerson);
		boolean condition = validation.success();

		verify(sut, never()).getGateway();
		assertFalse("[persistPerson] not correct!", condition);

	}

	@Test
	public void testPersistPerson_ForEmailNotExist() {
		doReturn(false).when(sut).doesEmailExist(any(Person.class));
		
		Person dummyPerson = new PersonBuilder().build();

		Validation validation = sut.persistPerson(dummyPerson);
		boolean condition = validation.success();

		verify(sut).getGateway();
		assertTrue("[persistPerson] not correct!", condition);
	}

	private List<Person> listWithPersons() {
		List<Person> somePersons = new ArrayList<Person>();
		PersonBuilder builder = new PersonBuilder();

		Person personOne = builder.withForename(Forename.newInstance("Dirk")).build();
		somePersons.add(personOne);

		Person personTwo = builder.withForename(Forename.newInstance("Christian")).build();
		somePersons.add(personTwo);

		Person personThree = builder.withForename(Forename.newInstance("Peter")).withEmail(EMAIL_PETER).build();
		somePersons.add(personThree);

		Person personFour = builder.withForename(Forename.newInstance("Justus")).withEmail(EMAIL_JUSTUS).build();
		somePersons.add(personFour);

		builder = new PersonBuilder();

		Person personFive = builder.withForename(Forename.newInstance("Michi")).build();
		somePersons.add(personFive);

		Person personSix = builder.withForename(Forename.newInstance("Smudo")).build();
		somePersons.add(personSix);

		Person personSeven = builder.withForename(Forename.newInstance("Andy")).build();
		somePersons.add(personSeven);

		Person personEight = builder.withForename(Forename.newInstance("Thomas")).build();
		somePersons.add(personEight);

		return somePersons;
	}

}