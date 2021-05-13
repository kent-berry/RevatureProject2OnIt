package com.revature.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.Task;
import com.revature.model.User;

@Configuration

@ImportResource({"classpath:beans-annotations.xml"})
@EnableTransactionManagement
@Repository("TaskDao")
public class TaskDao implements ITaskDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	

	@Transactional
	public boolean insert(Task task) {  //EXAMPLE OF VALIDATION CONSTRAINTS BEFORE INSERTING INTO THE DATABASE, IF IT FAILS VALIDATION IT DOESNT SAVE AND RETURNS NULL TO THE FRONT END
		
		if(Validate(task))
		{
			int id = (int) sessionFactory.getCurrentSession().save(task);
			if(id != task.getID())
			{
				return false;
			}
			return true;
		}
		else 
			return false;
		
	}

	@Transactional
	public boolean update(Task task) {
		
		if(Validate(task))
		{
			sessionFactory.getCurrentSession().saveOrUpdate(task);
			return true;
		}
		else
			return false;
	}

	@Transactional
	public boolean delete(Task incomingTask) {
		
		sessionFactory.getCurrentSession().delete(incomingTask);
		if(get(incomingTask.getID()) == null)
		{
			return true;
		}
		else
			return false;
	}
	@Transactional
	public Task get(int id) {
		Task task = sessionFactory.getCurrentSession().get(Task.class, id);
		return task;
	}
	@Transactional
	public List<Task> selectTasks(int userID) {
		List<Task> tasks = null;
		
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(Task.class);
		cr.add(Restrictions.eq("userID", userID));
		tasks = cr.list();
		
		
		return tasks;
	}

	@Transactional
	public boolean updateTask(Task task) {
		Task storedtask = get(task.getID());
		System.out.println(storedtask);
		BeanUtils.copyProperties(task, storedtask);
		sessionFactory.getCurrentSession().merge(storedtask);
		Task task2 = get(storedtask.getID());
		return task2 != null;
	}


	private boolean Validate(Task task)
	{
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Set<ConstraintViolation<Task>> constraintViolations = validator.validate(task);
		if(constraintViolations.size() == 0)
		{
			
			return true;
		}
		else {
			return false;
		}
			
		
		
	}
	

}
