package com.kita.web.pagebean;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.kita.attributes.EventDay;
import com.kita.attributes.Eventname;
import com.kita.web.bridge.TournamentEventBridge;

import static org.mockito.Mockito.*;

/**
 * Die Sorge, was andere wohl von einem denken, verfliegt, wenn man merkt, wie selten sie an einen denken,
 *  - David Foster Wallace
 *
 * @since 15.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
public class TournamentEventEditPageBeanTest {

	@Spy
	@InjectMocks
	private TournamentEventEditPageBean sut;

	@Mock
	private TournamentEventBridge tournamentEventBridge;

	@Before
	public void setUp() {
		doNothing().when(sut).openDialog();
		doNothing().when(sut).closeDialog();
	}

	@Test
	public void testIsSerializable() {
		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Klasse nicht Serializable!", condition);
	}

	@Test
	public void testOpenDialogForCreateTournament() {
		sut.openDialogForCreateTournamentEvent();

		verify(sut).prepareNewTournamentEvent();
		verify(sut).openDialog();
	}

	@Test
	public void testPrepareNewTournamentEvent() {
		sut.prepareNewTournamentEvent();

		assertNotNull("[workingTournamentEvent] has not been created.", sut.workingTournamentEvent);
	}

	@Test
	public void testEventDay() {
		sut.openDialogForCreateTournamentEvent();
		EventDay expected = EventDay.today();

		sut.setEventDay(expected);

		EventDay result = sut.getEventDay();
		assertEquals("[EventDay] not correct!", expected, result);
	}

	@Test
	public void testEventname() {
		sut.openDialogForCreateTournamentEvent();
		Eventname expected = Eventname.newInstance();

		sut.setEventname(expected);

		Eventname result = sut.getEventname();
		assertEquals("[Eventname] not correct!", expected, result);
	}

	@Test
	public void testSave() {

		sut.save();

		verify(sut).persistTournamentEvent();
		verify(sut).closeDialog();
	}
}