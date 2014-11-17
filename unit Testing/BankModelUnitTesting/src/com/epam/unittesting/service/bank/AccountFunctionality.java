package com.epam.unittesting.service.bank;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.epam.unittesting.dao.AccountDAO;
import com.epam.unittesting.model.Account;
import com.epam.unittesting.service.IBankModelFunctionality;
import com.epam.unittesting.utils.BankModelComparator;
import com.epam.unittesting.utils.PropertyValueChecker;

public class AccountFunctionality implements IBankModelFunctionality<Account> {

	private AccountDAO accountDAO;

	public AccountFunctionality(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	public AccountFunctionality() throws SQLException {
		this(new AccountDAO());
	}

	@Override
	public List<Account> order(String propertyName, int order) {
		BankModelComparator<Account> accountComparator = new BankModelComparator<Account>(
				propertyName, order);
		List<Account> banks = getAll();
		Collections.sort(banks, accountComparator);

		return banks;
	}

	@Override
	public List<Account> search(String propertyName, Object value) {
		if (!PropertyValueChecker.hasProperty(propertyName, Account.class)) {
			throw new IllegalArgumentException("No such property.");
		}

		List<Account> allAccounts = getAll();
		List<Account> result = new ArrayList<Account>();

		for (Account account : allAccounts) {
			Object propertyValue = account.getPropertyValue(propertyName);
			if (value == propertyValue) {
				result.add(account);
			} else if (value != null && value.equals(propertyValue)) {
				result.add(account);
			} else if (propertyValue != null && propertyValue.equals(value)) {
				result.add(account);
			}
		}
		return result;
	}

	@Override
	public List<Account> getAll() {
		List<Account> accounts;
		try {
			accounts = accountDAO.getAll();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		if (accounts == null) {
			accounts = new ArrayList<Account>();
		}
		return accounts;
	}

	@Override
	public Account get(Integer key) {
		try {
			return accountDAO.get(key);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
}
