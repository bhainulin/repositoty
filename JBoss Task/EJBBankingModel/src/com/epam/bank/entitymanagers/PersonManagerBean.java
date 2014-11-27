package com.epam.bank.entitymanagers;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.epam.bank.model.Person;

@Stateless
@DeclareRoles("bean")
public class PersonManagerBean implements PersonManager{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Person> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person get(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RolesAllowed("bean")
	public void update(Person object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RolesAllowed("bean")
	public void delete(Integer key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RolesAllowed("bean")
	public void add(Person bank) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Person> getAllOrdered(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> filter(String propertyName, String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
