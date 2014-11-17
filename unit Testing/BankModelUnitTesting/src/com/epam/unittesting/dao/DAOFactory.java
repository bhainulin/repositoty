package com.epam.unittesting.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DAOFactory {
	public Connection getConnection() throws SQLException;
}
