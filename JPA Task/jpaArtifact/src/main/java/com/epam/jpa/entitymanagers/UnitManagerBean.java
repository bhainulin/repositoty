package com.epam.jpa.entitymanagers;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.epam.jpa.model.Employee;
import com.epam.jpa.model.Unit;

@Stateless
public class UnitManagerBean implements UnitManager {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Unit> getAll() {
		Query query = em.createQuery("from Unit");
		return query.getResultList();
	}

	@Override
	public Unit get(int id) {
		Unit unit = em.find(Unit.class, id);
		return unit;
	}

	@Override
	public void delete(int id) {
		Unit unit = get(id);
		em.remove(unit);

	}

	@Override
	public void update(Unit unit) {
		em.merge(unit);
	}

	@Override
	public void add(Unit unit) {
		em.persist(unit);

	}

	@Override
	public Collection<Employee> getEmployees(int id) {
		/*
		 * Unit unit = get(id);
		 * 
		 * Collection<Employee> employees = unit.getEmployees();
		 * employees.size(); return employees;
		 */
		return null;
	}
}
