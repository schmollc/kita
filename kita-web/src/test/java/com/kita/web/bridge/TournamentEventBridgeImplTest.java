package com.kita.web.bridge;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.orm.GatewayType;

/**
 * If you let the tests rot, then your code will rot too.
 * Keep your tests clean.
 *  - Robert C. Martin
 *
 * @author schmollc (Christian@relayd.de)
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TournamentEventBridgeImplTest {

	private TournamentEventBridgeImpl sut = new TournamentEventBridgeImpl();

	@Test
	public void testIsSerializable() {
		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Klasse nicht Serializable!", condition);
	}

	@Test
	public void testGatewayType() {
		GatewayType result = sut.getGatewayType();

		assertEquals("[gatewayType] not correct!", GatewayType.FILE, result);
	}

}
