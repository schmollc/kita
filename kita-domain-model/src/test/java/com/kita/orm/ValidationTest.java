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
	public void testOk_forSuccess() {
		Validation sut = Validation.success(true);

		boolean condition = sut.success();

		assertTrue("[ok] not correct!", condition);
	}

	@Test
	public void testOk_forNonSuccess() {
		Validation sut = Validation.success(false);

		boolean condition = sut.success();

		assertFalse("[ok] not correct!", condition);
	}

}