package com.kita.orm;

/**
 * @since 28.05.2018
 */
public class Validation {

	private boolean success;

	private Validation() {

	}

	public static Validation newInstance() {
		return new Validation();
	}

	public boolean success() {
		return success;
	}

	public static Validation success(boolean aSuccessflag) {
		Validation validation = new Validation();
		validation.success = aSuccessflag;

		return validation;
	}

}