package com.revature.controller;


import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.revature.model.*;


public interface IUserController {

	// Authentication, deletion, download
	//User checkActiveSession();
	//Serializable register(DtoRegisterUser dtoRegisterUser);
	//User login(DtoLoginUser dtoLoginUser);
	//Boolean logout();
	//boolean updateUserInfo(DtoUpdatedUser dtoUpdatedUser)
	//boolean unregister(DtoPassword dtoPassword); //account deletion
	//String downloadMyData();
	// receiving email reminders
	//boolean receiveEmailReminders(DtoInteger dtoInteger);
	//Setting daily goals
	//boolean setDailyGoals(DtoInteger dtoInteger);
	
	// Creating, deleteing, and viewing tasks
	//Serializable createTask(DtoTask dtoTask);
	//boolean updateTask(DtoUpdatedTask dtoUpdatedTask);
	//boolean deleteTask(DtoString dtoString); 
	//List<Task> viewTasks ();
	
	// Completing a task, filtering based of completion
	//boolean completeTask(DtoUpdatedTask dtoUpdatedTask); 
	//List<Task> viewCompleted();
	
	
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