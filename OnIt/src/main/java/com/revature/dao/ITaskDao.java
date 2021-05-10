package com.revature.dao;

import java.time.LocalDate;
import java.util.List;

import com.revature.model.Task;

public interface ITaskDao {
	// Creating, deleteing, and viewing tasks
	boolean insert(Task task);
	boolean update(Task task);
	boolean delete(String taskId);
	List<Task> selectTasks (); //view all  
	
	// Completing a task, filtering based of completion
	boolean updateCompleteTask(String taskId);
	List<Task> selectCompleted(); //view completed
	
	// Labelling, filtering based on label
	boolean updateLabelTask(String taskId, String labelId);
	List<Task> selectLabel(String labelId);
	
	// Adding due date, filtering based on duedate
	boolean duedateTask(String taskId, LocalDate dueDate);
	boolean selectDuedate(LocalDate dueDate);
	
	
	// Assign repeatable/non repeatable
	boolean updateRepeatableTask(String taskId, boolean repeatable); //true or false
	
}
