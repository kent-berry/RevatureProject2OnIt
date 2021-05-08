package com.revature.exceptions;

public class PasswordIncorrectExecption extends Exception {
	public PasswordIncorrectExecption(String errorMessage, Throwable err)
	{
		super(errorMessage, err);
	}

}
