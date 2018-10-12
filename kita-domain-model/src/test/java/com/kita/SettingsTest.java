package com.kita;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.orm.GatewayType;

/**
 * If you don't like unit testing your product, most likely your customers won't like to test it either.
 * - Anonymous
 *
 * @since 14.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SettingsTest {
	private Settings sut = Settings.newInstance();

	@Test
	public void testIsSerializable() {

		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Class not Serializable!", condition);
	}

	@Test
	public void testGatewayType() {
		GatewayType defaultValue = Settings.getGatewayType();

		assertEquals("Default GatewayType not correct!", GatewayType.MEMORY, defaultValue);

		GatewayType expected = GatewayType.FILE;

		Settings.setGatewayType(expected);

		GatewayType actual = Settings.getGatewayType();

		assertEquals("[gatewayType] nicht korrekt!", expected, actual);

		Settings.setGatewayType(GatewayType.MEMORY);
	}

	@Test
	public void testTheme_ForInitialSut() {
		String expected = "glass-x";

		String actual = sut.getTheme();

		assertEquals("[getTheme] for initial state not correct!", expected, actual);
	}

	@Test
	public void testTheme() {
		String expected = "le-Frog";

		sut.setTheme(expected);

		String actual = sut.getTheme();

		assertEquals("[getTheme] not correct!", expected, actual);
	}

}
