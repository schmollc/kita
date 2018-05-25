package com.kita.web.converter;

import javax.faces.convert.FacesConverter;

import com.kita.attributes.Kampfname;

/**
 * @since   22.05.2018
 *
 */
@FacesConverter("com.kita.web.converter.KampfnameValueObjectConverter")
public class KampfnameValueObjectConverter extends NameValueObjectConverter {

	@Override
	Kampfname getName(String nameValue) {
		return Kampfname.newInstance(nameValue);
	}
}