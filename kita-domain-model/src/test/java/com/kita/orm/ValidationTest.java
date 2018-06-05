package com.kita.orm;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @since 04.06.2018
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidationTest {

	@Test
	public void testOk() {
		Validation sut = Validation.ok();

		boolean condition = sut.success();
		String object = sut.getMessage();

		assertTrue("[ok] not correct!", condition);
		assertNull("[errorMessage] should be null!", object);
	}

	@Test
	public void testFailure() {
		Validation sut = Validation.failure();

		boolean condition = sut.success();
		String actual = sut.getMessage();
		String expected = I18N.EMAIL_NOT_UNIQUE;

		assertFalse("[ok] not correct!", condition);
		assertEquals("[errorMessage] not correct!", expected, actual);
	}
}