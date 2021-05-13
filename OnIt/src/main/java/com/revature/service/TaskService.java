package com.revature.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.revature.dao.ITaskDao;
import com.revature.dao.TaskDao;
import com.revature.dao.UserDao;
import com.revature.exceptions.NoKnownUserException;

import com.revature.model.Task;
@Service
@Component
public class TaskService {

	@Autowired
	private TaskDao taskdao = new TaskDao();
	
	public void setTaskDao(TaskDao taskDao) {
		this.taskdao = taskDao;
	}
	

	public List<Task> createTask(Task task) throws NoKnownUserException {
		if(taskdao.insert(task))
		{
			return taskdao.selectTasks(task.getUserID());
		}
		else
			return null;
		 
		
	}

	public boolean deleteTask(Task incomingTask)  throws NoKnownUserException{
		
		
		if(taskdao.delete(incomingTask))
		{
			return true;
		}
		
		throw new NoKnownUserException(null , null);
	}

	public List<Task> getTask(int id, String mode)  throws NoKnownUserException{
		List<Task> temp = new ArrayList<Task>(); //Kent changed this to "= new ArrayList<Task>();" from "= null;" cause you cant add to a null list homie
		if(mode.contains("all"))
		{
			return taskdao.selectTasks(id);
		}
		else
		{
			temp.add(taskdao.get(id));
			return temp;
		}
		

	}
	public List<Task> viewTasks(int userID) {
		return taskdao.selectTasks(userID);
	}


	public List<Task> updateTask(Task task) {
		if(taskdao.updateTask(task))
		{
			return taskdao.selectTasks(task.getUserID());
		}
		else
			return null;
	
	}

}
