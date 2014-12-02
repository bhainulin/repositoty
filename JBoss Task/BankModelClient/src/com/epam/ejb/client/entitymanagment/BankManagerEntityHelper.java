package com.epam.ejb.client.entitymanagment;

import java.util.List;

import com.epam.bank.entitymanagers.BankManager;
import com.epam.bank.model.Bank;

public class BankManagerEntityHelper {
	
	private BankManager bankManager;
	
	public BankManagerEntityHelper(BankManager manager){
		this.bankManager = manager;
	}
	
	public List<Bank> getAll(){
		return bankManager.getAll();
	}
	
	public Bank get(int key){
		return bankManager.get(key);
	}
	
	public void add(Bank bank){
		bankManager.add(bank);
	}
}
