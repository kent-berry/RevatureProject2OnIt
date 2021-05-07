package com.revature.dao;

import com.revature.model.User;

public interface IUserDao {

	// Authentication, deletion, download
    boolean insert(User user);
	User select(User user);
	boolean delete(String email, String password); //account deletion, requires providing password to delete
	
	
	// receiving email reminders
	boolean updateEmailReminders(int reminderPeriod); //0, 1, 2 days before
	
	//Setting daily goals
	boolean updateGoal(int numDesired);

}
