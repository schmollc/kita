package com.kita.attributes;

import java.io.Serializable;

public class Teamname implements Serializable {
	private static final long serialVersionUID = 8869042288915778298L;

	String value;

	private Teamname() {
		super();
	}

	private Teamname(String teamname) {
		super();
		value = teamname;
	}

	static public Teamname newInstance() {
		return TeamnameNullObject.instance();
	}

	/**
	 * Bloch, Joshua, Effective Java, 2nd Edition, Item 1, p. 5
	 */
	public static Teamname newInstance(String teamname) {
		if (teamname == null || teamname.trim().isEmpty()) {
			return TeamnameNullObject.instance();
		}

		return new Teamname(teamname);
	}

	public static int sortByName(Teamname name1, Teamname name2) {
		return name1.getValue().compareToIgnoreCase(name2.getValue());
	}

	private String getValue() {
		return value;
	}

	public boolean isEmpty() {
		return false;
	}

	@Override
	public String toString() {
		return getValue();
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
		Teamname other = (Teamname) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

	static final class TeamnameNullObject extends Teamname {
		private static final long serialVersionUID = 5459181639652243416L;

		private static final TeamnameNullObject SINGLETON = new TeamnameNullObject();

		private static TeamnameNullObject instance() {
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