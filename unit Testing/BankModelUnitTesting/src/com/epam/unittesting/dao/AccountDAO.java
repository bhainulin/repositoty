package com.epam.unittesting.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.unittesting.model.Account;
import com.epam.unittesting.model.CurrencyShortName;

public class AccountDAO extends BankSystemDAO<Account> {

	public static final String UPDATE = "personId=%d, bankId='%d', currency='%s', amount=%f";
	public static final String QUOTES = "'%s'";
	public static final String INSERT_PART = "INSERT INTO `test`.`tblaccount` (`personId`, `bankId`, `currency`, `amount`) ";
	public static final String VALUES_PART = "VALUES ('%d', '%d', '%s', '%f')";

	public AccountDAO(Connection connection) {
		super(connection);
	}

	public AccountDAO() throws SQLException {
		super(MySqlDAOFactory.getInstance().getConnection());
	}

	@Override
	public String getTableName() {
		return "tblAccount";
	}

	@Override
	public Account createObject(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt(Account.OBJECT_ID);
		int bankId = resultSet.getInt(Account.BANK_ID);
		int personId = resultSet.getInt(Account.PERSON_ID);
		String currName = resultSet.getString("currency");
		double amount = resultSet.getDouble(Account.AMOUNT);
		CurrencyShortName currency = CurrencyShortName.valueOf(currName);
		Account account = new Account(id, personId, bankId, currency, amount);
		return account;
	}

	@Override
	public String getUpdateQuery(Account account) throws SQLException {
		String update = String
				.format(UPDATE, account.getPersonId(), account.getBankId(),
						account.getCurrency(), account.getAmount());
		return update;
	}

	@Override
	public String getAddQuery(Account account) throws SQLException {
		String add = String
				.format(INSERT_PART, account.getPersonId(),
						account.getBankId(), account.getCurrency(),
						account.getAmount());
		return VALUES_PART + add;
	}

}
