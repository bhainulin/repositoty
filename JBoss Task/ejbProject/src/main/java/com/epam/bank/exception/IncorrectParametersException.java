package com.epam.bank.exception;

public class IncorrectParametersException extends Exception{
	
	private static final long serialVersionUID = 4906611013394456184L;

	public IncorrectParametersException(String message){
		super(message);
	}

}
