package com.kita.web.pagebean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.component.themeswitcher.ThemeSwitcher;

import com.kita.orm.GatewayType;
import com.kita.web.bridge.Settings;
import com.kita.web.theme.Theme;
import com.kita.web.theme.ThemeService;

/**
 * @since 15.05.2018
 *
 */
@ManagedBean
@SessionScoped
public class SettingsPageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	Settings settings = Settings.newInstance();

	private List<Theme> themes;

	@ManagedProperty("#{themeService}")
	ThemeService service;

	@PostConstruct
	public void init() {
		themes = service.getThemes();
	}

	public List<Theme> getThemes() {
		return themes;
	}

	public void setService(ThemeService aService) {
		service = aService;
	}

	public GatewayType getGatewayType() {
		return Settings.getGatewayType();
	}

	public void setGatewayType(GatewayType aGatewayType) {
		Settings.setGatewayType(aGatewayType);
	}

	public String getTheme() {
		return settings.getTheme();
	}

	public void setTheme(String aTheme) {
		settings.setTheme(aTheme);
	}

	public void saveTheme(AjaxBehaviorEvent ajax) {
		setTheme((String) ((ThemeSwitcher) ajax.getSource()).getValue());
	}

	public void saveGatewayType(AjaxBehaviorEvent ajax) {
		SelectOneMenu selectOneMenu = (SelectOneMenu) ajax.getSource();
		GatewayType selectedGatewayType = (GatewayType) selectOneMenu.getValue();
		Settings.setGatewayType(selectedGatewayType);
	}
}