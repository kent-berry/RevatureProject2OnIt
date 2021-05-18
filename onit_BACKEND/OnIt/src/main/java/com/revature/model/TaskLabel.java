package com.revature.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="taskLabels")
public class TaskLabel {
	
	@Id
	private String taskLabel;
	
	private String taskLabelDescription;
	
	public TaskLabel() {
		super();
	}
	
	public TaskLabel(String taskLabel, String taskLabelDescription) {
		this.taskLabel = taskLabel;
		this.taskLabelDescription = taskLabelDescription;
	}
	
	

	public String getTaskLabel() {
		return taskLabel;
	}

	public void setTaskLabel(String taskLabel) {
		this.taskLabel = taskLabel;
	}

	public String getTaskLabelDescription() {
		return taskLabelDescription;
	}

	public void setTaskLabelDescription(String taskLabelDescription) {
		this.taskLabelDescription = taskLabelDescription;
	}
}