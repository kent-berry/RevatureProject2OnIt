package com.revature.dto;


public class DtoUpdatedUser {
	
	private String id;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String accountCreated;
	private int receiveEmailReminders;  
	private int goal;
	private String isPasswordChanging;
	
	public DtoUpdatedUser() {
		super();
	}

	public DtoUpdatedUser(String id, String firstName, String lastName, String email, String password,
			String accountCreated, int receiveEmailReminders, int goal, String isPasswordChanging) {
		super();
		this.id = id;
		this.firstname = firstName;
		this.lastname = lastName;
		this.email = email;
		this.password = password;
		this.accountCreated = accountCreated;
		this.receiveEmailReminders = receiveEmailReminders;
		this.goal = goal;
		this.setIsPasswordChanging(isPasswordChanging);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setnirstName(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public String getAccountCreated() {
		return accountCreated;
	}

	public void setAccountCreated(String accountCreated) {
		this.accountCreated = accountCreated;
	}

	public int getReceiveEmailReminders() {
		return receiveEmailReminders;
	}

	public void setReceiveEmailReminders(int receiveEmailReminders) {
		this.receiveEmailReminders = receiveEmailReminders;
	}

	public int getGoal() {
		return goal;
	}

	public void setGoal(int goal) {
		this.goal = goal;
	}

	public String getIsPasswordChanging() {
		return isPasswordChanging;
	}

	public void setIsPasswordChanging(String isPasswordChanging) {
		this.isPasswordChanging = isPasswordChanging;
	}
	
	
}
