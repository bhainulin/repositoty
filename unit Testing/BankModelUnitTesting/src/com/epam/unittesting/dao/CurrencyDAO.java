package com.epam.unittesting.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.unittesting.model.Currency;
import com.epam.unittesting.model.CurrencyShortName;

public class CurrencyDAO extends BankSystemDAO<Currency>{
	
	public static final String UPDATE = "bankId=%d, from='%s', to='%s', cost=%f";
	public static final String QUOTES = "'%s'";	
	public static final String INSERT_PART = "INSERT INTO `test`.`tblcurrency` (`bankId`, `from`, `to`, `cost`) ";
	public static final String VALUES_PART = "VALUES ('%d', '%s', '%s', '%f')";
	
	public CurrencyDAO(Connection connection) {
		super(connection);
	}
	
	public CurrencyDAO() throws SQLException {
		super(MySqlDAOFactory.getInstance().getConnection());
	}

	@Override
	public String getTableName() {
		return "tblcurrency";
	}

	@Override
	public Currency createObject(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt(Currency.OBJECT_ID);
		int bankId = resultSet.getInt(Currency.BANK_ID);
		String fromName = resultSet.getString(Currency.FROM_CURRENCY);
		String toName = resultSet.getString(Currency.TO_CURRENCY);
		double cost = resultSet.getDouble(Currency.CURRENCY_COST);
		
		CurrencyShortName from = CurrencyShortName.valueOf(fromName);
		CurrencyShortName to = CurrencyShortName.valueOf(toName);

		Currency currency = new Currency(id, bankId, from, to, cost);

		return currency;
	}

	@Override
	public String getUpdateQuery(Currency currency) throws SQLException {
		String update = String.format(UPDATE,currency.getBankId(), currency.getFromCurrency(), currency.getToCurrency(), currency.getCurrencyCost());
		return update;
	}

	@Override
	public String getAddQuery(Currency currency) throws SQLException {
		String add = String.format(INSERT_PART, currency.getBankId(), currency.getFromCurrency(), currency.getToCurrency(), currency.getCurrencyCost());
		return VALUES_PART + add;
	}

}
