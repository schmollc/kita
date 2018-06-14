package com.kita.attributes;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Quality is never an accident; it is always the result of intelligent effort.
 *  - John Ruskin
 *
 * @since 14.06.2018
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TournamentStatusTest {

	@Test
	public void testCount() {
		TournamentStatus[] values = TournamentStatus.values();

		assertEquals("wrong count of elements!", 3, values.length);
	}
}