package com.kita.orm;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.TournamentEvent;
import com.kita.attributes.EventDay;
import com.kita.attributes.Eventname;

/**
 * Es bleibt einem jeden immer noch soviel Kraft, das auszuführen, wovon er überzeugt ist.
 *  - Johann Wolfgang von Goethe
 *
 * @since   14.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class TournamentEventGatewayTest {

	public abstract TournamentEventGateway getSut();

	@Test
	public void testGetAll_WithNoEvent() {
		List<TournamentEvent> actualTournamentEventList = getSut().getAll();

		boolean condition = actualTournamentEventList.isEmpty();

		assertTrue("condition not correct!", condition);
	}

	@Test
	public void testGetAll_WithOneEvent() {
		Eventname expectedEventname = Eventname.newInstance("Neues SWD Turnier");
		EventDay expectedEventDay = EventDay.newInstance(LocalDate.of(2018, Month.APRIL, 16));

		TournamentEvent swdKickerTurnier = TournamentEvent.newInstance(expectedEventname, expectedEventDay);
		getSut().set(swdKickerTurnier);

		List<TournamentEvent> actualTournamentEventList = getSut().getAll();

		assertEquals("resultSize not correct!", 1, actualTournamentEventList.size());

		TournamentEvent actualTournamentEvent = actualTournamentEventList.get(0);

		assertEquals("[name] not correct!", expectedEventname, actualTournamentEvent.getName());
		assertEquals("[day] not correct!", expectedEventDay, actualTournamentEvent.getDay());
	}

	@Test
	public void testSet() {
		Eventname expectedEventname = Eventname.newInstance("C&A Services Kicker Turnier");
		EventDay expectedEventDay = EventDay.newInstance(LocalDate.of(2017, Month.AUGUST, 28));
		TournamentEvent swdKickerTurnier = TournamentEvent.newInstance(expectedEventname, expectedEventDay);

		getSut().set(swdKickerTurnier);
		List<TournamentEvent> actualTournamentEventList = getSut().getAll();

		assertEquals("resultSize not correct!", 1, actualTournamentEventList.size());

		TournamentEvent actualTournamentEventRundUmEnnepetal = actualTournamentEventList.get(0);

		assertEquals("[name] not correct!", expectedEventname, actualTournamentEventRundUmEnnepetal.getName());
		assertEquals("[day] not correct!", expectedEventDay, actualTournamentEventRundUmEnnepetal.getDay());
	}

	@Test
	public void testGet_WithExistingEntry() {
		TournamentEvent expected = createKickerTurnierSWD();

		getSut().set(createKickerTurnierBSOD());
		getSut().set(expected);

		TournamentEvent actual = getSut().get(expected.getUuid());

		assertNotNull("[get] has incorrect return value!", actual);
		assertEquals("[get] not correct!", expected, actual);
	}

	@Test
	public void testGet_WithNonExistingEntry() {
		getSut().set(createKickerTurnierBSOD());
		getSut().set(createKickerTurnierSWD());

		TournamentEvent actual = getSut().get(UUID.randomUUID());

		assertNull(actual);
	}

	private TournamentEvent createKickerTurnierSWD() {
		Eventname eventname = Eventname.newInstance("SWD KickerTurnier");
		EventDay eventDay = EventDay.newInstance(LocalDate.of(2018, Month.JANUARY, 31));
		TournamentEvent kickerTurnier = TournamentEvent.newInstance(eventname, eventDay);
		return kickerTurnier;
	}

	private TournamentEvent createKickerTurnierBSOD() {
		Eventname eventname = Eventname.newInstance("BSOD KickerTurnier");
		EventDay eventDay = EventDay.newInstance(LocalDate.of(2018, Month.FEBRUARY, 1));
		TournamentEvent kickerTurnier = TournamentEvent.newInstance(eventname, eventDay);
		return kickerTurnier;
	}
}