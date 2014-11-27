package com.epam.bank.dao;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.epam.bank.model.Bank;

public class BankDAO extends AbstractDAO<Bank>{

	@PersistenceContext(unitName = "PersistenceUnit")
    private EntityManager entityManager;
 
    public BankDAO() {
        super(Bank.class);
    }
 
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
 
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Bank> findAll() {
        return namedQuery(Bank.QUERY_FIND_ALL).getResultList();
    }
 
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Bank findByName(String name) {
        return namedQuery(Bank.QUERY_FIND_BY_NAME)
                .setParameter("name", name)
                .getSingleResult();
    }   

}
