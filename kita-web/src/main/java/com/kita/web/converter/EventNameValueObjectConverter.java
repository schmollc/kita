package com.kita.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.kita.attributes.Eventname;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 22.06.2016
 *
 */
@FacesConverter("com.kita.web.converter.EventNameValueObjectConverter")
public class EventNameValueObjectConverter implements Converter {

	@Override
	public Object getAsObject(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		Eventname name = Eventname.newInstance(value);
		return name;
	}

	@Override
	public String getAsString(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, Object value) {
		return value.toString();
	}
}