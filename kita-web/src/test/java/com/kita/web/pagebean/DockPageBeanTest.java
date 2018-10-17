package com.kita.web.pagebean;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.io.Serializable;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.kita.TournamentEvent;
import com.kita.web.bridge.TournamentEventBridge;


/**
 * Wrong is wrong, even if everyone is doing it
 * Right is right, even if no one is doing it
 *  - Dalai Lama
 *
 */
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DockPageBeanTest {

	@Mock
	private TournamentEventBridge tournamentEventBridge;

	@InjectMocks
	private DockPageBean sut = new DockPageBean();

	@Test
	public void testIsSerializable() {
		boolean condition = sut instanceof Serializable;

		assertTrue("Class is not Serializable!", condition);
	}
	 
	@Test
	public void testIsNotActive_ForEventIsNotActive() {
		doReturn(Optional.empty()).when(tournamentEventBridge).getActive();

		boolean condition = sut.isNotActive();

		assertTrue("[isNotActive] was not correct!", condition);
	}

	@Test
	public void testIsNotActive_ForEventIsActive() {
		doReturn(Optional.of(TournamentEvent.newInstance())).when(tournamentEventBridge).getActive();

		boolean condition = sut.isNotActive();

		assertFalse("[isNotActive] was not correct!", condition);
	}
	
}