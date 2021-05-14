package com.revature.dao;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.Task;
import com.revature.model.User;

@EnableTransactionManagement
@ImportResource({"classpath*:beans-annotations.xml"})
@Repository
public class TaskDao implements ITaskDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public Serializable insert(Task task) {
		Serializable identifier = sessionFactory.getCurrentSession().save(task);
		return identifier;
	}

	@Transactional
	@Override
	public boolean update(Task task) {
		sessionFactory.getCurrentSession().saveOrUpdate(task);
		return true;
	}

	@Transactional
	@Override
	public boolean delete(String taskId) {
		// Find your task
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Task.class);
		criteria.add(Restrictions.eq("id", taskId));
		List<Task> results = criteria.list();
				
		// Now delete
		if(results.isEmpty()) {
			return false;
		} else {
			sessionFactory.getCurrentSession().delete(results.get(0));
			return true;
		}
	}

	@Transactional
	@Override
	public List<Task> selectTasks(String userId) {
		// Find all user tasks
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Task.class);
		criteria.add(Restrictions.eq("userId", userId));
		List<Task> results = criteria.list();
		if(results.isEmpty()) {
			return null;
		} else {
			return results;
		}
	}

	@Transactional
	@Override
	public List<Task> selectCompleted(String userId) {
		// Find user completed tasks
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Task.class);
		criteria.add(Restrictions.eq("userId", userId));
		criteria.add(Restrictions.isNotNull("dateCompleted"));
		List<Task> results = criteria.list();
		if(results.isEmpty()) {
			return null;
		} else {
			return results;
		}
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



	@Transactional
	@Override
	public List<Task> selectDuedate(String userId, String upperBoundDate) {
		// Find user tasks with duedate earlier or at upperBoundDate
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Task.class);
		criteria.add(Restrictions.eq("userId", userId));
		criteria.add(Restrictions.le("dueDate", LocalDate.parse(upperBoundDate)));
		List<Task> results = criteria.list();
		if(results.isEmpty()) {
			return null;
		} else {
			return results;
		}
	}

}
