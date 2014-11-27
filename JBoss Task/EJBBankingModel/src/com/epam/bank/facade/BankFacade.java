package com.epam.bank.facade;
 
import java.util.List;

import javax.ejb.*;

import com.epam.bank.dao.BankDAO;
import com.epam.bank.exception.BankAlreadyExistsException;
import com.epam.bank.exception.BankNotFoundException;
import com.epam.bank.model.Bank;
 
@Stateless
@LocalBean
public class BankFacade {
 
    @EJB
    private BankDAO bankDAO;
 
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Bank findById(Long id){
        Bank bank = bankDAO.find(id);
        return bank;
    }
 
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Bank> findAll() {
        return bankDAO.findAll();
    }
 
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(String name) throws BankAlreadyExistsException {
        if (bankDAO.findByName(name) != null) throw new BankAlreadyExistsException("Gallery already exits", name);
        //Bank gallery = new Bank(name);
       // bankDAO.persist(gallery);
    }
 
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remove(Long id) throws BankNotFoundException {
        Bank bank = findById(id);
        bankDAO.remove(bank);
    }
}
