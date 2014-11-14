package com.epam.unittesting.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {
	
	//gets all non deleted elements
	public List<T> getAll() throws SQLException;

	//gets element by key
	public T get(Integer key) throws SQLException;

	//updates object
	public void update(T object) throws SQLException;

	//deletes object
	public void delete(Integer key) throws SQLException;

	//adds new object
	public void add(T t) throws SQLException;
}
