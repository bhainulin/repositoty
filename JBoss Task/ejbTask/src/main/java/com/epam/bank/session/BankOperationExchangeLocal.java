package com.epam.bank.session;

import javax.ejb.Local;

import com.epam.bank.exception.IncorrectParametersException;
import com.epam.bank.exception.MissingParametersException;
import com.epam.bank.model.Account;
import com.epam.bank.model.Currency;

@Local
public interface BankOperationExchangeLocal {

	public Account exchange(Account account, Currency toCurrency) throws MissingParametersException, IncorrectParametersException;
}
