package com.kita.orm;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.orm.file.TournamentEventGatewayFile;
import com.kita.orm.memory.TournamentEventGatewayMemory;

/**
 * Einen Fehler begangen haben und ihn nicht korrigieren: Erst das ist ein Fehler.
 * - Konfuzius
 *
 * @since 14.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TournamentEventGatewayFactoryTest {

	@Test
	public void testGetForRelayEventGatewayMemory() {
		TournamentEventGateway instance = TournamentEventGatewayFactory.get(GatewayType.MEMORY);

		assertEquals("Instance not korrekt.", instance.getClass(), TournamentEventGatewayMemory.class);
	}

	@Test
	public void testGetForRelayEventGatewayFile() {
		TournamentEventGateway instance = TournamentEventGatewayFactory.get(GatewayType.FILE);

		assertEquals("Instance not korrekt.", instance.getClass(), TournamentEventGatewayFile.class);
	}
}