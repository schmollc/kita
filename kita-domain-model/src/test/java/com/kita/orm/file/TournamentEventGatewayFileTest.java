package com.kita.orm.file;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.kita.orm.TournamentEventGateway;
import com.kita.orm.TournamentEventGatewayTest;

/**
 * Every large system that works started as a small system that worked.
 *  - Anonymous
 *
 * @since   14.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TournamentEventGatewayFileTest extends TournamentEventGatewayTest {

	private TournamentEventGatewayFile sut = TournamentEventGatewayFile.newInstance("bigDataTest.kita");

	@Before
	public void setUp() {
		sut.clear();
	}

	@Override
	public TournamentEventGateway getSut() {
		return sut;
	}
}
