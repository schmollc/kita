package com.kita.web.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.attributes.Nickname;

/**
 * Qualität ist das Produkt der Liebe zum Detail.
 *  - Andreas Tenzer
 *
 * @since   15.10.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NicknameValueObjectConverterTest extends NameValueObjectConverterTest {
	private NicknameValueObjectConverter sut = new NicknameValueObjectConverter();

	private final String name = "Jonas";

	@Override
	NameValueObjectConverter getSut() {
		return sut;
	}

	@Test
	public void testGetName() {
		Nickname expected = Nickname.newInstance(name);
		Nickname actual = sut.getName(name);

		assertEquals("Nickname has not been correctly created!", expected, actual);
	}

	@Test
	public void testGetAsObject_ForValue() {
		Object object = sut.getAsObject(null, null, name);

		assertNotNull("Expected valid instance!", object);
		assertEquals("Class not correct!", Nickname.class, object.getClass());
		Nickname nickname = (Nickname) object;
		assertEquals("Attribute not correct!", name, nickname.toString());
	}

	@Test
	public void testGetAsString() {
		Nickname nickname = Nickname.newInstance(name);

		String actual = sut.getAsString(null, null, nickname);

		assertNotNull("Expected valid instance!", actual);
		assertEquals("Attribute not correct!", name, actual);
	}
}