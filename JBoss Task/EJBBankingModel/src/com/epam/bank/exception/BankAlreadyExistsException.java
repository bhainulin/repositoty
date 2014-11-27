package com.epam.bank.exception;

public class BankAlreadyExistsException extends Exception {

	public BankAlreadyExistsException(String string, String name) {
		super(string + name);
	}

}
