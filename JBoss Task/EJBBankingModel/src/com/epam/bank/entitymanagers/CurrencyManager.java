package com.epam.bank.entitymanagers;

import java.util.List;

import javax.ejb.Remote;

import com.epam.bank.model.Currency;

@Remote
public interface CurrencyManager {
	public List<Currency> getAll();

	public Currency get(Integer key);

	public void update(Currency object);

	public void delete(Integer key);

	public void add(Currency bank);

	public List<Currency> getAllOrdered(String propertyName);

	public List<Currency> filter(String propertyName, String value);
}