package com.epam.bank.exceptions;

public class BankModelBeanException extends Exception {

	private static final long serialVersionUID = -2264902947711790723L;

	public BankModelBeanException(String message) {
		super(message);
	}

	public BankModelBeanException(String message, Throwable exception) {
		super(message, exception);
	}

	public BankModelBeanException(Throwable exception) {
		super(exception);
	}

}
