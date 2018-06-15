package com.kita.attributes;

import java.io.Serializable;

/**
 * @since 15.06.2018
 */
public class Score implements Serializable {
	private static final long serialVersionUID = 739549290599196979L;

	String value;

	private Score() {
	}

	private Score(String score) {
		super();
		value = score;
	}

	/**
	 * Bloch, Joshua, Effective Java, 2nd Edition, Item 1, p. 5
	 */
	public static Score newInstance() {
		return ScoreNullObject.instance();
	}

	/**
	 * Bloch, Joshua, Effective Java, 2nd Edition, Item 1, p. 5
	 */
	// TODO -small- Diskutieren: String? Intern ist es ja ein Integer...
	public static Score newInstance(String score) {
		if (score == null || score.trim().isEmpty()) {
			return ScoreNullObject.instance();
		}

		return new Score(score);
	}

	public void addScore() {
		Integer score = Integer.valueOf(value);
		score++;
		value = score.toString();
	}

	public boolean isEmpty() {
		return false;
	}

	public static int sortByScore(Score name1, Score name2) {
		return name1.toString().toUpperCase().compareTo(name2.toString().toUpperCase());
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Score other = (Score) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

	static final class ScoreNullObject extends Score {
		private static final long serialVersionUID = 6577776791000840413L;

		private static final ScoreNullObject SINGLETON = new ScoreNullObject();

		private static ScoreNullObject instance() {
			return SINGLETON;
		}

		@Override
		public boolean isEmpty() {
			return true;
		}

		@Override
		public String toString() {
			return "";
		}
	}
}
