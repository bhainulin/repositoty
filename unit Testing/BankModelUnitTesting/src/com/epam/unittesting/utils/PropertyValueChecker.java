package com.epam.unittesting.utils;

import java.util.HashSet;
import java.util.Set;

import com.epam.unittesting.model.Account;
import com.epam.unittesting.model.Bank;
import com.epam.unittesting.model.BankObject;
import com.epam.unittesting.model.Currency;
import com.epam.unittesting.model.Person;

public class PropertyValueChecker {

	private static final Set<String> BANK_PROPERTY_SET;
	private static final Set<String> PERSON_PROPERTY_SET;
	private static final Set<String> ACCOUNT_PROPERTY_SET;
	private static final Set<String> CURRENCY_PROPERTY_SET;

	static {
		BANK_PROPERTY_SET = new HashSet<>();
		BANK_PROPERTY_SET.add(BankObject.OBJECT_ID);
		BANK_PROPERTY_SET.add(Bank.BANK_NAME);
		BANK_PROPERTY_SET.add(Bank.CITY);
		BANK_PROPERTY_SET.add(Bank.BANK_STREET);
		BANK_PROPERTY_SET.add(Bank.BANK_BUILDING);
		BANK_PROPERTY_SET.add(Bank.BANK_OFFICE);
		BANK_PROPERTY_SET.add(Bank.PHONE);

		PERSON_PROPERTY_SET = new HashSet<>();
		PERSON_PROPERTY_SET.add(Person.OBJECT_ID);
		PERSON_PROPERTY_SET.add(Person.NICK_NAME);
		PERSON_PROPERTY_SET.add(Person.FIRST_NAME);
		PERSON_PROPERTY_SET.add(Person.LAST_NAME);
		PERSON_PROPERTY_SET.add(Person.CITY);
		PERSON_PROPERTY_SET.add(Person.PHONE);
		
		ACCOUNT_PROPERTY_SET = new HashSet<>();
		ACCOUNT_PROPERTY_SET.add(Account.OBJECT_ID);
		ACCOUNT_PROPERTY_SET.add(Account.BANK_ID);
		ACCOUNT_PROPERTY_SET.add(Account.PERSON_ID);
		ACCOUNT_PROPERTY_SET.add(Account.CURRENCY);
		ACCOUNT_PROPERTY_SET.add(Account.AMOUNT);
		
		CURRENCY_PROPERTY_SET = new HashSet<>();
		CURRENCY_PROPERTY_SET.add(Currency.OBJECT_ID);
		CURRENCY_PROPERTY_SET.add(Currency.BANK_ID);
		CURRENCY_PROPERTY_SET.add(Currency.FROM_CURRENCY);
		CURRENCY_PROPERTY_SET.add(Currency.TO_CURRENCY);
		CURRENCY_PROPERTY_SET.add(Currency.CURRENCY_COST);
	}

	

	public static final boolean hasProperty(String propertyName, Class<?> clazz) {
		if(clazz == null){
			return false;
		}
		if(clazz == Bank.class){
			return BANK_PROPERTY_SET.contains(propertyName);
		}else if(clazz == Person.class){
			return PERSON_PROPERTY_SET.contains(propertyName);
		}else if(clazz == Currency.class){
			return CURRENCY_PROPERTY_SET.contains(propertyName);
		}else if(clazz == Account.class){
			return ACCOUNT_PROPERTY_SET.contains(propertyName);
		}
		return false;
	}
}
