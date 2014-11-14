package com.epam.unittesting.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.unittesting.model.Account;

public class AccountDAO extends BankSystemDAO<Account>{

	public AccountDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account createObject(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUpdateQuery(Account object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAddQuery(Account object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
