package com.kita.attributes;

import java.io.Serializable;

public class Kampfname implements Serializable {
	private static final long serialVersionUID = 1L;

	String value;

	private Kampfname() {
	}

	private Kampfname(String kampfname) {
		super();
		value = kampfname;
	}

	public static Kampfname newInstance() {
		return KampfnameNullObject.instance();
	}

	/**
	 * Bloch, Joshua, Effective Java, 2nd Edition, Item 1, p. 5
	 */
	public static Kampfname newInstance(String kampfname) {
		if (kampfname == null || kampfname.trim().isEmpty()) {
			return KampfnameNullObject.instance();
		}

		return new Kampfname(kampfname);
	}

	public boolean isEmpty() {
		return false;
	}

	public static int sortByKampfname(Kampfname name1, Kampfname name2) {
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
		Kampfname other = (Kampfname) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

	static final class KampfnameNullObject extends Kampfname {
		private static final long serialVersionUID = -1400372422930380396L;

		private static final KampfnameNullObject SINGLETON = new KampfnameNullObject();

		private static KampfnameNullObject instance() {
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