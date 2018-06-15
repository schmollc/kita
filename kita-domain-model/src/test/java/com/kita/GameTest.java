package com.kita;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.attributes.Score;

/**
 * Don't practice CleanCode to get better in CleanCode;
 * practice CleanCode to get better at living
 *  - CleanCode Wisodm
 *
 * @since 15.06.2018
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameTest {

	@Test
	public void testIsSerializable() {
		Game sut = Game.newInstance();

		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;
		assertTrue("Class not Serializable!", condition);
	}

	@Test
	public void testGetUuid() {
		Game sut = Game.newInstance();

		UUID object = sut.getUuid();

		assertNotNull("[getUuid] not correct!", object);
	}

	@Test
	public void testTeamOne() {
		Game sut = Game.newInstance();

		Team expected = Team.newInstance(participant("Justus", "Jonas"), participant("Peter", "Shaw"));

		sut.setTeamOne(expected);

		Team actual = sut.getTeamOne();

		assertEquals("[getTeamOne] not correct!", expected, actual);
	}

	@Test
	public void testTeamTwo() {
		Game sut = Game.newInstance();

		Team expected = Team.newInstance(participant("Justus", "Jonas"), participant("Peter", "Shaw"));

		sut.setTeamTwo(expected);

		Team actual = sut.getTeamTwo();

		assertEquals("[getTeamTwo] not correct!", expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGoalForTeamOne_ForNotExistingTeam() {
		Game sut = Game.newInstance();

		sut.goalForTeamOne();
	}

	@Test
	public void testGoalForTeamOne() {
		Game sut = Game.newInstance();
		sut.setTeamOne(team());

		sut.goalForTeamOne();

		Score actual = sut.getScoreForTeamOne();
		Score expected = Score.newInstance("1");
		assertEquals("[getScoreForTeamOne]", expected, actual);
	}

	private Team team() {
		return Team.newInstance(participant("Justus", "Jonas"), participant("Peter", "Shaw"));
	}

	@Test
	public void testGoalForTeamTwo() {
		Game sut = Game.newInstance();
		sut.setTeamTwo(team());

		sut.goalForTeamTwo();

		Score actual = sut.getScoreForTeamTwo();
		Score expected = Score.newInstance("1");
		assertEquals("[getScoreForTeamOne]", expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGoalForTeamTwo_ForNotExistingTeam() {
		Game sut = Game.newInstance();

		sut.goalForTeamTwo();
	}

	private Participant participant(String aForename, String aSurename) {
		return new ParticipantBuilder().withForename(aForename).withSurename(aSurename).build();
	}
}
