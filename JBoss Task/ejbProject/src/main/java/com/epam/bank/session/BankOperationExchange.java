package com.epam.bank.session;

import com.epam.bank.model.Account;
import com.epam.bank.model.Currency;

public interface BankOperationExchange {

	public Account exchange(Account account, Currency toCurrency);
}
