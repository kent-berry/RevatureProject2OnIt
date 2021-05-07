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
	String firstName;
	
	@Column(name = "lastName")
	String lastName;
	
	@Column(name = "email")
	String email;
	
	@Column(name = "password")
	String password;
	
	@Column(name = "dailyTaskGoal")
	int dailyTaskGoal;
	
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
