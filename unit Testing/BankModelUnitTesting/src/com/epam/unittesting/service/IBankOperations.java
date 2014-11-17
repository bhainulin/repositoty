package com.epam.unittesting.service;

import java.sql.SQLException;

import com.epam.unittesting.model.Account;
import com.epam.unittesting.model.Currency;
import com.epam.unittesting.model.CurrencyShortName;

public interface IBankOperations {

	public Account exchange(Account account, CurrencyShortName toCurrency) throws SQLException;

	public Account transfer(Account from, Account to) throws SQLException;

	public Currency findExchange(Integer bankId, CurrencyShortName from,
			CurrencyShortName to) throws SQLException;
}
