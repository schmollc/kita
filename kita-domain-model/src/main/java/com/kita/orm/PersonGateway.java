package com.kita.orm;

import java.util.List;
import java.util.UUID;

import com.kita.Person;

/**
 * @since   18.05.2018
 *
 */
public interface PersonGateway {
	List<Person> getAll();

	Person get(UUID uuid);

	void set(Person person);

	void remove(UUID uuid);
}