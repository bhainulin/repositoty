package com.epam.jpa.entitymanagers;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.epam.jpa.model.Employee;
import com.epam.jpa.model.Project;

@Stateless
public class EmployeeManagerBean implements EmployeeManager {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAll() {
		Query query = em.createQuery("from Employee");
		return query.getResultList();
	}

	@Override
	public Employee get(int id) {
		Employee employee = em.find(Employee.class, id);
		return employee;
	}

	@Override
	public void delete(int id) {
		Employee employee = get(id);
		em.remove(employee);

	}

	@Override
	public void update(Employee employee) {
		em.merge(employee);
	}

	@Override
	public void add(Employee employee) {
		em.persist(employee);
	}

	@Override
	public Collection<Project> getEmployeeProject(int id) {
		Employee employee = get(id);
		Collection<Project> projects = employee.getProjects();
		projects.size();
		return projects;
	}

}
