package com.revature.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.ITaskDao;
import com.revature.dao.IUserDao;
import com.revature.dao.TaskDao;
import com.revature.dao.UserDao;
import com.revature.dto.DtoUser;
import com.revature.model.Task;
import com.revature.model.User;

@Service("UserService")
public class UserService implements IUserService {

	@Autowired
	private IUserDao userdao = new UserDao();
	
	@Autowired
	private ITaskDao taskdao = new TaskDao();
	
	@Override
	public Serializable register(User newUser) {
		return userdao.insert(newUser);
	}

	@Override
	public User login(String email, String password) {
		return userdao.select(email, password);
	}

	@Override
	public User getUserById(String id) {
		return userdao.select(id);
	}

	
	@Override
	public boolean unregister(String email, String password) {
		return userdao.delete(email, password);
	}

	@Override
	public String downloadMyData(String email , String password) {
		String returnString = "";
		returnString = returnString + userdao.select(email, password).toString() + "\n\nYour Tasks:\n\n";
		
		List<Task> userTasks = userdao.selectTasksFromEmail(email);
		
		/*for (Task t: userTasks) {
			returnString = returnString + t.toString() +"\n";
		}*/
				
		

		
		return returnString;
		
	}

	@Override
	public boolean receiveEmailReminders(User loggedinUser) {
		return userdao.updateEmailReminders(loggedinUser);
	}

	@Override
	public boolean setDailyGoals(User loggedinUser) {
		return userdao.updateGoal(loggedinUser);
	}
	
	@Override
	public boolean updateUserInfo(User updatedUser) {
		return userdao.updateUserInfo(updatedUser);
	}
	
	@Override
	public Serializable createTask(Task task) {
		return taskdao.insert(task);
	}

	@Override
	public boolean updateTask(Task task) {
		return taskdao.update(task);
	}

	@Override
	public boolean deleteTask(String taskId) {
		return taskdao.delete(taskId);
	}

	@Override
	public List<Task> viewTasks(String userId) {
		return taskdao.selectTasks(userId);
	}

	@Override
	public List<Task> viewCompleted(String userId) {
		return taskdao.selectCompleted(userId);
	}


	@Override
	public List<Task> viewDuedate(String userId, String upperBoundDate) {
		return taskdao.selectDuedate(userId, upperBoundDate);
	}
	
	
	
	public User generateSessionToken(User u) {
		
		boolean foundUniqueToken = false;
		int maxTokenGenerationAttempts = 3;
		int tokenGenerationAttempt = 0;
		
		while (!foundUniqueToken && tokenGenerationAttempt < maxTokenGenerationAttempts) {	
			/*
			 * Generate a unique token
			 */
			String token = Long.toString(Math.round(100000000 + Math.random() * 900000000));
			u.setSessionToken(token);
			foundUniqueToken = userdao.updateUserInfo(u);
			maxTokenGenerationAttempts++;
			
		}
		
		if (!foundUniqueToken) {
			u.setSessionToken(null);
		}
		
		return u;
	}
	
	public boolean deleteSessionToken(User user) {
		
		userdao.deleteSessionToken(user);
		return true;
		
	}
	
	
	public User getUserFromSessionToken(String sessionToken) {

		User user = userdao.findUserFromSessionToken(sessionToken);
		return user;
		
	}

	
}