package com.kita.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * @since 22.05.2018
 *
 */
public abstract class NameValueObjectConverter implements Converter {

	abstract Object getName(String nameValue);

	@Override
	public Object getAsObject(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, String nameValue) {
		Object name = null;
		if (nameValue != null && !nameValue.trim().isEmpty()) {
			name = getName(nameValue);
		}
		return name;
	}

	@Override
	public String getAsString(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, Object nameValue) {
		return nameValue.toString();
	}
}