package com.kita.orm;

/**
 * @since 28.05.2018
 */
public class Validation {

	private boolean success;
	private String message;

	private Validation() {

	}

	public static Validation ok() {
		Validation validation = new Validation();
		validation.success = true;
		return validation;
	}

	public static Validation failure() {
		Validation validation = new Validation();
		validation.success = false;
		validation.message = I18N.EMAIL_NOT_UNIQUE;
		return validation;
	}

	public boolean success() {
		return success;
	}

	public String getMessage() {
		return message;
	}
}