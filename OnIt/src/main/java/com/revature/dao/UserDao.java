package com.revature.dao;



import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.User;
@EnableTransactionManagement
@Repository("UserDao")
public class UserDao implements IUserDao {

	
	//private SessionFactory sessionFactory = app
	
	@Transactional
	public void saveUser(User u, SessionFactory sessionFactory)
	{
		
		Session s = sessionFactory.openSession();
		
		Transaction tx = s.beginTransaction();
		System.out.println(sessionFactory.getStatistics());
		s.save(u);
		tx.commit();
		s.close();
	
		System.out.println(sessionFactory.getStatistics());
		
		//sessionFactory.getCurrentSession().save(u);
	}
	
}
