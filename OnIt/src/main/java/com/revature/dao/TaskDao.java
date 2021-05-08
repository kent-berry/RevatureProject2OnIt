package com.revature.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.Task;

@Configuration

@ImportResource({"classpath:beans-annotations.xml"})
@EnableTransactionManagement
@Repository("TaskDao")
public class TaskDao implements ITaskDao {
	
	@Autowired
	private SessionFactory sessionFactory;


	@Transactional
	public boolean insert(Task task) {
		sessionFactory.getCurrentSession().saveOrUpdate(task);
		return true;
	}


	public boolean update(Task task) {
		sessionFactory.getCurrentSession().saveOrUpdate(task);
		return true;
	}


	public boolean delete(Task incomingTask) {
		sessionFactory.getCurrentSession().delete(incomingTask);
		return true;
	}

	public Task get(int id) {
		Task task = sessionFactory.getCurrentSession().get(Task.class, id);
		return task;
	}

	public List<Task> selectTasks() {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean updateCompleteTask(String taskId) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public List<Task> selectCompleted() {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean updateLabelTask(String taskId, String labelId) {
		// TODO Auto-generated method stub
		return false;
	}


	public List<Task> selectLabel(String labelId) {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean duedateTask(String taskId, LocalDate dueDate) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean selectDuedate(LocalDate dueDate) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean updateRepeatableTask(String taskId, boolean repeatable) {
		// TODO Auto-generated method stub
		return false;
	}


	

}
