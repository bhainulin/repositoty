package com.epam.unittesting.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.unittesting.model.Currency;

public class CurrencyDAO extends BankSystemDAO<Currency>{

	public CurrencyDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}
	
	public CurrencyDAO() throws SQLException {
		super(MySqlDAOFactory.getInstance().getConnection());
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Currency createObject(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUpdateQuery(Currency object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAddQuery(Currency object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
