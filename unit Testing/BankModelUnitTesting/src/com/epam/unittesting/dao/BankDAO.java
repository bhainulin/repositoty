package com.epam.unittesting.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.unittesting.model.Bank;
import com.epam.unittesting.model.BankObject;

public class BankDAO extends BankSystemDAO<Bank>{

	public static final String UPDATE = " name='%s', city='%s', street='%s', building=%d, phone=%s, office=%s";
	public static final String QUOTES = "'%s'";

	public BankDAO(Connection connection) {
		super(connection);
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
		String phone = bank.getPhone() == null ? "null" : String.format(QUOTES, bank.getPhone());
		String office = bank.getOffice() == null ? "null" :  bank.getOffice().toString();
		String updateQuery = String.format(UPDATE, bank.getName(), bank.getCity(), bank.getStreet(), bank.getBuilding(), phone, office);
		return updateQuery;
	}
	
	/*public static void main(String [] args) throws SQLException{
		MySqlDAOFactory factory = new MySqlDAOFactory();
		BankDAO bankDAO = new BankDAO(factory.getConnection());
		System.out.println(bankDAO.get(1));
	}*/
	
	@Override
	public String getAddQuery(Bank object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
