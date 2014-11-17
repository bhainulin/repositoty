package com.epam.unittesting.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDAOFactory implements DAOFactory {

	private static String user = "root";
	private static String password = "12345";
	private static String url = "jdbc:mysql://localhost:3306/test";
	private static String driver = "com.mysql.jdbc.Driver";
	
	private static MySqlDAOFactory instance;

	public MySqlDAOFactory() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
	public static MySqlDAOFactory getInstance(){
		if(instance == null){
			instance = new MySqlDAOFactory();
		}
		return instance;
	}
	
}
