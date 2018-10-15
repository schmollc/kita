package com.kita.attributes;

import java.io.Serializable;

public class Nickname implements Serializable {
	private static final long serialVersionUID = 7094038242337072674L;

	String value;

	private Nickname() {
	}
	
	private Nickname(String aNicknameValue) {
		value = aNicknameValue;
	}
	
	public static Nickname newInstance() {
		return NicknameNullObject.instance();
	}

	/**
	 * Bloch, Joshua, Effective Java, 2nd Edition, Item 1, p. 5
	 */
	public static Nickname newInstance(String aNicknameValue) {
		if(aNicknameValue == null || aNicknameValue.trim().isEmpty()) {
			return Nickname.newInstance();
		}
		return new Nickname(aNicknameValue);
	}

	// TODO -medium- sortByNickname.. Nun ja.. wonach denn sonst? sort sollte hier reichen!
	// TODO -medium- basiert auf der toString Methode (in den anderen noch)... Gar nicht gut!
	public static int sortByNickname(Nickname name1, Nickname name2) {
		return name1.value.toUpperCase().compareTo(name2.value.toUpperCase());
	}
	
	public boolean isEmpty() {
		return false;
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nickname other = (Nickname) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	static final class NicknameNullObject extends Nickname {
		private static final long serialVersionUID = -7154716967776865367L;

		private static final NicknameNullObject SINGLETON = new NicknameNullObject();

		private static NicknameNullObject instance() {
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