package com.kita.orm.memory;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.kita.orm.PersonGateway;
import com.kita.orm.PersonGatewayTest;

/**
 * Konventionen sind eines der intellektuellen Werkzeuge, die Ihnen im Kampf gegen die Komplexität von Software zur Verfügung stehen.
 *  - Steve McConnell - Code Complete Seite 889
 *
 * @since 18.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonGatewayMemoryTest extends PersonGatewayTest {

	private PersonGateway sut = PersonGatewayMemory.newInstance();

	@Before
	public void setUp() {
		MemorySingleton.getInstance().getPersons().clear();
	}

	@Override
	public PersonGateway getSut() {
		return sut;
	}
}