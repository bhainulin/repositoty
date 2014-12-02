package com.epam.bank.exceptions;

public class MissingParametersException extends Exception {

	private static final long serialVersionUID = -5487300729273366244L;

	public MissingParametersException(String message) {
		super(message);
	}
}