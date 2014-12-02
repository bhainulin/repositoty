package com.epam.bank.entitymanagers;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.epam.bank.model.Currency;
import com.epam.bank.model.CurrencyShortName;

@Stateless
@DeclareRoles("bean")
public class CurrencyManagerBean implements CurrencyManager {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Currency> getAll() {
		Query query = em.createQuery("from Currency");
		return query.getResultList();
	}

	@Override
	public Currency get(Integer key) {
		Currency currency = em.find(Currency.class, key);
		return currency;
	}

	@Override
	@RolesAllowed("bean")
	public void add(Currency currency) {
		em.persist(currency);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Currency> find(int bankId, CurrencyShortName from,
			CurrencyShortName to) {
		Query q = em
				.createQuery("select c from Currency c where c.bankId=?1 and c.from=?2 and c.to=?3");
		q.setParameter(1, bankId);
		q.setParameter(2, from);
		q.setParameter(3, to);
		return q.getResultList();
	}

}
