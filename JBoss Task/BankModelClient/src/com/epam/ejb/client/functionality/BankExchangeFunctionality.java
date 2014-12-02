package com.epam.ejb.client.functionality;

import com.epam.bank.model.Account;
import com.epam.bank.model.Currency;
import com.epam.bank.session.BankOperationExchange;

public class BankExchangeFunctionality {
	private BankOperationExchange bankOperationExchange;

	public BankExchangeFunctionality(BankOperationExchange operation) {
		this.bankOperationExchange = operation;
	}

	public Account exchange(Account account, Currency currency) {
		return bankOperationExchange.exchange(account, currency);
	}
}
