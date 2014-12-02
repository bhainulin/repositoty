package com.epam.bank.session;

import java.util.List;

import javax.ejb.Remove;

import com.epam.bank.model.Account;
import com.epam.bank.model.Currency;

public interface BankOperationTransfer {
	public void transfer(Account from, Account to, Currency currency);

	@Remove
	public List<Account> getAccounts();
}
