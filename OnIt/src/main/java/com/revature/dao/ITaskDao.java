package com.revature.dao;

import java.io.Serializable;
import java.util.List;

import com.revature.model.Task;

public interface ITaskDao {
	// Creating, deleteing, and viewing tasks
	Serializable insert(Task task);
	boolean update(Task task);
	boolean delete(String taskId);
	List<Task> selectTasks (String userId); //view all  
	
	// Completing a task, filtering based of completion
	List<Task> selectCompleted(String userId); //view completed
	
	// Labelling, filtering based on label
	boolean updateLabelTask(String taskId, String labelId);
	List<Task> selectLabel(String labelId);
	
	// Adding due date, filtering based on duedate
	List<Task> selectDuedate(String userId, String upperBoundDate);
	
}
