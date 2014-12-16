package com.epam.jpa.entitymanagers;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import com.epam.jpa.model.Employee;
import com.epam.jpa.model.Project;

@Remote
public interface EmployeeManager {
	
	public List<Employee> getAll();
	
	public Employee get(int id);
	
	public void delete(int id);
	
	public void update(Employee employee);
	
	public void add(Employee employee);
	
	public Collection<Project> getEmployeeProject(int id);
}
