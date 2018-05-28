package com.kita.web.bridge;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.kita.Settings;
import com.kita.TournamentEvent;
import com.kita.orm.GatewayType;
import com.kita.orm.TournamentEventGateway;
import com.kita.orm.TournamentEventGatewayFactory;

/**
 * @since 14.05.2018
 *
 */
public class TournamentEventBridgeImpl implements Serializable, TournamentEventBridge {
	private static final long serialVersionUID = 1436811809418403317L;

	TournamentEventGateway getGateway() {
		return TournamentEventGatewayFactory.get(getGatewayType());
	}

	GatewayType getGatewayType() {
		return Settings.getGatewayType();
	}

	@Override
	public List<TournamentEvent> all() {
		return getGateway().getAll();
	}

	@Override
	public void set(TournamentEvent aTournamentEvent) {
		getGateway().set(aTournamentEvent);
	}

	@Override
	public TournamentEvent get(UUID anUuid) {
		return getGateway().get(anUuid);
	}
}