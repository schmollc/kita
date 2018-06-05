package com.kita.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
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
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.kita.Person;
import com.kita.PersonBuilder;
import com.kita.attributes.Email;
import com.kita.orm.Validation;

/**
 * Auch aus Steinen, die einem in den Weg gelegt werden, kann man Schönes bauen.
 *  - Johann Wolfgang von Goethe
 *   
 * @since 04.06.2018
 * 
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {
	private static final String EMAIL_JUSTUS = "Justus.Jonas@RockyBeach.com";

	private static final String EMAIL_PETER = "Peter.Shaw@RockyBeach.com";

	private static final String EMAIL_BOB = "Bob.Andrews@RockyBeach.com";

	@Spy
	private PersonService sut;


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

}