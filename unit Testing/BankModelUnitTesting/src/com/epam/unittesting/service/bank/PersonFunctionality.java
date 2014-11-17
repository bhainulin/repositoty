package com.epam.unittesting.service.bank;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.epam.unittesting.dao.PersonDAO;
import com.epam.unittesting.model.Person;
import com.epam.unittesting.service.IBankModelFunctionality;
import com.epam.unittesting.utils.BankModelComparator;
import com.epam.unittesting.utils.PropertyValueChecker;

public class PersonFunctionality implements IBankModelFunctionality<Person> {

	private PersonDAO personDAO;

	public PersonFunctionality(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	public PersonFunctionality() throws SQLException {
		this(new PersonDAO());
	}

	@Override
	public List<Person> order(String propertyName, int order) {
		BankModelComparator<Person> personComparator = new BankModelComparator<Person>(
				propertyName, order);
		List<Person> persons = getAll();
		Collections.sort(persons, personComparator);
		return persons;
	}

	@Override
	public List<Person> search(String propertyName, Object value) {
		if (!PropertyValueChecker.hasProperty(propertyName, Person.class)) {
			throw new IllegalArgumentException("No such property.");
		}

		List<Person> allPersons = getAll();
		List<Person> result = new ArrayList<Person>();

		for (Person person : allPersons) {
			Object propertyValue = person.getPropertyValue(propertyName);
			if (value == propertyValue) {
				result.add(person);
			} else if (value != null && value.equals(propertyValue)) {
				result.add(person);
			} else if (propertyValue != null && propertyValue.equals(value)) {
				result.add(person);
			}
		}

		return result;
	}

	@Override
	public List<Person> getAll() {
		List<Person> persons;
		try {
			persons = personDAO.getAll();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		if (persons == null) {
			persons = new ArrayList<Person>();
		}
		return persons;
	}

	@Override
	public Person get(Integer key) {
		try {
			return personDAO.get(key);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

}
