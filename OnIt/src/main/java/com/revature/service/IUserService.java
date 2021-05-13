package com.revature.service;

import java.util.List;
import java.io.Serializable;
import java.time.LocalDate;
import com.revature.model.*;


public interface IUserService {
	
	// Authentication, deletion, download
	Serializable register(User newUser);
	User login(String email, String password);

	boolean unregister(String email, String password); //account deletion, requires providing password to delete
	String downloadMyData(String email, String password);
	
	// receiving email reminders
	boolean receiveEmailReminders(User loggedinUser); 
	
	//Setting daily goals
	boolean setDailyGoals(User loggedinUser);
		
	// Creating, deleteing, and viewing tasks
	Serializable createTask(Task task);
	boolean updateTask(Task task);
	boolean deleteTask(String taskId);
	List<Task> viewTasks (); //view all  
	
	// Completing a task, filtering based of completion
	boolean completeTask(String taskId);
	List<Task> viewCompleted(); //view completed
	
	
	// Labelling, filtering based on label
	boolean labelTask(String taskId, String labelId);
	List<Task> viewLabel(String labelId);
	
	// Adding due date, filtering based on duedate
	boolean duedateTask(String taskId, LocalDate dueDate);
	boolean viewDuedate(LocalDate dueDate);
			
	// Assign repeatable/non repeatable
	boolean SetRepeatableTask(String taskId, boolean repeatable); //true or false
	
	
	Object viewProgress();
	
	//viewing graph
	Object viewPastProgressGraph();
}