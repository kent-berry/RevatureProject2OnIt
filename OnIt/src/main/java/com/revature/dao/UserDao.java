package com.revature.dao;

import org.springframework.stereotype.Component;

import com.revature.model.User;

@Component
public class UserDao implements IUserDao {

	@Override
	public boolean insert(String firstName, String lastName, String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User select(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEmailReminders(int reminderPeriod) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateGoal(int numDesired) {
		// TODO Auto-generated method stub
		return false;
	}

}
