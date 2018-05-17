package com.kita.web.pagebean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.kita.orm.GatewayType;

/**
 * Holds the methods for Comboboxes in the GUI.
 *
 * @since 17.05.2018
 *
 */
@ManagedBean(name = "selectItemBean")
@SessionScoped
public class SelectItemBean implements Serializable {
	private static final long serialVersionUID = -8028809269452661427L;

	private List<GatewayType> gateways;

	public SelectItemBean() {
		initGateways();
	}

	private void initGateways() {
		gateways = new ArrayList<GatewayType>();

		for (GatewayType eachGatewayType : GatewayType.values()) {
			gateways.add(eachGatewayType);
		}
	}

	public List<GatewayType> getGatewayTypes() {
		return Collections.unmodifiableList(gateways);
	}
}