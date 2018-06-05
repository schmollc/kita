package com.kita.services;

import java.io.Serializable;

import com.kita.orm.file.SettingsGateway;
import com.kita.orm.file.SettingsGatewayFile;

/**
 * Only a simple Wrapper for the Gateway.
 *
 * @since 05.06.2018
 *
 */
public class SettingsBridgeService implements Serializable {
	private static final long serialVersionUID = -2622778356289645552L;

	private SettingsBridgeService() {

	}

	public static SettingsBridgeService newInstance() {
		return new SettingsBridgeService();
	}

	SettingsGateway getGateway() {
		return new SettingsGatewayFile();
	}

	public void resetFile() {
		getGateway().resetFile();
	}
}