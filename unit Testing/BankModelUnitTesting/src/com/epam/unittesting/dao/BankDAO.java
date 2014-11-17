package com.epam.unittesting.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.unittesting.model.Bank;
import com.epam.unittesting.model.BankObject;

public class BankDAO extends BankSystemDAO<Bank> {

	public static final String UPDATE = " name='%s', city='%s', street='%s', building=%d, phone=%s, office=%s";
	public static final String QUOTES = "'%s'";
		
	public static final String INSERT_PART = "INSERT INTO `test`.`tblbank` (`name`, `city`, `street`, `building`, `office`, `phone`) ";
	public static final String VALUES_PART = "VALUES ('%s', '%s', '%s', %d, %s, %s) ";

	public BankDAO(Connection connection) {
		super(connection);
	}

	public BankDAO() throws SQLException {
		this(MySqlDAOFactory.getInstance().getConnection());
	}

	@Override
	public String getTableName() {
		return "tblbank";
	}

	@Override
	public Bank createObject(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt(BankObject.OBJECT_ID);
		String name = resultSet.getString(Bank.BANK_NAME);
		String city = resultSet.getString(Bank.CITY);
		String street = resultSet.getString(Bank.BANK_STREET);
		int building = resultSet.getInt(Bank.BANK_BUILDING);
		int office = resultSet.getInt(Bank.BANK_OFFICE);
		String phone = resultSet.getString(Bank.PHONE);

		Bank bank = new Bank(id, name, city, street, building, office, phone);

		return bank;
	}

	@Override
	public String getUpdateQuery(Bank bank) throws SQLException {
		String phone = bank.getPhone() == null ? "null" : String.format(QUOTES,
				bank.getPhone());
		String office = bank.getOffice() == null ? "null" : bank.getOffice()
				.toString();
		String updateQuery = String.format(UPDATE, bank.getName(),
				bank.getCity(), bank.getStreet(), bank.getBuilding(), phone,
				office);
		return updateQuery;
	}

	@Override
	public String getAddQuery(Bank bank) throws SQLException {
		String phone = bank.getPhone() == null ? "null" : String.format(QUOTES,
				bank.getPhone());
		String office = bank.getOffice() == null ? "null" : bank.getOffice()
				.toString();
		
		String addPart = String.format(VALUES_PART, bank.getName(), bank.getCity(), bank.getStreet(), bank.getBuilding(), office, phone);
		return INSERT_PART + addPart;
	}

}
