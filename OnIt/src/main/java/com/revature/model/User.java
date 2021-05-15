package com.revature.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	private String id;
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private LocalDate accountCreated;
	private int receiveEmailReminders;  
	private int goal;
	
	String sessionToken;
	
	
	//constructors
	public User() {
		super();
	}
	
	/*
	 * This is the constructor used when REGISTERING a user
	 */
	public User(String firstName, String lastName, String email, String password) {
		super();
		
		this.id = UUID.randomUUID().toString();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		
		this.accountCreated = LocalDate.now();
	}
	
	
	/*
	 * This is the constructor used when creating a Java version of a User object
	 */
	public User(String id, String firstName, String lastName, String email, String password, LocalDate accountCreated,
			int receiveEmailReminders, int goal) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.accountCreated = accountCreated;
		this.receiveEmailReminders = receiveEmailReminders;
		this.goal = goal;
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
	
	public LocalDate getAccountCreated() {
		return accountCreated;
	}


	public void setAccountCreated(LocalDate accountCreated) {
		this.accountCreated = accountCreated;
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

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", accountCreated=" + accountCreated + ", receiveEmailReminders=" + receiveEmailReminders + ", goal="
				+ goal + "]";
	}
}