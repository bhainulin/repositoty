package com.epam.unittesting.service;

import java.util.List;

import com.epam.unittesting.model.BankObject;

public interface IBankModelFunctionality<T extends BankObject> {
	
	public List<T> order(String property, int order);
	
	public List<T> getAll();
	
	public T get(Integer key);
	
	public List<T> search(String propertyName, Object value);
}
