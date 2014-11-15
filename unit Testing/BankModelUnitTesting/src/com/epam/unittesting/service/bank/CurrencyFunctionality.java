package com.epam.unittesting.service.bank;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.epam.unittesting.dao.CurrencyDAO;
import com.epam.unittesting.model.Currency;
import com.epam.unittesting.service.IBankModelFunctionality;
import com.epam.unittesting.utils.BankModelComparator;
import com.epam.unittesting.utils.PropertyValueChecker;

public class CurrencyFunctionality implements IBankModelFunctionality<Currency> {

	private CurrencyDAO currencyDAO;

	public CurrencyFunctionality(CurrencyDAO currencyDAO) {
		this.currencyDAO = currencyDAO;
	}

	public CurrencyFunctionality() throws SQLException {
		this(new CurrencyDAO());
	}

	@Override
	public List<Currency> order(String propertyName, int order) {
		BankModelComparator<Currency> bankComparator = new BankModelComparator<Currency>(
				propertyName, order);
		List<Currency> banks = getAll();
		Collections.sort(banks, bankComparator);

		return banks;
	}

	@Override
	public List<Currency> search(String propertyName, Object value) {
		if (!PropertyValueChecker.hasProperty(propertyName, Currency.class)) {
			throw new IllegalArgumentException("No such property.");
		}

		List<Currency> allCurrencies = getAll();
		List<Currency> result = new ArrayList<Currency>();

		for (Currency currency : allCurrencies) {
			Object propertyValue = currency.getPropertyValue(propertyName);
			if (value == propertyValue) {
				result.add(currency);
			} else if (value != null && value.equals(propertyValue)) {
				result.add(currency);
			} else if (propertyValue != null && propertyValue.equals(value)) {
				result.add(currency);
			}
		}
		return result;
	}

	@Override
	public List<Currency> getAll() {
		List<Currency> currencies;
		try {
			currencies = currencyDAO.getAll();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		if (currencies == null) {
			currencies = new ArrayList<Currency>();
		}
		return currencies;
	}

	@Override
	public Currency get(Integer key) {
		try {
			return currencyDAO.get(key);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

}
