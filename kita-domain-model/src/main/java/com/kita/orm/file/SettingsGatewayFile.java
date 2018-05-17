package com.kita.orm.file;

/**
 * @since 17.05.2018
 *
 */
public class SettingsGatewayFile implements SettingsGateway {

	@Override
	public void resetFile() {
		FileSingleton.getInstance().reset();
	}
}
