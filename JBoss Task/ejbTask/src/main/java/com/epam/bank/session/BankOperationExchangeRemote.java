package com.epam.bank.session;

import javax.ejb.Remote;

import com.epam.bank.exception.IncorrectParametersException;
import com.epam.bank.exception.MissingParametersException;
import com.epam.bank.model.Account;
import com.epam.bank.model.Currency;

@Remote
public interface BankOperationExchangeRemote {

	public Account exchange(Account account, Currency toCurrency) throws MissingParametersException, IncorrectParametersException;
}
