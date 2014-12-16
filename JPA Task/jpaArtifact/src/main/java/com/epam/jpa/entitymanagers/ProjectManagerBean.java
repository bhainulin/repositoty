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
public class ProjectManagerBean implements ProjectManager {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getAll() {
		Query query = em.createQuery("from Project");
		return query.getResultList();
	}

	@Override
	public Project get(int id) {
		Project project = em.find(Project.class, id);
		return project;
	}

	@Override
	public void delete(int id) {
		Project project = get(id);
		em.remove(project);

	}

	@Override
	public void update(Project project) {
		em.merge(project);
	}

	@Override
	public void add(Project project) {
		em.persist(project);

	}

	@Override
	public Collection<Employee> getEmployees(int id) {
		Project project = get(id);
		Collection<Employee> employees = project.getEmployees();
		employees.size();
		return employees;
	}
}
