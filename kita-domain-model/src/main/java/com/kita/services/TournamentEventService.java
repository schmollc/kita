package com.kita.services;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.kita.Settings;
import com.kita.TournamentEvent;
import com.kita.orm.GatewayType;
import com.kita.orm.TournamentEventGateway;
import com.kita.orm.TournamentEventGatewayFactory;

/**
 * Only a simple Wrapper for the Gateway.
 *
 * @since 11.06.2018
 *
 */
public class TournamentEventService implements Serializable {
	private static final long serialVersionUID = 3854904742256165114L;

	private TournamentEventService() {

	}

	public static TournamentEventService newInstance() {
		return new TournamentEventService();
	}

	public TournamentEvent get(UUID anUuid) {
		return getGateway().get(anUuid);
	}

	public void set(TournamentEvent aTournamentEvent) {
		getGateway().set(aTournamentEvent);
	}

	public List<TournamentEvent> getAll() {
		return getGateway().getAll();
	}

	public GatewayType getGatewayType() {
		return Settings.getGatewayType();
	}

	public TournamentEventGateway getGateway() {
		return TournamentEventGatewayFactory.get(getGatewayType());
	}
}