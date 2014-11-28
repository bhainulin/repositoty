package com.epam.bank.session;

import javax.annotation.security.DeclareRoles;
import javax.ejb.Stateless;

import com.epam.bank.exception.IncorrectParametersException;
import com.epam.bank.exception.MissingParametersException;
import com.epam.bank.model.Account;
import com.epam.bank.model.Currency;

@Stateless(name = "BankOperationExchangeBean")
@DeclareRoles("bean")
public class BankOperationExchangeBean implements BankOperationExchangeRemote,
		BankOperationExchangeLocal {

	public BankOperationExchangeBean() {
	}

	@Override
	public Account exchange(Account account, Currency currency)
			throws MissingParametersException, IncorrectParametersException {
		if (account == null || currency == null) {
			throw new MissingParametersException("Incorrect input parameters.");
		}
		if (account.getBankId() != currency.getBankId()) {
			throw new IncorrectParametersException(
					"Account is not created in the bank.");
		}

		if (account.getCurrency() != currency.getFrom()) {
			throw new IncorrectParametersException(
					"Account is not in needed currency.");
		}

		Double amount = account.getAmount() / currency.getCost();
		account.setAmount(amount);
		account.setCurrency(currency.getTo());
		return account;
	}

}
