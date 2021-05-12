package com.revature.dao;

import com.revature.model.User;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;

@EnableTransactionManagement
@ImportResource({"classpath:beans-annotations.xml"})
@Repository
public class UserDao implements IUserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public Serializable insert(String firstName, String lastName, String email, String password) {
		User newUser = new User(firstName, lastName, email, password);
		System.out.println(newUser.toString());
		Serializable identifier = sessionFactory.getCurrentSession().save(newUser);
		return identifier;
	}

	@Transactional
	@Override
	public User select(String email, String password) {
		// Create CriteriaBuilder
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("password", password));
		List<User> results = criteria.list();
		return (results.isEmpty() ? null : results.get(0));
	}

	@Transactional
	@Override
	public boolean delete(String email, String password) {
		User user = select(email, password);
		if(user != null) {
			sessionFactory.getCurrentSession().delete(user);
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	@Override
	public boolean updateEmailReminders(User loggedinUser) {
		sessionFactory.getCurrentSession().saveOrUpdate(loggedinUser);
		return true;
	}

	@Transactional
	@Override
	public boolean updateGoal(User loggedinUser) {
		sessionFactory.getCurrentSession().saveOrUpdate(loggedinUser);
		return true;
	}

}