package com.kita.web.converter;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.attributes.Eventname;

/**
 * Discipline is the best tool.
 * - Anonymous
 *
 * @since 15.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventNameValueObjectConverterTest {
	private EventNameValueObjectConverter sut = new EventNameValueObjectConverter();

	private final String name = "SWD Kicker Turnier";

	@Test
	public void testGetAsObject_WithEmptyValue() {
		String emptyValue = "";
		Object object = sut.getAsObject(null, null, emptyValue);

		assertNull("Expected valid instance.", object);
	}

	@Test
	public void testGetAsObject_WithValue() {
		Object object = sut.getAsObject(null, null, name);

		assertNotNull("Expected valid instance.", object);
		assertEquals("Class not correct!", Eventname.class, object.getClass());
		Eventname eventName = (Eventname) object;
		assertEquals("Attribute not correct.", name, eventName.toString());
	}

	@Test
	public void testGetAsString() {
		Eventname eventName = Eventname.newInstance(name);

		String object = sut.getAsString(null, null, eventName);

		assertNotNull("Expected valid instance.", object);
		assertEquals("Attribute not correct.", name, object);
	}
}