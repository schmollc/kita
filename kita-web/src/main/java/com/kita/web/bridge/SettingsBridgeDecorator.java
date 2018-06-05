package com.kita.web.bridge;

import java.io.Serializable;

import com.kita.services.SettingsBridgeService;

/**
 * @since 05.06.2018
 *
 */
public class SettingsBridgeDecorator implements SettingsBridge, Serializable {
	private static final long serialVersionUID = -5525834371109399559L;
	// FCoI
	private SettingsBridgeService settingsBridgeService = SettingsBridgeService.newInstance();

	private SettingsBridgeDecorator() {

	}

	public static SettingsBridge newInstance() {
		return new SettingsBridgeDecorator();
	}

	private SettingsBridgeService getSettingsBridgeImpl() {
		return settingsBridgeService;
	}

	@Override
	public void resetFile() {
		getSettingsBridgeImpl().resetFile();
	}
}
