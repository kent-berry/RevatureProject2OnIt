package com.revature.dao;

import java.io.Serializable;
import java.util.List;

import com.revature.model.User;

public interface IUserDao {

	// Authentication, deletion, download
	Serializable insert(User newUser);
	User select(String email, String password);
	boolean delete(String email, String password); //account deletion, requires providing password to delete
	
	
	// receiving email reminders
	boolean updateEmailReminders(User loggedinUser); //0, 1, 2 days before
	
	//Setting daily goals
	boolean updateGoal(User loggedinUser);
	
	//update all user info
	boolean updateUserInfo(User updatedUser);
	List<User> getAllUser();

}