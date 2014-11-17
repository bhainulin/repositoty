package com.epam.unittesting.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.unittesting.dao.AccountDAO;
import com.epam.unittesting.dao.CurrencyDAO;
import com.epam.unittesting.model.Account;
import com.epam.unittesting.model.Currency;
import com.epam.unittesting.model.CurrencyShortName;

public class BankOperations implements IBankOperations {

	private CurrencyDAO currencyDAO;
	private AccountDAO accountDAO;

	public BankOperations(CurrencyDAO currencyDAO, AccountDAO accountDAO) {
		this.currencyDAO = currencyDAO;
		this.accountDAO = accountDAO;
	}

	public BankOperations() throws SQLException {
		this.currencyDAO = new CurrencyDAO();
		this.accountDAO = new AccountDAO();
	}

	@Override
	public Account exchange(Account account, CurrencyShortName toCurrency)
			throws SQLException {
		if (account == null || toCurrency == null) {
			throw new IllegalArgumentException("Incorrect input parameters.");
		}

		Account existingAccount = accountDAO.get(account.getId());

		if (existingAccount == null) {
			throw new IllegalArgumentException("Unexisting account.");
		}

		Currency currency = findExchange(existingAccount.getBankId(),
				existingAccount.getCurrency(), toCurrency);

		if (currency == null) {
			return null;
		}

		Double amount = existingAccount.getAmount()
				/ currency.getCurrencyCost();
		existingAccount.setAmount(amount);
		existingAccount.setCurrency(toCurrency);
		accountDAO.update(existingAccount);

		return existingAccount;
	}

	@Override
	public Account transfer(Account from, Account to) throws SQLException {
		if (from == null || to == null) {
			throw new IllegalArgumentException("Incorrect input parameters.");
		}
		Account accountFrom = accountDAO.get(from.getId());
		Account accountTo = accountDAO.get(to.getId());

		if (accountFrom == null || accountTo == null) {
			throw new IllegalArgumentException("Unexisting accounts.");
		}

		// cannot transfer between different people
		if (!accountFrom.getPersonId().equals(accountTo.getPersonId())) {
			throw new IllegalArgumentException(
					"Cannot transfer betwwen different people.");
		}

		// cannot transfer between different banks
		if (!accountFrom.getBankId().equals(accountTo.getBankId())) {
			throw new IllegalArgumentException(
					"Cannot transfer betwwen different banks.");
		}

		Currency currency = findExchange(accountFrom.getBankId(),
				accountFrom.getCurrency(), accountTo.getCurrency());
		if (currency == null) {
			return null;
		}

		Double amount = accountFrom.getAmount() / currency.getCurrencyCost();

		accountFrom.setAmount(0d);
		accountTo.setAmount(accountTo.getAmount() + amount);

		accountDAO.update(accountTo);
		accountDAO.update(accountFrom);

		return accountTo;
	}

	@Override
	public Currency findExchange(Integer bankId, CurrencyShortName from,
			CurrencyShortName to) throws SQLException {
		if (bankId == null || from == null || to == null) {
			throw new IllegalArgumentException("Incorrect input parameters.");
		}
		List<Currency> currencies = currencyDAO.getAll();

		for (Currency currency : currencies) {
			boolean isEqual = bankId.equals(currency.getBankId())
					&& from == currency.getFromCurrency()
					&& to == currency.getToCurrency();

			if (isEqual) {
				// bank is not allowed to have more than one currency exchange
				return currency;
			}
		}
		return null;
	}

}
