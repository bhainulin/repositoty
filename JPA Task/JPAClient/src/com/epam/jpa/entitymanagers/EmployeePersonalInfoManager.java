package com.epam.jpa.entitymanagers;

import java.util.List;

import javax.ejb.Remote;

import com.epam.jpa.model.EmployeePersonalInfo;

@Remote
public interface EmployeePersonalInfoManager {
	public List<EmployeePersonalInfo> getAll();

	public EmployeePersonalInfo get(int id);

	public void delete(int id);

	public void update(EmployeePersonalInfo employeePersonalInfo);
	
	public void add(EmployeePersonalInfo employeePersonalInfo);
}
