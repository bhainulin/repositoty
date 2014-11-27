package com.epam.bank.entitymanagers;

import java.util.List;

import javax.ejb.Remote;

import com.epam.bank.model.Account;

@Remote
public interface AccountManager {
	public List<Account> getAll();

	public Account get(Integer key);

	public void update(Account object);

	public void delete(Integer key);

	public void add(Account bank);

	public List<Account> getAllOrdered(String propertyName);

	public List<Account> filter(String propertyName, String value);
}