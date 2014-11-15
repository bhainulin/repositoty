package com.epam.unittesting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.unittesting.model.Bank;
import com.epam.unittesting.model.BankObject;

public abstract class BankSystemDAO<T extends BankObject> implements IDAO<T>{

	public static String GET_ALL_ELEMENTS = "select * from %s where deleted=0";
	public static String GET_ELEMENT = "select * from %s where id=?";
	public static String UPDATE_ELEMENT = "update %s set %s where id=? and deleted=0";
	public static String DELETE_ELEMENT = "update %s set deleted=1 where id=?";
	
	
	private final Connection connection;

	public BankSystemDAO(Connection connection) {
		this.connection = connection;
	}
	
	/*public BankSystemDAO() {
		super();
	}*/

	public abstract String getTableName();

	public List<T> getAll() throws SQLException {
		String sqlString = String.format(GET_ALL_ELEMENTS, getTableName());
		PreparedStatement sql = connection.prepareStatement(sqlString);
		ResultSet result = sql.executeQuery();
		List<T> list = new ArrayList<T>();
		while (result.next()) {
			T object = createObject(result);
			list.add(object);
		}
		return list;
	}
	
	public T get(Integer key) throws SQLException {
		String sqlString = String.format(GET_ELEMENT, getTableName());
		PreparedStatement sql = connection.prepareStatement(sqlString);
		sql.setInt(1, key);
		ResultSet result = sql.executeQuery();
		T object = null;
		if (result.next()) {
			object = createObject(result);
		}
		return object;
	}
	
	public void update(T object) throws SQLException{
		String sqlString = String.format(UPDATE_ELEMENT, getTableName(), getUpdateQuery(object));
		PreparedStatement sql = connection.prepareStatement(sqlString);
		sql.setInt(1,object.getId());
		sql.executeUpdate();
	}
	
	
	@Override
	public void delete(Integer key) throws SQLException {
		String sqlString = String.format(DELETE_ELEMENT, getTableName());
		PreparedStatement sql = connection.prepareStatement(sqlString);
		sql.setInt(1,key);
		sql.executeUpdate();
		
	}

	@Override
	public void add(T object) throws SQLException {
		String sqlString = getAddQuery(object);
		PreparedStatement sql = connection.prepareStatement(sqlString);
		sql.executeUpdate();
	}
	
	public abstract T createObject(ResultSet resultSet) throws SQLException;

	public abstract String getUpdateQuery(T object) throws SQLException;
	
	public abstract String getAddQuery(T object) throws SQLException;
}
