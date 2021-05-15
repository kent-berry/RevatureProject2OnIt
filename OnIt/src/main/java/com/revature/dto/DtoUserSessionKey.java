package com.revature.dto;

public class DtoUserSessionKey {

	private String id;
	private String email;
	private String sessionToken;
	
	
	
	public DtoUserSessionKey(String id, String email, String sessionKey) {
		super();
		this.email = email;
		this.sessionToken = sessionToken;
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
