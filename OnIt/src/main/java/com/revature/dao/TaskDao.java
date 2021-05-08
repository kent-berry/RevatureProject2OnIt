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

	@Override
	@Transactional
	public boolean insert(Task task) {
		sessionFactory.getCurrentSession().save(task);
		return false;
	}

	@Override
	public boolean update(Task task) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String taskId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Task> selectTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateCompleteTask(String taskId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Task> selectCompleted() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateLabelTask(String taskId, String labelId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Task> selectLabel(String labelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean duedateTask(String taskId, LocalDate dueDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean selectDuedate(LocalDate dueDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateRepeatableTask(String taskId, boolean repeatable) {
		// TODO Auto-generated method stub
		return false;
	}

}
