package com.kita.web.pagebean;

import java.io.Serializable;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.kita.TournamentEvent;
import com.kita.web.bridge.TournamentEventBridge;
import com.kita.web.bridge.TournamentEventBridgeDecorator;

/**
 * @since   16.10.2018
 *
 */
@ManagedBean
@SessionScoped
public class DockPageBean implements Serializable {
	private static final long serialVersionUID = 3687250236065787765L;

	private TournamentEventBridge tournamentEventBridge;

	@PostConstruct
	public void init() {
		tournamentEventBridge = TournamentEventBridgeDecorator.newInstance();
	}
	
	public boolean isTournamentNotActive() {
		Optional<TournamentEvent> active = tournamentEventBridge.getActive();
		boolean isPresent = active.isPresent();
		return !isPresent;
	}
}
