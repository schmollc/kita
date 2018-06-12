package com.kita.services;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.Test;

/**
 * Verlasse dich auf nichts.
 *  - Miyamoto Musashi
 *
 *  @since 12.06.2018
 *
 */
public class TournamentEventServiceTest {

	private TournamentEventService sut = TournamentEventService.newInstance();

	@Test
	public void testIsSerializable() {
		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Class not Serializable!", condition);
	}

}