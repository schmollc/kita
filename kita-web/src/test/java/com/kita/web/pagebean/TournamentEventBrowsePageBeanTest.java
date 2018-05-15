package com.kita.web.pagebean;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Spy;

/**
 * Tests are the Programmer’s stone, transmuting fear into boredom.
 *  - Kent Beck
 *
 * @since 13.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TournamentEventBrowsePageBeanTest {

	@Spy
	private TournamentEventBrowsePageBean sut;

	@Test
	public void testIsSerializable() {

		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Class not Serializable!", condition);
	}
}