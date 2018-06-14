package com.kita.orm;

import java.io.Serializable;

/**
 * @since 13.05.2018
 *
 */
public final class I18N implements Serializable {
	private static final long serialVersionUID = 1219047352229337279L;

	private I18N() {
		//restrict access
	}

	// @formatter:off
	public static final String EMAIL_NOT_UNIQUE		= "Email not uniqe!";
	public static final String NOT_POSSIBLE 		= "Not Possible!";
	public static final String NOT_IMPLEMENTD_YET	= "Not implemented yet!";
	public static final String SELECT_A_PARTICIPANT = "Please select a Participant!";
	public static final String SELECT_A_PERSON		= "Please select a Person!";
	public static final String SELECT_A_ROW			= "Please select one row!";
	// @formatter:on
}