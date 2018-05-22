package com.kita.web.pagebean;

import java.io.Serializable;

/**
 * Bloch, Joshua, Effective Java, 2nd Edition, p. 98
 *
 * @since 15.05.2018
 *
 */
public final class NavigationConstants implements Serializable {
	private static final long serialVersionUID = 2482809151474877656L;

	private NavigationConstants() {
		// restrict instantiation
	}

	public static final String TOURNAMENT_EVENT_EDIT_DIALOG_ID = "event/tournamentEventEditDialog";

	public static final String PERSON_PERSON_DIALOG_ID = "personEditDialog";

}