package com.epam.jpa.entitymanagers;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import com.epam.jpa.model.Employee;
import com.epam.jpa.model.Unit;

@Remote
public interface UnitManager {
	public List<Unit> getAll();

	public Unit get(int id);

	public void delete(int id);

	public void update(Unit unit);

	public void add(Unit unit);

	public Collection<Employee> getEmployees(int id);
}
