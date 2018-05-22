package com.kita.web.converter;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.attributes.Forename;

/**
 * The goal of software architecture is to minimize the human resources requiered to build an maintain the required system.
 *  - Robert C. Martin, Clean Architecture
 *
 * @since 22.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ForenameValueObjectConverterTest extends NameValueObjectConverterTest {
	private ForenameValueObjectConverter sut = new ForenameValueObjectConverter();

	private final String name = "Justus";

	@Override
	NameValueObjectConverter getSut() {
		return sut;
	}

	@Test
	public void testGetName() {
		Forename expected = Forename.newInstance(name);
		Forename object = sut.getName(name);

		assertEquals("Forename has not been correctly created!", expected, object);
	}

	@Test
	public void testGetAsObject_ForValue() {
		Object object = sut.getAsObject(null, null, name);

		assertNotNull("Expected valid instance!", object);
		assertEquals("Class not correct!", Forename.class, object.getClass());
		Forename forename = (Forename) object;
		assertEquals("Attribute is not correct!", name, forename.toString());
	}

	@Test
	public void testGetAsString() {
		Forename forename = Forename.newInstance(name);

		String object = sut.getAsString(null, null, forename);

		assertNotNull("Expected valid instance!", object);
		assertEquals("Attribute is not correct!", name, object);
	}
}