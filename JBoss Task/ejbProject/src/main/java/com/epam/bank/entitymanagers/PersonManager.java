package com.epam.bank.entitymanagers;

import java.util.List;

import javax.ejb.Remote;

import com.epam.bank.model.Person;

@Remote
public interface PersonManager {
	public List<Person> getAll();

	public Person get(Integer key);

	public void update(Person object);

	public void add(Person bank);
}