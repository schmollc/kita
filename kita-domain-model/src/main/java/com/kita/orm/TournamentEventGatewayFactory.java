package com.kita.orm;

import com.kita.orm.file.TournamentEventGatewayFile;
import com.kita.orm.memory.TournamentEventGatewayMemory;

/**
 * @since 14.05.2018
 *
 */
public class TournamentEventGatewayFactory {

	public static TournamentEventGateway get(GatewayType gatewayType) {

		switch (gatewayType) {
			case MEMORY:
				return TournamentEventGatewayMemory.newInstance();

			case FILE:
				return TournamentEventGatewayFile.newInstance();

			default:
				throw new IllegalArgumentException("[" + gatewayType + "] is unsupported Gateway Type.");
		}
	}
}