package com.revature.tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import com.revature.dao.TaskDao;
import com.revature.exceptions.NoKnownUserException;
import com.revature.model.Task;
import com.revature.service.TaskService;
import com.revature.service.UserService;

public class TaskServiceTest {
	
	@Mock
	TaskDao taskDao;
	
	@Test
	public void testGetTask() {
		taskDao = mock(TaskDao.class);
		
		List<Task> list = new ArrayList<Task>();
		Task t = new Task();
		
		when(taskDao.selectTasks(1)).thenReturn(list);
		when(taskDao.get(1)).thenReturn(t);
		
		TaskService taskService = new TaskService();
		taskService.setTaskDao(taskDao);
		
		try {
			assertEquals(taskService.getTask(1, "all"), list);
		} catch (NoKnownUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			list.add(t);
			assertEquals(taskService.getTask(1, "").get(0), list.get(0));
		} catch (NoKnownUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
