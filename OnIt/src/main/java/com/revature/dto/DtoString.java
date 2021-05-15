package com.revature.dto;

import org.springframework.stereotype.Component;

@Component
public class DtoString {
	private String formString;

	public DtoString() {
		super();
	}

	public String getFormString() {
		return formString;
	}

	public void setFormString(String formString) {
		this.formString = formString;
	}
	
	
}
