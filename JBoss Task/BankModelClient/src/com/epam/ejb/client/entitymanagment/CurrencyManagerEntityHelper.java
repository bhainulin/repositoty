package com.epam.ejb.client.entitymanagment;

import java.util.List;

import com.epam.bank.entitymanagers.CurrencyManager;
import com.epam.bank.model.Currency;
import com.epam.bank.model.CurrencyShortName;

public class CurrencyManagerEntityHelper {

	private CurrencyManager currencyManager;

	public CurrencyManagerEntityHelper(CurrencyManager manager) {
		this.currencyManager = manager;
	}

	public List<Currency> getAll() {
		return currencyManager.getAll();
	}

	public Currency get(int key) {
		return currencyManager.get(key);
	}

	public void add(Currency currency) {
		currencyManager.add(currency);
	}

	public List<Currency> find(int bankId, CurrencyShortName from,
			CurrencyShortName to) {
		return currencyManager.find(bankId, from, to);
	}
}
