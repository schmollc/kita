package com.kita.orm;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.kita.orm.file.PersonGatewayFile;
import com.kita.orm.memory.PersonGatewayMemory;

/**
 * An alle Programmierer, die noch um Mitternacht arbeiten: Es ist spät. Legt Euch schlafen.
 *  - Philip Toshio Sudo
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   23.06.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonGatewayFactoryTest {

	@Test
	public void testGet_ForPersonGatewayMemory() {
		PersonGateway instance = PersonGatewayFactory.get(GatewayType.MEMORY);

		assertEquals("Instance not korrekt!", instance.getClass(), PersonGatewayMemory.class);
	}

	@Test
	public void testGet_ForPersonGatewayFile() {
		PersonGateway instance = PersonGatewayFactory.get(GatewayType.FILE);

		assertEquals("Instance not korrekt!", instance.getClass(), PersonGatewayFile.class);
	}
}