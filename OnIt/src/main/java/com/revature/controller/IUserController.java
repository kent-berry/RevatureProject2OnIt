package com.revature.controller;


import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.revature.model.*;


public interface IUserController {

	// Authentication, deletion, download
	//Serializable register(HttpServletRequest request);
	//User login(HttpServletRequest request);
	//String logout();
	//boolean unregister(Password password); //account deletion
	//String downloadMyData();
	// receiving email reminders
	boolean receiveEmailReminders(HttpServletRequest request);
	//Setting daily goals
	boolean setDailyGoals(HttpServletRequest request);
	
	// Creating, deleteing, and viewing tasks
	boolean createTask(HttpServletRequest request);
	boolean updateTask(HttpServletRequest request);
	boolean deleteTask(HttpServletRequest request);
	List<Task> viewTasks (HttpServletRequest request);
	
	// Completing a task, filtering based of completion
	boolean completeTask(HttpServletRequest request);
	List<Task> viewCompleted(HttpServletRequest request);
	
	
	// Labelling, filtering based on label
	boolean labelTask(HttpServletRequest request);
	List<Task> viewLabel(HttpServletRequest request);
	
	// Adding due date, filtering based on duedate
	boolean duedateTask(HttpServletRequest request);
	boolean viewDuedate(HttpServletRequest request);
	
	
	// Assign repeatable/non repeatable
	boolean setRepeatableTask(HttpServletRequest request);

	
	//viewing graph
	Object viewProgress(HttpServletRequest request);
	
	//viewing graph
	Object viewPastProgressGraph(HttpServletRequest request);
	
}