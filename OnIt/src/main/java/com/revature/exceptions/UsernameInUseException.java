package com.revature.exceptions;

public class UsernameInUseException extends Exception {
	public UsernameInUseException(String errorMessage, Throwable err)
	{
		super(errorMessage, err);
	}
}
