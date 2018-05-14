package com.kita.orm.file;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.kita.TournamentEvent;
import com.kita.attributes.EventDay;
import com.kita.attributes.Eventname;

/**
 *  1. Testen beginnt mit Respekt und endet mit Respekt.
 *  - Gichin Funakoshi
 *
 * @since 14.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TournamentEventToTournamentEventMapperTest {
	TournamentEventToTournamentEventMapper sut = TournamentEventToTournamentEventMapper.newInstance();

	private TournamentEvent source = TournamentEvent.newInstance();
	private TournamentEvent target = TournamentEvent.newInstance();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test
	public void testmapTournamentEventToTournamentEvent_ForSourceIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[source] must not be 'null'!");

		sut.mapTournamentEventToTournamentEvent(null, target);
	}

	@Test
	public void testmapTournamentEventToTournamentEvent_ForTargetIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[target] must not be 'null'!");

		sut.mapTournamentEventToTournamentEvent(source, null);
	}

	@Test
	public void testmapTournamentEventToTournamentEvent_ForEventDay() {
		EventDay expected = EventDay.today();
		source.setEventDay(expected);

		sut.mapTournamentEventToTournamentEvent(source, target);

		EventDay actual = target.getEventDay();
		assertEquals("Mapping of [eventDay] is not correct!", expected, actual);
	}

	@Test
	public void testmapTournamentEventToTournamentEvent_ForName() {
		Eventname expected = Eventname.newInstance("Rund um Ennepetal");
		source.setName(expected);

		sut.mapTournamentEventToTournamentEvent(source, target);

		Eventname actual = target.getName();
		assertEquals("Mapping of [name] is not correct!", expected, actual);
	}
}
