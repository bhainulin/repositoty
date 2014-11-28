package com.epam.bank.entitymanagers;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.epam.bank.model.Person;

@Stateless
@DeclareRoles("bean")
public class PersonManagerBean implements PersonManager {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getAll() {
		Query query = em.createQuery("from Person");
		return query.getResultList();
	}

	@Override
	public Person get(Integer key) {
		Person person = em.find(Person.class, key);
		return person;
	}

	@Override
	@RolesAllowed("bean")
	public void update(Person object) {
		em.merge(object);
	}

	@Override
	@RolesAllowed("bean")
	public void add(Person person) {
		em.persist(person);
	}
}
