package com.kita;

import java.io.Serializable;
import java.util.UUID;

import com.kita.attributes.Score;

/**
 * @since 15.06.2018
 */
public class Game implements Serializable {
	private static final long serialVersionUID = -6643897760864363089L;

	private UUID uuid = null;
	private Team teamOne = null;
	private Team teamTwo = null;
	private Score scoreForTeamOne = Score.newInstance("0");
	private Score scoreForTeamTwo = Score.newInstance("0");

	private Game() {
		uuid = UUID.randomUUID();
	}

	public static Game newInstance() {
		return new Game();
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setTeamOne(Team aTeam) {
		teamOne = aTeam;
	}

	public Team getTeamOne() {
		return teamOne;
	}

	public void setTeamTwo(Team aTeam) {
		teamTwo = aTeam;
	}

	public Team getTeamTwo() {
		return teamTwo;
	}

	public void goalForTeamOne() {
		if (getTeamOne() == null) {
			throw new IllegalArgumentException();
		}
		scoreForTeamOne.addScore();
	}

	public void goalForTeamTwo() {
		if (getTeamTwo() == null) {
			throw new IllegalArgumentException();
		}
		scoreForTeamTwo.addScore();
	}

	public Score getScoreForTeamOne() {
		return scoreForTeamOne;
	}

	public Score getScoreForTeamTwo() {
		return scoreForTeamTwo;
	}
}