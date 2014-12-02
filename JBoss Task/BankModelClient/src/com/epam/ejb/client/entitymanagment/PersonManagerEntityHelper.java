package com.epam.ejb.client.entitymanagment;

import java.util.List;

import com.epam.bank.entitymanagers.PersonManager;
import com.epam.bank.model.Person;

public class PersonManagerEntityHelper {
	
	private PersonManager personManager;
	
	public PersonManagerEntityHelper(PersonManager manager){
		this.personManager = manager;
	}
	
	public List<Person> getList(){
		return personManager.getAll();
	}
	
	public Person get(int key){
		return personManager.get(key);
	}
	
	public void add(Person person){
		personManager.add(person);
	}
	
	public void update(Person person){
		personManager.update(person);
	}
	
	
}
