package com.revature.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.revature.dao.ITaskDao;
import com.revature.dao.TaskDao;
import com.revature.exceptions.NoKnownUserException;

import com.revature.model.Task;
@Service
@Component
public class TaskService {

	@Autowired
	private TaskDao taskdao = new TaskDao();
	

	public boolean createTask(Task task) throws NoKnownUserException {
		return taskdao.insert(task);
	}

	public boolean deleteTask(Task incomingTask)  throws NoKnownUserException{
		return taskdao.delete(incomingTask);
	}

	public Task getTask(int id)  throws NoKnownUserException{
		return taskdao.get(id);

	}
	public List<Task> viewTasks() {
		return taskdao.selectTasks();
	}


	public List<Task> viewCompleted() {
		return taskdao.selectCompleted();
	}



	


}
