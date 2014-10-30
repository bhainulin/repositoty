package com.epam.module4.functionality.filereader;

import com.epam.module4.model.Person;

public class PersonParser extends AbstractParser{

	// Person@1@Alex@First@Last@Minsk@+375293958542

	@Override
	public Person parse(String[] values) {
		if (values.length != 7) {
			throw new IllegalArgumentException("Incorrect Person row");
		}

		for (int i = 1; i < 5; i++) {
			if (!validateNotEmptyValue(values[i])) {
				throw new IllegalArgumentException("Requied field is empty.");
			}
		}

		Integer id = parseInteger(values[1]);
		Person person = new Person(id, values[2], values[3], values[4]);

		String city = values[5];
		if (city != null && !city.isEmpty()) {
			person.setCity(city);
		}

		String phone = values[6];
		if (phone != null && !phone.isEmpty()) {
			person.setPhone(phone);
		}

		System.out.println(person.toString());

		return person;
	}

}
