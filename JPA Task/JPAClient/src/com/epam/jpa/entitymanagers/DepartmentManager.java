package com.epam.jpa.entitymanagers;

import javax.ejb.Remote;

@Remote
public interface DepartmentManager {

	public void addEmployeeToUnit(int employeeId, int unitId);

	public void assignEmployeeToProject(int employeeId, int projectId);
}
