package com.epam.bank.entitymanagers;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.epam.bank.model.Account;

@Stateless
@DeclareRoles("bean")
public class AccountManagerBean implements AccountManager {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Account> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account get(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RolesAllowed("bean")
	public void update(Account object) {
		// TODO Auto-generated method stub

	}

	@Override
	@RolesAllowed("bean")
	public void delete(Integer key) {
		// TODO Auto-generated method stub

	}

	@Override
	@RolesAllowed("bean")
	public void add(Account bank) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Account> getAllOrdered(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> filter(String propertyName, String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
