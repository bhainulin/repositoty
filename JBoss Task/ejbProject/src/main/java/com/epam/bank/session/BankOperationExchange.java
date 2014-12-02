package com.epam.bank.session;

import com.epam.bank.exception.IncorrectParametersException;
import com.epam.bank.exception.MissingParametersException;
import com.epam.bank.model.Account;
import com.epam.bank.model.Currency;

public interface BankOperationExchange {

	public Account exchange(Account account, Currency toCurrency)
			throws MissingParametersException, IncorrectParametersException;
}
