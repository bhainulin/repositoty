package com.epam.jpa.entitymanagers;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import com.epam.jpa.model.Employee;
import com.epam.jpa.model.Project;

@Remote
public interface ProjectManager {
	public List<Project> getAll();

	public Project get(int id);

	public void delete(int id);

	public void update(Project project);
	
	public void add(Project project);
	
	public Collection<Employee> getEmployees(int id);
}
