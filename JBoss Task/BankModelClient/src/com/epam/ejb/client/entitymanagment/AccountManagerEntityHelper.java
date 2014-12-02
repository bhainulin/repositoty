package com.epam.ejb.client.entitymanagment;

import java.util.List;

import com.epam.bank.entitymanagers.AccountManager;
import com.epam.bank.model.Account;

public class AccountManagerEntityHelper {
	
	private AccountManager accountManager;
	
	public AccountManagerEntityHelper(AccountManager manager){
		this.accountManager = manager;
	}
	
	public List<Account> getAll(){
		return accountManager.getAll();
	}
	
	public Account get(int key){
		return accountManager.get(key);
	}
	
	public void add(Account account){
		accountManager.add(account);
	}
	
	public void update(Account account){
		accountManager.update(account);
	}
}
