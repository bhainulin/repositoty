package com.epam.unittesting.service.bank;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.epam.unittesting.dao.BankDAO;
import com.epam.unittesting.model.Bank;
import com.epam.unittesting.service.IBankModelFunctionality;
import com.epam.unittesting.utils.BankModelComparator;
import com.epam.unittesting.utils.PropertyValueChecker;

public class BankFunctionality implements IBankModelFunctionality<Bank> {

	private BankDAO bankDAO;

	public BankFunctionality(BankDAO bankDAO) {
		this.bankDAO = bankDAO;
	}

	public BankFunctionality() throws SQLException {
		this(new BankDAO());
	}

	@Override
	public List<Bank> order(String propertyName, int order) {
		BankModelComparator<Bank> bankComparator = new BankModelComparator<Bank>(propertyName, order);
		List<Bank> banks = getAll();	
		Collections.sort(banks, bankComparator);
		
		return banks;
	}

	@Override
	public List<Bank> search(String propertyName, Object value) {
		if(!PropertyValueChecker.hasProperty(propertyName, Bank.class)){
			throw new IllegalArgumentException("No such property.");
		}
		
		List<Bank> allBanks = getAll();
		List<Bank> result = new ArrayList<Bank>();
		
		for(Bank bank : allBanks){
			Object propertyValue = bank.getPropertyValue(propertyName);
			if(value == propertyValue){
				result.add(bank);
			}else if(value != null && value.equals(propertyValue)){
				result.add(bank);
			}else if(propertyValue!= null && propertyValue.equals(value)){
				result.add(bank);
			}
		}
		
		return result;
	}

	@Override
	public List<Bank> getAll() {
		List<Bank> banks;
		try {
			banks = bankDAO.getAll();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		if (banks == null) {
			banks = new ArrayList<Bank>();
		}
		return banks;
	}

	@Override
	public Bank get(Integer key) {
		try {
			return bankDAO.get(key);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

}
