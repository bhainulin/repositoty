package com.epam.ejb.client.functionality;

import java.util.List;

import com.epam.bank.model.Account;
import com.epam.bank.model.Currency;
import com.epam.bank.session.BankOperationTransfer;

public class BankTransferFunctionality {

	private BankOperationTransfer operationTransfer;

	public BankTransferFunctionality(BankOperationTransfer operation) {
		this.operationTransfer = operation;
	}

	public List<Account> transfer(Account from, Account to, Currency currency) {
		operationTransfer.transfer(from, to, currency);

		return operationTransfer.getAccounts();
	}
}
