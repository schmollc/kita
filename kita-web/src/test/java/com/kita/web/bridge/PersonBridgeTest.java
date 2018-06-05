package com.kita.web.bridge;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * But isn’t some code hard to test?
 * Yes, but only because that code has been designed to be hard to test.
 *  - Robert C. Martin
 *
 * @since 05.06.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonBridgeTest {
	private PersonBridge sut = PersonBridgeDecorator.newInstance();

	@Test
	public void testIsSerializable() {
		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Klasse nicht Serializable!", condition);
	}
}
