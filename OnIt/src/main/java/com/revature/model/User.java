package com.revature.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.stereotype.Component;

@Component("User")
@Entity
@Table(name= "user_table")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	
	@Column(nullable = false)
	@Min(1)
	@Max(200)
	private String firstName;
	
	@Min(1)
	@Max(200)
	@Column(nullable = false)
	private String lastName;
	
	@Min(1)
	@Max(200)
	@Column(nullable = false, unique = true)
	private String email;
	
	@Min(1)
	@Max(200)
	@Column(nullable = false)
	private String password;
	
	@Min(0)
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
			Date date = new Date();
			this.accountCreated = new Timestamp(date.getTime());
		
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
