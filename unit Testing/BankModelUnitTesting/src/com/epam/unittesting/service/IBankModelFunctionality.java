package com.epam.unittesting.service;

import java.util.List;

import com.epam.unittesting.model.BankObject;

public interface IBankModelFunctionality {
	
	public List<BankObject> order(String property);
	
	public List<BankObject> getAll();
	
	public BankObject get(Integer key);
	
	public List<BankObject> search(String propertyName, String value);
}
