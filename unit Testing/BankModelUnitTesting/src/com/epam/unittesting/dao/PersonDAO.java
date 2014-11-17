package com.epam.unittesting.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.unittesting.model.BankObject;
import com.epam.unittesting.model.Person;

public class PersonDAO extends BankSystemDAO<Person> {

	public static final String UPDATE = " nickname='%s', first='%s', last='%s', city=%s, phone=%s";
	public static final String QUOTES = "'%s'";
	
	public static final String INSERT_PART = "INSERT INTO `test`.`tblperson` (`nickname`, `firstname`, `lastname`, `city`, `phone`) ";
	public static final String VALUES_PART = "VALUES ('%s', '%s', '%s', %s, %s) ";

	public PersonDAO(Connection connection) {
		super(connection);
	}

	public PersonDAO() throws SQLException {
		this(MySqlDAOFactory.getInstance().getConnection());
	}

	@Override
	public String getTableName() {
		return "tblPerson";
	}

	@Override
	public Person createObject(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt(BankObject.OBJECT_ID);
		String nick = resultSet.getString("nickname");
		String first = resultSet.getString("firstname");
		String last = resultSet.getString("lastname");
		String city = resultSet.getString(Person.CITY);
		String phone = resultSet.getString(Person.PHONE);

		Person person = new Person(id, nick, first, last, city, phone);

		return person;
	}

	@Override
	public String getUpdateQuery(Person person) throws SQLException {
		String phone = person.getPhone() == null ? "null" : String.format(
				QUOTES, person.getPhone());
		String city = person.getCity() == null ? "null" : person.getCity()
				.toString();
		String updateQuery = String.format(UPDATE, person.getNickName(),
				person.getFirstName(), person.getLastName(), city, phone);
		return updateQuery;
	}

	@Override
	public String getAddQuery(Person person) throws SQLException {
		String phone = person.getPhone() == null ? "null" : String.format(
				QUOTES, person.getPhone());
		String city = person.getCity() == null ? "null" : person.getCity()
				.toString();
		
		String addPart = String.format(VALUES_PART, person.getNickName(), person.getFirstName(), person.getLastName(), city, phone);
		return INSERT_PART + addPart;
	}

}
