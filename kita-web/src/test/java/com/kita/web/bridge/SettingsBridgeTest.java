package com.kita.web.bridge;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Alles ist schwer, bevor es leicht wird
 *  - Johann Wolfgang von Goethe
 *
 * @since 17.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SettingsBridgeTest {

	private SettingsBridge sut = SettingsBridgeDecorator.newInstance();

	@Test
	public void testIsSerializable() {
		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Klasse nicht Serializable!", condition);
	}
}
