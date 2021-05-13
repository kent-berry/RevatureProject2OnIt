package com.revature.tests;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mock;

import com.revature.dao.UserDao;
import com.revature.exceptions.InsertFailedException;
import com.revature.exceptions.PasswordIncorrectException;
import com.revature.exceptions.UsernameInUseException;
import com.revature.model.User;
import com.revature.service.UserService;

public class UserServiceTest {
	
	@Mock
	UserDao userDao;
	
	@Test
	public void testRegister() {
		userDao = mock(UserDao.class);
		
		User user = new User();
		
		when(userDao.create(user)).thenReturn(user);
		
		UserService userService = new UserService();
		userService.setUserdao(userDao);
		
		try {
			assertTrue(userService.register(user));
		} catch (InsertFailedException e) {
			e.printStackTrace();
		} catch (UsernameInUseException e) {
			e.printStackTrace();
		} catch (PasswordIncorrectException e) {
			e.printStackTrace();
		}		
	}
}
