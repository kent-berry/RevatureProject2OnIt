package com.revature.model;

import java.sql.Timestamp;
import java.util.Calendar;

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
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int ID;

	@Column(name = "fkuserid", nullable = false)
	@JoinColumn(name = "userid")
	String userID;
	
	@Column(nullable = false)
	String taskName;
	
	String notes;
	
	Timestamp dueDate;
	
	String labelName;
	
	@Column(nullable = false)
	Timestamp dateCreated = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	
	Timestamp dateCompleted;
	
	int reminder;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean repeatable;

	
	@Override
	public String toString() {
		return "Task [ID=" + ID + ", userID=" + userID + ", taskName=" + taskName + ", notes=" + notes + ", dueDate="
				+ dueDate + ", labelId=" + labelName + ", dateCreated=" + dateCreated + ", dateCompleted=" + dateCompleted
				+ ", reminder=" + reminder + ", repeatable=" + repeatable + "]";
	}


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getTaskName() {
		return taskName;
	}


	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public Timestamp getDueDate() {
		return dueDate;
	}


	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}


	public String getLabelID() {
		return labelName;
	}


	public void setLabelID(String labelName) {
		this.labelName = labelName;
	}


	public Timestamp getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}


	public Timestamp getDateCompleted() {
		return dateCompleted;
	}


	public void setDateCompleted(Timestamp dateCompleted) {
		this.dateCompleted = dateCompleted;
	}


	public int getReminder() {
		return reminder;
	}


	public void setReminder(int reminder) {
		this.reminder = reminder;
	}


	public boolean isRepeatable() {
		return repeatable;
	}


	public void setRepeatable(boolean repeatable) {
		this.repeatable = repeatable;
	}

	
	
}
