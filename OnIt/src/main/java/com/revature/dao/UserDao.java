package com.revature.dao;



import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.postgresql.util.PSQLException;
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
@Repository("UserDao")
public class UserDao implements IUserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void saveUser(User u)
	{
		
		sessionFactory.getCurrentSession().save(u);
	}

	@Override
	@Transactional
	public boolean insert(User user) {
		
		sessionFactory.getCurrentSession().save(user);
			
		return true;
	}

	@Override
	@Transactional
	public User select(User user) {
		List<Task> temp = new ArrayList<Task>();
		Task t = new Task();
		Task t1 = new Task();
		Task t2 = new Task();
		Task t3 = new Task();
		Task t4 = new Task();
		temp.add(t);
		temp.add(t2);
		temp.add(t3);
		temp.add(t4);
		User returned = sessionFactory.getCurrentSession().get(User.class, user.getID());
		//List<Task> temp = (List<Task>) sessionFactory.getCurrentSession().get(Task.class, user.getID());
		
//		CriteriaBuilder cb = sessionFactory.getCurrentSession().getCriteriaBuilder();
//		CriteriaQuery<Task> c = cb.createQuery(Task.class);
//		c.add()
		returned.setTasks(temp);
		return returned;
	}

	@Override
	@Transactional
	public boolean delete(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEmailReminders(int reminderPeriod) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateGoal(int numDesired) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
