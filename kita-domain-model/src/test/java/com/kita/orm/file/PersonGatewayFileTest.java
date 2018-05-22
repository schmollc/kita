package com.kita.orm.file;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.kita.orm.PersonGateway;
import com.kita.orm.PersonGatewayTest;

/**
 * Write your code so that it reflects, or rises above, the best parts of your personal character.
 *  - Daniel Read
 *
 * @since 18.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonGatewayFileTest extends PersonGatewayTest {

	private PersonGateway sut = PersonGatewayFile.newInstance("bigDataTest.kita");

	@Before
	public void setUp() {
		FileSingleton.getInstance().clear();
	}

	@Override
	public PersonGateway getSut() {
		return sut;
	}
}