package com.epam.bank.entitymanagers;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.epam.bank.model.Account;

@Stateless
@DeclareRoles("bean")
public class AccountManagerBean implements AccountManager {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getAll() {
		Query query = em.createQuery("from Account");
		return query.getResultList();
	}

	@Override
	public Account get(Integer key) {
		Account account = em.find(Account.class, key);
		return account;
	}

	@Override
	@RolesAllowed("bean")
	public void update(Account object) {
		em.merge(object);

	}

	@Override
	@RolesAllowed("bean")
	public void add(Account account) {
		em.persist(account);

	}
}
