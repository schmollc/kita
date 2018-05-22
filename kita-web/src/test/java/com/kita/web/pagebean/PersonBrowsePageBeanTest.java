package com.kita.web.pagebean;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.kita.Person;
import com.kita.web.bridge.PersonBridge;

import static org.mockito.Mockito.*;

/**
 * Quality is never an accident; it is always the result of intelligent effort.
 *  - John Ruskin
 *
 * @since 22.05.2018
 *
 */
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonBrowsePageBeanTest {
	@InjectMocks
	@Spy
	private PersonBrowsePageBean sut;

	@Mock
	private PersonBridge personBridge;

	//
	//	@Before
	//	public void setUp() {
	//		doNothing().when(sut).showMessage(any(Severity.class), anyString(), anyString());
	//		doNothing().when(sut).showDialog(any(Severity.class), anyString(), anyString());
	//	}
	//
	@Test
	public void testGetNumberOfResults_ForEmptyResultList() {
		Integer actual = sut.getNumberOfResults();

		assertEquals("Numboer of results for empty result list not correct!", Integer.valueOf(0), actual);
	}

	@Test
	public void testGetNumberOfResults_ForFilledResultList() {
		doReturn(createResultList(size(5))).when(personBridge).all();
		sut.refreshPersons();

		Integer numberOfResults = sut.getNumberOfResults();

		assertEquals(Integer.valueOf(5), numberOfResults);
	}

	private Integer size(Integer aValue) {
		return aValue;
	}

	private List<Person> createResultList(Integer aSize) {
		List<Person> result = new ArrayList<Person>();
		PersonBuilder personBuilder = new PersonBuilder();

		for (int i = 0; i < aSize; i++) {
			Person person = personBuilder.build();
			result.add(person);
		}
		return result;
	}

	@Test
	public void testIsRowSelected_ForNonSelectedRow() {
		boolean result = sut.isRowSelectedForOneRow();

		assertFalse("Row should not selected!", result);
	}
	//
	//	@Test
	//	public void testIsRowSelected_ForOneSelectedRow() {
	//		List<Person> selectedPersons = new ArrayList<Person>();
	//		selectedPersons.add(new PersonBuilder().build());
	//
	//		sut.setSelectedPersons(selectedPersons);
	//
	//		boolean result = sut.isRowSelectedForOneRow();
	//
	//		assertTrue("Row should selected.", result);
	//	}
	//
	//	@Test
	//	public void testIsRowSelected_ForTwoSelectedRows() {
	//		List<Person> selectedPersons = new ArrayList<Person>();
	//		selectedPersons.add(new PersonBuilder().withForename(Forename.newInstance("Justus")).build());
	//		selectedPersons.add(new PersonBuilder().withForename(Forename.newInstance("Peter")).build());
	//		sut.setSelectedPersons(selectedPersons);
	//
	//		boolean result = sut.isRowSelectedForOneRow();
	//
	//		assertFalse("Not Valid for 2 Rows!", result);
	//	}
	//
	//
	//
	//	@Test
	//	public void testEditRow_ForNonSelectedRow() {
	//		ActionEvent dummyActionEvent = null;
	//
	//		sut.edit(dummyActionEvent);
	//
	//		verify(personEditPageBean, never()).openDialogFor(any(UUID.class));
	//		verify(sut).showMessageErrorNoRowSelected();
	//	}
	//
	//	@Test
	//	public void testEditRow_ForOneSelectedRow() {
	//		List<Person> selectedPersons = new ArrayList<Person>();
	//		selectedPersons.add(new PersonBuilder().build());
	//
	//		sut.setSelectedPersons(selectedPersons);
	//
	//		ActionEvent dummyActionEvent = null;
	//
	//		sut.edit(dummyActionEvent);
	//
	//		verify(personEditPageBean).openDialogFor(any(UUID.class));
	//		verify(sut, never()).showMessageErrorNoRowSelected();
	//
	//	}
	//
	//	@Test
	//	public void testEditRow_ForTwoSelectedRows() {
	//		List<Person> selectedPersons = new ArrayList<Person>();
	//		selectedPersons.add(new PersonBuilder().withForename(Forename.newInstance("Justus")).build());
	//		selectedPersons.add(new PersonBuilder().withForename(Forename.newInstance("Peter")).build());
	//		sut.setSelectedPersons(selectedPersons);
	//
	//		ActionEvent dummyActionEvent = null;
	//
	//		sut.edit(dummyActionEvent);
	//
	//		verify(personEditPageBean, never()).openDialogFor(any(UUID.class));
	//		verify(sut).showMessageErrorNoRowSelected();
	//	}

}