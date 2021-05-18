package com.revature.dto;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class DtoUserWithoutDate {

	
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private int receiveEmailReminders;  
	private int goal;
	
	String sessionToken;
	
	
	//
	public DtoUserWithoutDate() {
		super();
	}
	
	
	//Getters and setters
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	


	
	public int getGoal() {
		return goal;
	}
	
	public void setGoal(int dailyTaskGoal) {
		this.goal = dailyTaskGoal;
	}
	
	public int getReceiveEmailReminders() {
		return receiveEmailReminders;
	}

	public void setReceiveEmailReminders(int receiveEmailReminders) {
		this.receiveEmailReminders = receiveEmailReminders;
	}
	
	


	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	
}