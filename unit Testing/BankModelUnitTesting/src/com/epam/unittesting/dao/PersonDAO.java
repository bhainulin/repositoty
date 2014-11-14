package com.epam.unittesting.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.unittesting.model.Person;

public class PersonDAO extends BankSystemDAO<Person> {

	public PersonDAO(Connection connection) {
		super(connection);
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person createObject(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUpdateQuery(Person object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAddQuery(Person object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
