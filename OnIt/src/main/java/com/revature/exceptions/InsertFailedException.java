package com.revature.exceptions;

public class InsertFailedException extends Exception {
	public InsertFailedException(String errorMessage, Throwable err)
	{
		super(errorMessage, err);
	}

}
