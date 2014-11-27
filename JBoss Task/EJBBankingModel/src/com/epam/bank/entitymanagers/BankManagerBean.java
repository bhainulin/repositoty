package com.epam.bank.entitymanagers;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.epam.bank.model.Bank;

@Stateless
@DeclareRoles("bean")
public class BankManagerBean implements BankManager {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Bank> getAll() {
		Query query = em.createQuery("from Bank");
        return query.getResultList();
	}

	@Override
	public Bank get(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RolesAllowed("bean")
	public void update(Bank object) {
        em.persist(object);
	}

	@Override
	@RolesAllowed("bean")
	public void delete(Integer key) {
		//em.remove();

	}

	@Override
	@RolesAllowed("bean")
	public void add(Bank bank) {
		 em.persist(bank);
	}

	@Override
	public List<Bank> getAllOrdered(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bank> filter(String propertyName, String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
