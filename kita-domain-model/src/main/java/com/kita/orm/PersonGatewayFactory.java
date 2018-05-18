package com.kita.orm;

import com.kita.orm.file.PersonGatewayFile;
import com.kita.orm.memory.PersonGatewayMemory;

/**
 * @since   18.05.2018
 *
 */
public class PersonGatewayFactory {

	public static PersonGateway get(GatewayType gatewayType) {

		switch (gatewayType) {
			case MEMORY:
				return new PersonGatewayMemory();

			case FILE:
				return new PersonGatewayFile();

			default:
				throw new IllegalArgumentException("[" + gatewayType + "] is unsupported Gateway Type.");
		}
	}
}