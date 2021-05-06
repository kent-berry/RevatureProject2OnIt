package com.revature.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component("Task")
@Entity
@Table(name= "task_table")

public class Task {
	
	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int ID;

	@Column(name = "fkuserid")
	@JoinColumn(name = "userid")
	String userID;
	
	String taskName;
	
	String notes;
	
	Timestamp dueDate;
	
	@Column(name = "fklabelid")
	@JoinColumn(name = "labelid")
	int labelId;
	
	Timestamp dateCreated;
	
	Timestamp dateCompleted;
	
	int reminder;
	
	boolean repeatable; 



}
