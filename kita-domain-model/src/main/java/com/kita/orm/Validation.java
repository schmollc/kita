package com.kita.orm;

/**
 * @since 28.05.2018
 */
public class Validation {

	private Validation() {

	}

	public static Validation newInstance() {
		return new Validation();
	}

	public boolean ok() {
		return true;
	}

}