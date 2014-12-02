package com.epam.bank.entitymanagers;

import java.util.List;

import javax.ejb.Remote;

import com.epam.bank.model.Currency;
import com.epam.bank.model.CurrencyShortName;

@Remote
public interface CurrencyManager {
	public List<Currency> getAll();

	public Currency get(Integer key);

	public void add(Currency bank);
	
	public List<Currency> find(int bankId, CurrencyShortName from, CurrencyShortName to);
}