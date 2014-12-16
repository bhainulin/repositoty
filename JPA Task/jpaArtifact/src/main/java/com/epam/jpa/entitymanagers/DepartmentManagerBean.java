package com.epam.jpa.entitymanagers;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.epam.jpa.model.Employee;
import com.epam.jpa.model.Project;
import com.epam.jpa.model.Unit;

@Stateless
public class DepartmentManagerBean implements DepartmentManager {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void addEmployeeToUnit(int employeeId, int unitId) {
		Unit unit = em.find(Unit.class, unitId);
		Employee employee = em.find(Employee.class, employeeId);
		if (unit != null && employee != null) {
			employee.setUnit(unit);
			em.persist(employee);
		}
	}

	@Override
	public void assignEmployeeToProject(int employeeId, int projectId) {
		Project project = em.find(Project.class, projectId);
		Employee employee = em.find(Employee.class, employeeId);
		if (project != null && employee != null) {
			Collection<Project> projects = employee.getProjects();
			if (!projects.contains(project)) {
				projects.add(project);
				employee.setProjects(projects);
				em.merge(employee);
			}
		}
	}
}
