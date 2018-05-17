package com.kita.web.bridge;

import java.io.Serializable;

import com.kita.orm.file.SettingsGateway;
import com.kita.orm.file.SettingsGatewayFile;

/**
 * @since 17.05.2018
 *
 */
public class SettingsBridgeImpl implements SettingsBridge, Serializable {
	private static final long serialVersionUID = -5525834371109399559L;

	SettingsGateway getGateway() {
		return new SettingsGatewayFile();
	}

	@Override
	public void resetFile() {
		getGateway().resetFile();
	}
}