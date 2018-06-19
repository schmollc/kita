package com.kita;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.attributes.Forename;
import com.kita.attributes.Surename;
import com.kita.attributes.Teamname;

/**
 * Was nützt es wenn wir rennen, uns aber auf dem falschen Weg befinden?
 *  - Sprichwort
 *
 * @since 14.06.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TeamTest {

	@Test
	public void testIsSerializable() {
		Team sut = Team.newInstance();

		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Klasse nicht Serializable!", condition);
	}

	@Test
	public void testNewInstance() {
		Team sut = Team.newInstance();

		assertNotNull("Not a valid instance!", sut);

		Object object = sut.getUuid();

		assertNotNull("[uuid] is not correct!", object);
	}

	@Test
	public void testUuid() {
		Participant dummyParticipant = Participant.newInstance();

		Team sut = Team.newInstance(dummyParticipant, dummyParticipant);
		UUID expected = UUID.randomUUID();
		sut.setUuid(expected);

		UUID actual = sut.getUuid();
		assertEquals("[uuid] not correct!", expected, actual);
	}

	@Test
	public void testName() {
		Participant dummyParticipant = Participant.newInstance();

		Team sut = Team.newInstance(dummyParticipant, dummyParticipant);
		Teamname expected = Teamname.newInstance("Team A");
		sut.setName(expected);

		Teamname actual = sut.getName();
		assertEquals("[Name] not correct!", expected, actual);
	}

	@Test
	public void testGetFirstParticipant() {
		Participant expected = Participant.newInstance(Forename.newInstance("Justus"), Surename.newInstance("Jonas"), UUID.randomUUID());
		Participant dummyParticipant = null;

		Team sut = Team.newInstance(expected, dummyParticipant);

		Participant actual = sut.getFirstParticipant();

		assertEquals("[getFirstParticipant] not correct!", expected, actual);
	}

	@Test
	public void testGetSecondParticipant() {
		Participant dummyParticipant = null;
		Participant expected = Participant.newInstance(Forename.newInstance("Justus"), Surename.newInstance("Jonas"), UUID.randomUUID());

		Team sut = Team.newInstance(dummyParticipant, expected);

		Participant actual = sut.getSecondParticipant();

		assertEquals("[getSecondParticipant] not correct!", expected, actual);

	}

	@Test
	public void testToString() {
		String expected = "Great Expactations";

		Teamname teamname = Teamname.newInstance(expected);

		Team sut = Team.newInstance();

		sut.setName(teamname);

		String actual = sut.toString();

		assertEquals("[toString] not correct!", expected, actual);
	}

	@Test
	public void testHashCode() {
		Participant dummyParticipant = Participant.newInstance();

		Team sut = Team.newInstance(dummyParticipant, dummyParticipant);

		sut.setUuid(UUID.fromString("5697d710-8967-4b2d-9ab2-8fc50ddc6138"));

		int hashCode = sut.hashCode();

		assertEquals(1218343647, hashCode);

		sut.setUuid(null);

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEqualsWithMyself() {
		Team sut = Team.newInstance();

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithNull() {
		Team sut = Team.newInstance();

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		Team sut = Team.newInstance();

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEqualsWithValueIsNull() {
		Participant dummyParticipant = Participant.newInstance();

		Team sut = Team.newInstance(dummyParticipant, dummyParticipant);
		sut.setUuid(null);

		UUID uuidForSecondSut = UUID.randomUUID();
		Team secondSut = Team.newInstance(dummyParticipant, dummyParticipant);
		secondSut.setUuid(uuidForSecondSut);

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		Participant dummyParticipant = Participant.newInstance();

		Team sut = Team.newInstance(dummyParticipant, dummyParticipant);
		sut.setUuid(null);
		Team secondSut = Team.newInstance(dummyParticipant, dummyParticipant);
		secondSut.setUuid(null);

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		Participant dummyParticipant = Participant.newInstance();

		Team sut = Team.newInstance(dummyParticipant, dummyParticipant);
		UUID uuid = UUID.randomUUID();
		sut.setUuid(uuid);

		Team secondSut = Team.newInstance(dummyParticipant, dummyParticipant);
		UUID uuidForSecondSut = UUID.randomUUID();
		secondSut.setUuid(uuidForSecondSut);

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithSameValues() {
		Participant dummyParticipant = Participant.newInstance();
		UUID uuid = UUID.randomUUID();

		Team sut = Team.newInstance(dummyParticipant, dummyParticipant);
		sut.setUuid(uuid);
		Team secondSut = Team.newInstance(dummyParticipant, dummyParticipant);
		secondSut.setUuid(uuid);

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}
}