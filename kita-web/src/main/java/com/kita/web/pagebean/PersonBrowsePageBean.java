package com.kita.web.pagebean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.kita.Person;
import com.kita.attributes.Forename;
import com.kita.attributes.Surename;
import com.kita.web.bridge.PersonBridge;
import com.kita.web.bridge.PersonBridgeImpl;

/**
 * @since 2.05.2018
 *
 */
@ManagedBean
@SessionScoped
public class PersonBrowsePageBean implements Serializable {
	private static final long serialVersionUID = -7684007777613912395L;

	private PersonBridge personBridge = null;
	private List<Person> searchResult = new ArrayList<>();

	public PersonBrowsePageBean() {
		personBridge = new PersonBridgeImpl();
		refreshPersons();
	}

	private PersonBridge getPersonBridge() {
		return personBridge;
	}

	void refreshPersons() {
		searchResult = getPersonBridge().all();
	}

	public List<Person> getPersons() {
		return searchResult;
	}

	public int sortByForename(Forename name1, Forename name2) {
		return Forename.sortByForename(name1, name2);
	}

	public int sortBySurename(Surename name1, Surename name2) {
		return Surename.sortBySurename(name1, name2);
	}

	public Integer getNumberOfResults() {
		return searchResult == null ? 0 : searchResult.size();
	}
}