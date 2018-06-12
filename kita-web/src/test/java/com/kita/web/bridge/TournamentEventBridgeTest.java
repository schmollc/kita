package com.kita.web.bridge;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * If you let the tests rot, then your code will rot too.
 * Keep your tests clean.
 *  - Robert C. Martin
 *
 * @since 11.06.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TournamentEventBridgeTest {

	private TournamentEventBridge sut = TournamentEventBridgeDecorator.newInstance();

	@Test
	public void testIsSerializable() {
		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Klasse nicht Serializable!", condition);
	}
}