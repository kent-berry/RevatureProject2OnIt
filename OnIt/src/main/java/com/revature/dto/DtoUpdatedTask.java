package com.revature.dto;


public class DtoUpdatedTask {

	private String id;
	private String userId;
	private String sessionToken;
	
	private String taskName;
	private String notes;
	
	private Integer dueDateMonth;
	private Integer dueDateDay;
	private Integer dueDateYear;
	
	private Integer createdMonth;
	private Integer createdDay;
	private Integer createdYear;
	
	private Integer completedMonth;
	private Integer completedDay;
	private Integer completedYear;
	
	private int reminder;
	private boolean repeatable;
	
	private String taskLabel_fk;
	private double latitude;
	private double longitude;
	
	public DtoUpdatedTask() {
		super();
	}

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


	public Integer getDueDateMonth() {
		return dueDateMonth;
	}

	public void setDueDateMonth(Integer dueDateMonth) {
		this.dueDateMonth = dueDateMonth;
	}

	public Integer getDueDateDay() {
		return dueDateDay;
	}

	public void setDueDateDay(Integer dueDateDay) {
		this.dueDateDay = dueDateDay;
	}

	public Integer getDueDateYear() {
		return dueDateYear;
	}

	public void setDueDateYear(Integer dueDateYear) {
		this.dueDateYear = dueDateYear;
	}

	public Integer getCreatedMonth() {
		return createdMonth;
	}

	public void setCreatedMonth(Integer createdMonth) {
		this.createdMonth = createdMonth;
	}

	public Integer getCreatedDay() {
		return createdDay;
	}

	public void setCreatedDay(Integer createdDay) {
		this.createdDay = createdDay;
	}

	public Integer getCreatedYear() {
		return createdYear;
	}

	public void setCreatedYear(Integer createdYear) {
		this.createdYear = createdYear;
	}

	public Integer getCompletedMonth() {
		return completedMonth;
	}

	public void setCompletedMonth(Integer completedMonth) {
		this.completedMonth = completedMonth;
	}

	public Integer getCompletedDay() {
		return completedDay;
	}

	public void setCompletedDay(Integer completedDay) {
		this.completedDay = completedDay;
	}

	public Integer getCompletedYear() {
		return completedYear;
	}

	public void setCompletedYear(Integer completedYear) {
		this.completedYear = completedYear;
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

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	
	

}