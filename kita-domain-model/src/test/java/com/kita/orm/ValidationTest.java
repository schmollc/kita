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
	private Validation sut = Validation.newInstance();

	@Test
	public void testOk() {
		boolean condition = sut.ok();

		assertTrue("[ok] not correct!", condition);
	}
}