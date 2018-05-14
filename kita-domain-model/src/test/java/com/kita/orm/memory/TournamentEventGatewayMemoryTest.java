package com.kita.orm.memory;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.kita.orm.TournamentEventGateway;
import com.kita.orm.TournamentEventGatewayTest;

/**
 * Wenn du den Feind und dich kennst, brauchst du den Ausgang von hundert Tests nicht zu fürchten.
 *  - Sunzi (Sun Tzu)
 *
 * @since   31.05.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TournamentEventGatewayMemoryTest extends TournamentEventGatewayTest {
	private TournamentEventGatewayMemory sut = TournamentEventGatewayMemory.newInstance();

	@Before
	public void setUp() {
		MemorySingleton.getInstance().getTournamentEvents().clear();
	}

	@Override
	public TournamentEventGateway getSut() {
		return sut;
	}
}