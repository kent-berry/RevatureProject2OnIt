package com.revature.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component("User")
@Entity
@Table(name= "user_table")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	private int dailyTaskGoal;
	
	@Column(nullable = false) 
	private Timestamp accountCreated;
	
	@Transient
	private List<Task> tasks = null;
	
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
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
	public void setAccountCreated() {
		
		if(this.accountCreated == null)
		{
			this.accountCreated = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		}
		
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "User [ID=" + ID + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", dailyTaskGoal=" + dailyTaskGoal + ", accountCreated=" + accountCreated
				+ ", tasks="  + "]";
	}
	


}
