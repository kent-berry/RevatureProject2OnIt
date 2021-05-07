package com.revature.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component("User")
@Entity
@Table(name= "user_table")
public class User {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	
	//@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "dailyTaskGoal")
	private int dailyTaskGoal;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	public int getDailyTaskGoal() {
		return dailyTaskGoal;
	}
	public void setDailyTaskGoal(int dailyTaskGoal) {
		this.dailyTaskGoal = dailyTaskGoal;
	}
	public Timestamp getAccountCreated() {
		return accountCreated;
	}
	public void setAccountCreated(Timestamp accountCreated) {
		this.accountCreated = accountCreated;
	}
	public int getReceiveEmailReminders() {
		return receiveEmailReminders;
	}
	public void setReceiveEmailReminders(int receiveEmailReminders) {
		this.receiveEmailReminders = receiveEmailReminders;
	}
	@Column(name = "accountCreated")
	private Timestamp accountCreated;
	
	@Column(name = "receiveEmailReminders")
	private int receiveEmailReminders;  

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	


}
