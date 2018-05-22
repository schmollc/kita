package com.kita.web.converter;

import javax.faces.convert.FacesConverter;

import com.kita.attributes.Forename;

/**
 * @since 22.05.2018
 *
 */
@FacesConverter("com.relayd.web.converter.ForenameValueObjectConverter")
public class ForenameValueObjectConverter extends NameValueObjectConverter {

	@Override
	Forename getName(String nameValue) {
		return Forename.newInstance(nameValue);
	}
}