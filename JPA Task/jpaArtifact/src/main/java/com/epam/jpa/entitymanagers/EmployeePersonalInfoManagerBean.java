package com.epam.jpa.entitymanagers;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.epam.jpa.model.EmployeePersonalInfo;

@Stateless
public class EmployeePersonalInfoManagerBean implements
		EmployeePersonalInfoManager {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeePersonalInfo> getAll() {
		Query query = em.createQuery("from EmployeePersonalInfo");
		return query.getResultList();
	}

	@Override
	public EmployeePersonalInfo get(int id) {
		EmployeePersonalInfo employee = em.find(EmployeePersonalInfo.class, id);
		return employee;
	}

	@Override
	public void delete(int id) {
		EmployeePersonalInfo employee = get(id);
		em.remove(employee);

	}

	@Override
	public void update(EmployeePersonalInfo employee) {
		em.merge(employee);
	}

	@Override
	public void add(EmployeePersonalInfo employeePersonalInfo) {
		em.persist(employeePersonalInfo);
		
	}

}
