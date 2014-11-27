package com.epam.bank.entitymanagers;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.epam.bank.model.Currency;

@Stateless
@DeclareRoles("bean")
public class CurrencyManagerBean implements CurrencyManager{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Currency> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Currency get(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RolesAllowed("bean")
	public void update(Currency object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RolesAllowed("bean")
	public void delete(Integer key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RolesAllowed("bean")
	public void add(Currency bank) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Currency> getAllOrdered(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Currency> filter(String propertyName, String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
