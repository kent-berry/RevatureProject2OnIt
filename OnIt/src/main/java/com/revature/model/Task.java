package com.revature.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
	
	@Id
	private String id;
	
	//Label as a foreign key
	private String userId;
	
	private String taskName;
	private String notes;
	
	private LocalDate dateCreated;
	private LocalDate dueDate;
	private LocalDate dateCompleted;
	
	private int reminder;
	private boolean repeatable;
	
	private String taskLabel_fk;
	
	private Double latitude;
	private Double longitude;
	
	//Constructors
	
	public Task() {
		super();
	}
	
	public Task(String userId, String taskName, String notes, LocalDate dueDate, int reminder,
			boolean repeatable) {
		super();
		
		this.id = UUID.randomUUID().toString();
		
		this.userId = userId;
		this.taskName = taskName;
		this.notes = notes;
		
		
		this.dateCreated = LocalDate.now(); 
		this.dueDate = dueDate;
		
		this.reminder = reminder;
		this.repeatable = repeatable;
	}


	public Task(String userId, String taskName, String notes, LocalDate dueDate, int reminder,
			boolean repeatable, String taskLabel, Double latitude, Double longitude) {
		super();
		
		this.id = UUID.randomUUID().toString();
		
		this.userId = userId;
		this.taskName = taskName;
		this.notes = notes;
		
		
		this.dateCreated = LocalDate.now(); 
		this.dueDate = dueDate;
		
		this.reminder = reminder;
		this.repeatable = repeatable;
		this.taskLabel_fk = taskLabel;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	
	
	public Task(String id, String userId, String taskName, String notes, LocalDate dateCreated, LocalDate dueDate,
			LocalDate dateCompleted, int reminder, boolean repeatable) {
		super();
		this.id = id;
		this.userId = userId;
		this.taskName = taskName;
		this.notes = notes;
		this.dateCreated = dateCreated;
		this.dueDate = dueDate;
		this.dateCompleted = dateCompleted;
		this.reminder = reminder;
		this.repeatable = repeatable;
	}

	public Task(String id, String userId, String taskName, String notes, LocalDate dateCreated, LocalDate dueDate,
			LocalDate dateCompleted, int reminder, boolean repeatable, String taskLabel_fk, Double latitude, Double longitude) {
		super();
		this.id = id;
		this.userId = userId;
		this.taskName = taskName;
		this.notes = notes;
		this.dateCreated = dateCreated;
		this.dueDate = dueDate;
		this.dateCompleted = dateCompleted;
		this.reminder = reminder;
		this.repeatable = repeatable;
		this.taskLabel_fk = taskLabel_fk;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	//Getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDate getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(LocalDate dateCompleted) {
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

	
	public String getTaskLabel_fk() {
		return taskLabel_fk;
	}

	public void setTaskLabel_fk(String taskLabel_fk) {
		this.taskLabel_fk = taskLabel_fk;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Task name:  " + taskName + "\nDescription: " + notes
				+ "\nCreated on: " + dateCreated + "\nDue on: " + dueDate + "\nCompleted on: " + dateCompleted
				+ "\nGetting email reminders for this task " + reminder + " days before it is due.\n"
						+ "Weekly repeatable = " + repeatable + "\nCategory: " + taskLabel_fk
				+ "\nLocation: latitude = " + latitude + ", longitude=" + longitude;
	}

	
		
}