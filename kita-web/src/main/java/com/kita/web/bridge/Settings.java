package com.kita.web.bridge;

import java.io.Serializable;

import com.kita.orm.GatewayType;

/**
 * @since 14.05.2018
 *
 */
public class Settings implements Serializable {
	private static final long serialVersionUID = 6060470764587750857L;

	private static GatewayType gatewayType = GatewayType.FILE;

	private Settings() {
		super();
	}

	public static Settings newInstance() {
		return new Settings();
	}

	public static GatewayType getGatewayType() {
		return gatewayType;
	}

	public static void setGatewayType(GatewayType aGatewayType) {
		gatewayType = aGatewayType;
	}
}