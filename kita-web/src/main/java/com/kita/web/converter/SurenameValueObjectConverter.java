package com.kita.web.converter;

import javax.faces.convert.FacesConverter;

import com.kita.attributes.Surename;

/**
 * @since   22.05.2018
 *
 */
@FacesConverter("com.kita.web.converter.SurenameValueObjectConverter")
public class SurenameValueObjectConverter extends NameValueObjectConverter {

	@Override
	Surename getName(String nameValue) {
		return Surename.newInstance(nameValue);
	}
}