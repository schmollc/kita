package com.kita.web.converter;

import javax.faces.convert.FacesConverter;

import com.kita.attributes.Nickname;

/**
 * @since 15.10.2018
 *
 */
@FacesConverter("com.kita.web.converter.NicknameValueObjectConverter")
public class NicknameValueObjectConverter  extends NameValueObjectConverter {

	@Override
	Nickname getName(String nameValue) {
		return Nickname.newInstance(nameValue);
	}
}