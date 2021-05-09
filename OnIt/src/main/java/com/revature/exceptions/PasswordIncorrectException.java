package com.revature.exceptions;

public class PasswordIncorrectException extends Exception {
	public PasswordIncorrectException(String errorMessage, Throwable err)
	{
		super(errorMessage, err);
	}

}
