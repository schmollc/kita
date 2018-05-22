package com.kita.attributes;

import java.io.Serializable;

/**
 * @since 17.05.2018
 *
 */
public class Forename implements Serializable {
	private static final long serialVersionUID = -1054781657742578752L;

	String value;

	private Forename() {
	}

	private Forename(String forename) {
		super();
		value = forename;
	}

	/**
	 * Bloch, Joshua, Effective Java, 2nd Edition, Item 1, p. 5
	 */
	public static Forename newInstance() {
		return ForenameNullObject.instance();
	}

	/**
	 * Bloch, Joshua, Effective Java, 2nd Edition, Item 1, p. 5
	 */
	public static Forename newInstance(String forename) {
		if (forename == null || forename.trim().isEmpty()) {
			return ForenameNullObject.instance();
		}

		return new Forename(forename);
	}

	public boolean isEmpty() {
		return false;
	}

	public static int sortByForename(Forename name1, Forename name2) {
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
		Forename other = (Forename) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

	static final class ForenameNullObject extends Forename {
		private static final long serialVersionUID = 6577776791000840413L;

		private static final ForenameNullObject SINGLETON = new ForenameNullObject();

		private static ForenameNullObject instance() {
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