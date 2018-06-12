package com.kita;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.attributes.Forename;
import com.kita.attributes.Surename;

/**
 * Wer sichere Schritte tun will, muß sie langsam tun.
 *  - Johann Wolfgang von Goethe
 *
 * @since 11.06.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParticipantTest {

	@Test
	public void testIsSerializable() {
		Participant sut = Participant.newInstance();

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testNewInstance() {
		Participant sut = Participant.newInstance();

		assertNotNull("Not a valid instance!", sut);

		UUID object = sut.getUuid();
		assertNotNull("[uuid] is not correct!", object);
	}

	@Test
	public void testCreateInstance_ForParameter() {
		Forename forename = Forename.newInstance();
		Surename surename = Surename.newInstance();

		Participant sut = Participant.newInstance(forename, surename);

		assertNotNull("Not a valid instance!", sut);

		boolean result = sut.getClass() == Participant.class;

		assertTrue("Instance is not correct!", result);
	}

	@Test
	public void testUuid() {
		Forename forename = Forename.newInstance();
		Surename surename = Surename.newInstance();

		Participant sut = Participant.newInstance(forename, surename);
		UUID expected = UUID.randomUUID();
		sut.setUuid(expected);

		UUID actual = sut.getUuid();
		assertEquals("[uuid] not correct!", expected, actual);
	}

	@Test
	public void testGetForename() {
		Forename expected = Forename.newInstance("Jonas");
		Surename dummySurename = Surename.newInstance();

		Participant sut = Participant.newInstance(expected, dummySurename);

		Forename actual = sut.getForename();

		assertEquals("[forename] not correct!", expected, actual);
	}

	@Test
	public void testGetSurename() {
		Surename expected = Surename.newInstance("Justus");

		Forename dummyForename = Forename.newInstance();

		Participant sut = Participant.newInstance(dummyForename, expected);

		Surename actual = sut.getSurename();

		assertEquals("[surename] not correct!", expected, actual);
	}

	@Test
	public void testToString_ForEmptyForenameAndSurename() {
		Participant sut = Participant.newInstance();

		String actual = sut.toString();
		String expected = " ";

		assertEquals("[toString] not correct!", expected, actual);
	}

	@Test
	public void testToString_ForFilledForenameAndSurename() {
		Forename forename = Forename.newInstance("Justus");
		Surename surename = Surename.newInstance("Jonas");

		Participant sut = Participant.newInstance(forename, surename);

		String actual = sut.toString();
		String expected = "Justus Jonas";

		assertEquals("[toString] not correct!", expected, actual);
	}

	@Test
	public void testHashCode() {
		Participant sut = Participant.newInstance();
		sut.setUuid(UUID.fromString("5697d710-8967-4b2d-9ab2-8fc50ddc6138"));

		int hashCode = sut.hashCode();

		assertEquals(1218343647, hashCode);

		sut.setUuid(null);

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEqualsWithMyself() {
		Participant sut = Participant.newInstance();

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithNull() {
		Participant sut = Participant.newInstance();

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		Participant sut = Participant.newInstance();

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEqualsWithValueIsNull() {
		Forename dummyForename = Forename.newInstance();
		Surename dummySurename = Surename.newInstance();

		Participant sut = Participant.newInstance(dummyForename, dummySurename);
		sut.setUuid(null);

		UUID uuidForSecondSut = UUID.randomUUID();
		Participant secondSut = Participant.newInstance(dummyForename, dummySurename);
		secondSut.setUuid(uuidForSecondSut);

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		Forename dummyForename = Forename.newInstance();
		Surename dummySurename = Surename.newInstance();

		Participant sut = Participant.newInstance(dummyForename, dummySurename);
		sut.setUuid(null);
		Participant secondSut = Participant.newInstance(dummyForename, dummySurename);
		secondSut.setUuid(null);

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		Forename dummyForename = Forename.newInstance();
		Surename dummySurename = Surename.newInstance();

		Participant sut = Participant.newInstance(dummyForename, dummySurename);
		UUID uuid = UUID.randomUUID();
		sut.setUuid(uuid);

		Participant secondSut = Participant.newInstance(dummyForename, dummySurename);
		UUID uuidForSecondSut = UUID.randomUUID();
		secondSut.setUuid(uuidForSecondSut);

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithSameValues() {
		Forename dummyForename = Forename.newInstance();
		Surename dummySurename = Surename.newInstance();
		UUID uuid = UUID.randomUUID();

		Participant sut = Participant.newInstance(dummyForename, dummySurename);
		sut.setUuid(uuid);
		Participant secondSut = Participant.newInstance(dummyForename, dummySurename);
		secondSut.setUuid(uuid);

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

}
