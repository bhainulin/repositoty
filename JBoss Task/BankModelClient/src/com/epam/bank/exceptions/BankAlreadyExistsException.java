package com.epam.bank.exceptions;

public class BankAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1781593062515473840L;

	public BankAlreadyExistsException(String string, String name) {
		super(string + name);
	}

}
