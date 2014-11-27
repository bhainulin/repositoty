package com.epam.bank.entitymanagers;

import java.util.List;

import javax.ejb.Remote;

import com.epam.bank.model.Bank;

@Remote
public interface BankManager {
		public List<Bank> getAll();
		public Bank get(Integer key);
		public void update(Bank object);
		public void delete(Integer key);
		public void add(Bank bank);	
		public List<Bank> getAllOrdered(String propertyName);	
		public List<Bank> filter(String propertyName, String value);
}
