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
		Bank bank = em.find(Bank.class, key);
		return bank;
	}

	@Override
	@RolesAllowed("bean")
	public void add(Bank bank) {
		em.persist(bank);
	}

}
