package com.revature.dto;

import org.springframework.stereotype.Component;

@Component
public class DtoUserSessionKey {

	private String id;
	private String email;
	private String sessionToken;
	
	public DtoUserSessionKey() {
		super();
		// 
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSessionToken() {
		return sessionToken;
	}
	public void setSessionKey(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	
	
	
}
