package com.epam.bank.session;

import com.epam.bank.exceptions.IncorrectParametersException;
import com.epam.bank.exceptions.MissingParametersException;
import com.epam.bank.model.Account;
import com.epam.bank.model.Currency;

public interface BankOperationExchange {

	public Account exchange(Account account, Currency toCurrency)
			throws MissingParametersException, IncorrectParametersException;
}
