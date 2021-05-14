package com.revature.service;

import java.util.List;
import java.io.Serializable;

import com.revature.model.*;

public interface IUserService {
	
	// Authentication, deletion, download
	Serializable register(User newUser);
	User login(String email, String password);
	User getUserById(String id);
	
	boolean unregister(String email, String password); //account deletion, requires providing password to delete
	String downloadMyData(String email, String password);
	
	// receiving email reminders
	boolean receiveEmailReminders(User loggedinUser); 
	
	//Setting daily goals
	boolean setDailyGoals(User loggedinUser);
		
	//Update any of the user info
	boolean updateUserInfo(User updatedUser);
	
	// Creating, deleteing, and viewing tasks
	Serializable createTask(Task task);
	boolean updateTask(Task task);
	boolean deleteTask(String taskId);
	List<Task> viewTasks (String userId); //view all  
	
	// Completing a task, filtering based of completion
	List<Task> viewCompleted(String userId); //view completed
	
	// Adding due date, filtering based on duedate
	List<Task> viewDuedate(String userId, String upperBoundDate);
	
}