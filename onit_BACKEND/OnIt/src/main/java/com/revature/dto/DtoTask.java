package com.revature.dto;

import org.springframework.stereotype.Component;

@Component
public class DtoTask {
	
	private String taskName;
	private String notes;
	
	private int dueDateMonth;
	private int dueDateDay;
	private int dueDateYear;
	
	private int reminder;
	private boolean repeatable;
	private String taskLabel;
	private Double latitude;
	private Double longitude;
	
	private String userId;
	private String sessionToken;
	
	
	public DtoTask() {
		super();
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
	
	

	public String getTaskLabel() {
		return taskLabel;
	}

	public void setTaskLabel(String taskLabel) {
		this.taskLabel = taskLabel;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	
	
	public int getDueDateMonth() {
		return dueDateMonth;
	}

	public void setDueDateMonth(int dueDateMonth) {
		this.dueDateMonth = dueDateMonth;
	}

	public int getDueDateDay() {
		return dueDateDay;
	}

	public void setDueDateDay(int dueDateDay) {
		this.dueDateDay = dueDateDay;
	}

	public int getDueDateYear() {
		return dueDateYear;
	}

	public void setDueDateYear(int dueDateYear) {
		this.dueDateYear = dueDateYear;
	}

	


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	@Override
	public String toString() {
		return "DtoTask [taskName=" + taskName + ", notes=" + notes + ", dueDateMonth=" + dueDateMonth + ", dueDateDay="
				+ dueDateDay + ", dueDateYear=" + dueDateYear + ", reminder=" + reminder + ", repeatable=" + repeatable
				+ ", taskLabel=" + taskLabel + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	

	
}