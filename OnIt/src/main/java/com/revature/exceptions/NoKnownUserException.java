package com.revature.exceptions;

public class NoKnownUserException extends Exception {
	public NoKnownUserException(String errorMessage, Throwable err)
	{
		super(errorMessage, err);
	}

}
