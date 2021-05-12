package com.revature.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.revature.dao.ITaskDao;
import com.revature.dao.IUserDao;
import com.revature.dao.TaskDao;
import com.revature.dao.UserDao;
import com.revature.exceptions.DeleteFailedException;
import com.revature.exceptions.InsertFailedException;
import com.revature.exceptions.NoKnownUserException;
import com.revature.exceptions.PasswordIncorrectException;
import com.revature.exceptions.UsernameInUseException;
import com.revature.model.Task;
import com.revature.model.User;

@Service
@Component
public class UserService implements IUserService {

	@Autowired
	private UserDao userdao = new UserDao();
	


	public boolean register(User user) throws InsertFailedException, UsernameInUseException, PasswordIncorrectException{
		
		
		user.setAccountCreated();
		User temp = userdao.create(user);
		
		if(temp!= null)
		{
			System.out.println(user);
			return true;
			
		}
		else
		{
			throw new InsertFailedException(null, null);
		}
		
	}


	public User login(User user) throws NoKnownUserException,PasswordIncorrectException {
	
	
		
		User temp = userdao.login(user);
		
		
		
		if(temp!= null)
		{
			return temp;
		}
		else
		{
			throw new NoKnownUserException(null, null);
		}
		
	}
	
	public User update(User user) throws InsertFailedException{
		User temp = userdao.update(user);
		
		if(temp!= null)
		{
			return user;
		}
		else
		{
			throw new InsertFailedException(null, null);
		}
	}


	public boolean unregister(User user) throws DeleteFailedException {
		if(userdao.delete(user))
			{
				return true;
			}
		else
			throw new DeleteFailedException();
	}

	public String downloadMyData(String email , String password) {
		User user = null;
		return userdao.select(user).toString();
	}


	public boolean setDailyGoals(int numDesired) {
		return userdao.updateGoal(numDesired);
	}

	
	public Object viewProgress() {
		// TODO Auto-generated method stub
		return null;
	}


	public Object viewPastProgressGraph() {
		// TODO Auto-generated method stub
		return null;
	}

}