package com.kita.web.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.kita.FormatConstants;
import com.kita.attributes.EventDay;

/**
 * @since 15.05.2018
 *
 */
@FacesConverter("com.kita.web.converter.EventDayValueObjectConverter")
public class EventDayValueObjectConverter implements Converter {

	@Override
	public Object getAsObject(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}

		LocalDate localDate = LocalDate.parse(value, DateTimeFormatter.ofPattern(FormatConstants.DATE_FORMAT_ISO));
		EventDay eventDay = EventDay.newInstance(localDate);

		return eventDay;
	}

	@Override
	public String getAsString(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, Object value) {
		return value.toString();
	}
}