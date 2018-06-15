package com.kita.services;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.kita.Participant;
import com.kita.ParticipantBuilder;
import com.kita.Team;

/**
 * Verlasse dich auf nichts.
 *  - Miyamoto Musashi
 *
 *  @since 12.06.2018
 *
 */
public class TournamentEventServiceTest {

	private TournamentEventService sut = TournamentEventService.newInstance();

	@Test
	public void testIsSerializable() {
		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Class not Serializable!", condition);
	}

	@Test
	public void testCreateTeams_ForOddParticipants() {
		// Arrange
		List<Participant> someParticipants = new ArrayList<>();

		Participant firstParticipant = new ParticipantBuilder().withForename("Justus").withSurename("Jonas").build();
		Participant secondParticipant = new ParticipantBuilder().withForename("Peter").withSurename("Shaw").build();
		Participant thirdParticipant = new ParticipantBuilder().withForename("Bob").withSurename("Andrews").build();
		Participant fourthParticipant = new ParticipantBuilder().withForename("Skinny").withSurename("Norris").build();

		someParticipants.add(firstParticipant);
		someParticipants.add(secondParticipant);
		someParticipants.add(thirdParticipant);
		someParticipants.add(fourthParticipant);

		// Act
		List<Team> actual = sut.createTeamsFrom(someParticipants);

		// Assert

		Team firstTeam = actual.get(0);

		Team secondTeam = actual.get(1);

		assertEquals(firstParticipant, firstTeam.getFirstParticipant());
		assertEquals(secondParticipant, firstTeam.getSecondParticipant());

		assertEquals(thirdParticipant, secondTeam.getFirstParticipant());
		assertEquals(fourthParticipant, secondTeam.getSecondParticipant());
	}

	@Test
	public void testCreateTeams_ForNotOddParticipants() {
		// Arrange
		List<Participant> someParticipants = new ArrayList<>();

		Participant firstParticipant = new ParticipantBuilder().withForename("Justus").withSurename("Jonas").build();
		Participant secondParticipant = new ParticipantBuilder().withForename("Peter").withSurename("Shaw").build();
		Participant thirdParticipant = new ParticipantBuilder().withForename("Bob").withSurename("Andrews").build();

		someParticipants.add(firstParticipant);
		someParticipants.add(secondParticipant);
		someParticipants.add(thirdParticipant);

		// Act
		List<Team> actual = sut.createTeamsFrom(someParticipants);

		// Assert
		Team firstTeam = actual.get(0);
		Team secondTeam = actual.get(1);

		assertEquals(firstParticipant, firstTeam.getFirstParticipant());
		assertEquals(secondParticipant, firstTeam.getSecondParticipant());

		assertEquals(thirdParticipant, secondTeam.getFirstParticipant());
		assertNull(secondTeam.getSecondParticipant());

	}

}