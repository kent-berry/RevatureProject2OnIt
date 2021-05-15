package com.revature.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
@ImportResource({"classpath:beans-annotations.xml"})
@Repository
public class UserDao implements IUserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public Serializable insert(User newUser) {
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
	public User select(String id) {
		// Create CriteriaBuilder
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("id", id));
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

	@Transactional
	@Override
	public boolean updateUserInfo(User updatedUser) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(updatedUser);
		} catch (HibernateException h) {
			return false;
		}
		return true;
	}

	
	@Transactional
	public boolean deleteSessionToken(String sessionToken) {
		
		try {
			String sql = "UPDATE users SET sessiontoken = (?) WHERE sessiontoken = (?)";
			Query q = sessionFactory.getCurrentSession().createNativeQuery(sql).setParameter(1, 0).setParameter(2, sessionToken);
			q.executeUpdate();
		} catch (HibernateException h) {
			return false;
		}
		
		return true;
		
	}
	
	@Transactional
	public User findUserFromSessionToken(String sessionToken) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("sessionToken", sessionToken));
		List<User> results = criteria.list();
		if(results.isEmpty()) {
			return null;
		} else {
			return results.get(0);
		}
		
	}

	
	
}