package com.kita.web.pagebean;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.orm.GatewayType;

/**
 * Testen ist wie Rudern gegen den Strom. Hört man damit auf, treibt man zurück.
 *  - Laotse
 *
 * @since 17.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SelectItemBeanTest {
	private SelectItemBean sut = new SelectItemBean();

	@Test
	public void testGetGatewayTypes() {
		List<GatewayType> actual = sut.getGatewayTypes();

		assertNotNull("[actual] not correct!", actual);

		int size = actual.size();

		assertEquals("[actual] size not correct!", 2, size);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testModifyGatewayTypes() {
		sut.getGatewayTypes().add(GatewayType.FILE);
	}
}