package com.revature.dto;

import org.springframework.stereotype.Component;

@Component
public class DtoUserPasswordSessionKey {

	
	private String email;
	private String password;
	private String sessionToken;
	
	
	public DtoUserPasswordSessionKey() {
		super();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSessionToken() {
		return sessionToken;
	}
	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	
	
	
}
