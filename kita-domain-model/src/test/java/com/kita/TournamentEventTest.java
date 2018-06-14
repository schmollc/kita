package com.kita;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.attributes.EventDay;
import com.kita.attributes.Eventname;
import com.kita.attributes.TournamentStatus;

/**
 * Keine Straße ist zu lang mit einem Test an der Seite.
 *  - Konfuzius
 *
 * @since 13.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TournamentEventTest {
	private Eventname eventName = Eventname.newInstance("SWD Kicker Turnier");
	private EventDay eventDay = EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30));

	private TournamentEvent sut = TournamentEvent.newInstance(eventName, eventDay);

	@Test
	public void testIsSerializable() {

		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Class not Serializable!", condition);
	}

	@Test
	public void testNewValidInstance() {

		Eventname actualName = sut.getName();
		EventDay actualDay = sut.getDay();

		assertNotNull("[uuid] not correct!", sut.getUuid());
		assertEquals("[name] not correct!", eventName, actualName);
		assertEquals("[eventDay] not correct!", eventDay, actualDay);
	}

	@Test
	public void testNewEmptyInstance() {
		TournamentEvent sutEmpty = TournamentEvent.newInstance();

		assertNotNull("[uuid] not correct!", sutEmpty.getUuid());
		assertNotNull("[name] not correct!", sutEmpty.getName());
		assertNotNull("[eventDay] not correct!", sutEmpty.getDay());
	}

	@Test
	public void testName() {
		Eventname expected = Eventname.newInstance("Name");

		sut.setName(expected);

		Eventname actual = sut.getName();

		assertEquals("[name] not correct!", expected, actual);
	}

	@Test
	public void testDay() {
		EventDay expected = EventDay.newInstance(LocalDate.now());

		sut.setDay(expected);

		EventDay actual = sut.getDay();

		assertEquals("[eventDay] not correct!", expected, actual);
	}

	@Test
	public void testUuid() {
		UUID object = sut.getUuid();

		assertNotNull("Expected valid instance!", object);
	}

	@Test
	public void testActive() {
		boolean expected = true;

		sut.setActive(expected);

		boolean actual = sut.isActive();

		assertEquals("[active] not correct!", expected, actual);
	}

	@Test
	public void testStatus_ForDefault() {
		TournamentStatus expected = TournamentStatus.OPEN;

		TournamentStatus actual = sut.getStatus();

		assertEquals("[status] not correct!", expected, actual);
	}

	@Test
	public void testStatus_ForRunning() {
		TournamentStatus expected = TournamentStatus.RUNNING;

		sut.start();

		TournamentStatus actual = sut.getStatus();

		assertEquals("[status] not correct!", expected, actual);
	}

	@Test
	public void testStatus_ForClosed() {
		TournamentStatus expected = TournamentStatus.CLOSED;

		sut.close();

		TournamentStatus actual = sut.getStatus();

		assertEquals("[status] not correct!", expected, actual);
	}

	@Test
	public void testAddParticipant() {
		Participant participant = new ParticipantBuilder().withForename("Justus").withSurename("Jonas").build();

		sut.addParticipant(participant);

		Collection<Participant> actual = sut.getParticipants();

		assertNotNull("instance of [participants] not correct!", actual);
		assertEquals("[participants] size not correct!", 1, actual.size());
		Participant actualParticipant = actual.iterator().next();

		assertEquals("inserted participant not correct!", participant, actualParticipant);
	}

	@Test
	public void testAddParticipant_ForSameParticipant() {
		Participant participant = new ParticipantBuilder().withForename("Justus").withSurename("Jonas").build();

		sut.addParticipant(participant);
		sut.addParticipant(participant);

		Collection<Participant> actual = sut.getParticipants();

		assertNotNull("instance of [participants] not correct!", actual);
		assertEquals("[participants] size not correct!", 1, actual.size());
		Participant actualParticipant = actual.iterator().next();

		assertEquals("inserted participant not correct!", participant, actualParticipant);
	}

	@Test
	public void testRemoveParticipant() {
		Participant participant = new ParticipantBuilder().withForename("Justus").withSurename("Jonas").build();
		sut.addParticipant(participant);

		sut.removeParticipant(participant);

		Collection<Participant> actual = sut.getParticipants();

		assertNotNull("instance of [participants] not correct!", actual);
		boolean condition = actual.isEmpty();
		assertTrue("[participants] size not correct!", condition);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetParticipant_ForUnmodifiable() {
		Collection<Participant> participants = sut.getParticipants();
		participants.add(Participant.newInstance());
	}

	@Test
	public void testSetUuid() {
		Eventname eventNameDummy = Eventname.newInstance();
		EventDay eventDayDummy = EventDay.newInstance(LocalDate.of(2017, Month.APRIL, 30));

		TournamentEvent secondSut = TournamentEvent.newInstance(eventNameDummy, eventDayDummy);

		UUID expected = UUID.randomUUID();
		secondSut.setUuid(expected);

		UUID actual = secondSut.getUuid();

		assertEquals("[uuid] not correct!", expected, actual);
	}

	@Test
	public void testToString() {
		String actual = sut.toString();
		String expected = "SWD Kicker Turnier, 2017-04-30";

		assertEquals("[toString] not correct!", expected, actual);
	}

	@Test
	public void testGetHashCode() {
		UUID uuid = UUID.fromString("53a27b33-a5cb-4997-8eaf-dcf8bd1cb2d2");
		sut.setUuid(uuid);

		int hashCode = sut.hashCode();

		assertEquals(-975545171, hashCode);

		sut.setUuid(null);

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEquals_WithMyself() {
		boolean condition = sut.equals(sut);

		assertTrue(condition);
	}

	@Test
	public void testEquals_WithNull() {
		boolean condition = sut.equals(null);

		assertFalse(condition);
	}

	@Test
	public void testEquals_WithNotCompatibleClass() {
		@SuppressWarnings("unlikely-arg-type")
		boolean condition = sut.equals(new String());

		assertFalse(condition);
	}

	@Test
	public void testEquals_WithDiffrentValues() {
		TournamentEvent secondSut = TournamentEvent.newInstance(null, null);
		sut.setUuid(UUID.randomUUID());

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);

	}

	@Test
	public void testEquals_WithSameValues() {
		TournamentEvent secondSut = TournamentEvent.newInstance(null, null);
		secondSut.setUuid(sut.getUuid());

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}

	@Test
	public void testEquals_WithValueIsNull() {
		sut.setUuid(null);

		TournamentEvent secondSut = TournamentEvent.newInstance(null, null);

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);

	}

	@Test
	public void testEquals_WithBothValuesAreNull() {
		sut.setUuid(null);

		TournamentEvent secondSut = TournamentEvent.newInstance(null, null);
		secondSut.setUuid(null);

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}
}