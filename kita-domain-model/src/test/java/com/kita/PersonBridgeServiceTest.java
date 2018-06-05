package com.kita;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Matchers;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.kita.attributes.Email;
import com.kita.orm.Validation;

import static org.mockito.Mockito.*;

/**
 * @since 04.06.2018
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
public class PersonBridgeServiceTest {

	@Spy
	private PersonBridgeService sut;

	@Test
	public void testDoesEmailExist_ForEmailDoesNotExist() {
		List<Person> somePersons = new ArrayList<>();
		somePersons.add(new PersonBuilder().withEmail(Email.newInstance("Justus.Jonas@rockyBeach.com")).build());
		somePersons.add(new PersonBuilder().withEmail(Email.newInstance("Peter.Shaw@rockyBeach.com")).build());

		doReturn(somePersons).when(sut).all();

		Email email = Email.newInstance("Bob.Andrews@rockyBeach.com");

		boolean condition = sut.doesEmailExist(email);

		assertFalse("[doesEmailExist] not correct!", condition);
	}

	@Test
	public void testDoesEmailExist_ForEmailExistingOneTime() {
		List<Person> somePersons = new ArrayList<>();
		somePersons.add(new PersonBuilder().withEmail(Email.newInstance("Justus.Jonas@rockyBeach.com")).build());
		somePersons.add(new PersonBuilder().withEmail(Email.newInstance("Peter.Shaw@rockyBeach.com")).build());
		somePersons.add(new PersonBuilder().withEmail(Email.newInstance("Bob.Andrews@rockyBeach.com")).build());

		doReturn(somePersons).when(sut).all();

		Email email = Email.newInstance("Bob.Andrews@rockyBeach.com");

		boolean condition = sut.doesEmailExist(email);

		assertTrue("[doesEmailExist] not correct!", condition);
	}

	@Test
	public void testPersistPerson_ForEmailExist() {
		doReturn(true).when(sut).doesEmailExist(Matchers.any());
		Person dummyPerson = new PersonBuilder().build();

		Validation validation = sut.persistPerson(dummyPerson);
		boolean condition = validation.success();

		verify(sut, never()).getGateway();
		assertFalse("[persistPerson] not correct!", condition);

	}

	@Test
	public void testPersistPerson_ForEmailNotExist() {
		doReturn(false).when(sut).doesEmailExist(Matchers.any());
		Person dummyPerson = new PersonBuilder().build();

		Validation validation = sut.persistPerson(dummyPerson);
		boolean condition = validation.success();

		verify(sut).getGateway();
		assertTrue("[persistPerson] not correct!", condition);
	}

}