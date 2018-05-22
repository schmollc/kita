package com.kita.web.converter;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.attributes.Kampfname;

/**
 * Qualität ist das Produkt der Liebe zum Detail.
 *  - Andreas Tenzer
 *
 * @since   22.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KampfnameValueObjectConverterTest extends NameValueObjectConverterTest {
	private KampfnameValueObjectConverter sut = new KampfnameValueObjectConverter();

	private final String name = "Jonas";

	@Override
	NameValueObjectConverter getSut() {
		return sut;
	}

	@Test
	public void testGetName() {
		Kampfname expected = Kampfname.newInstance(name);
		Kampfname actual = sut.getName(name);

		assertEquals("Kampfname has not been correctly created!", expected, actual);
	}

	@Test
	public void testGetAsObject_ForValue() {
		Object object = sut.getAsObject(null, null, name);

		assertNotNull("Expected valid instance!", object);
		assertEquals("Class not correct!", Kampfname.class, object.getClass());
		Kampfname kampfname = (Kampfname) object;
		assertEquals("Attribute not correct!", name, kampfname.toString());
	}

	@Test
	public void testGetAsString() {
		Kampfname kampfname = Kampfname.newInstance(name);

		String actual = sut.getAsString(null, null, kampfname);

		assertNotNull("Expected valid instance!", actual);
		assertEquals("Attribute not correct!", name, actual);
	}
}
