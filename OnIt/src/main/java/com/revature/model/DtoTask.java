package com.revature.model;

import java.time.LocalDate;

public class DtoTask {
	
	private String taskName;
	private String notes;
	private int reminder;
	private boolean repeatable;
	
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

	@Override
	public String toString() {
		return "DtoTask [taskName=" + taskName + ", notes=" + notes +  ", reminder=" + reminder
				+ ", repeatable=" + repeatable + "]";
	}
		
}
