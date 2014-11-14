package com.epam.unittesting.service;

import com.epam.unittesting.model.Account;
import com.epam.unittesting.model.CurrencyShortName;

public interface IBankOperations {
	
	public Account exchange(Account account, CurrencyShortName toCurrency);
	
	public Account transfer(Account from, Account to);
}
