package com.revature;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.revature.connection.ConnectionUtil;
import com.revature.dao.UserDao;
import com.revature.model.Task;
import com.revature.model.User;
import com.revature.servlet.MasterServlet;


// This main is for testing purposes, this is a WAR and no main class is needed at the end
public class Main {

	public final static ApplicationContext appContext = new ClassPathXmlApplicationContext("beans-annotations.xml");
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		User u = appContext.getBean("User", User.class);
		UserDao ud = appContext.getBean("UserDao",UserDao.class);
		SessionFactory sessionFactory = appContext.getBean("sessionFactory",SessionFactory.class);
		System.out.println(sessionFactory);
		ud.saveUser(u, sessionFactory);
//		ConnectionUtil connectionUtil = new ConnectionUtil();
//		connectionUtil.getConnection();


		//Task task = appContext.getBean("Task", Task.class);
		
		//MasterServlet ms = appContext.getBean("MasterServlet", MasterServlet.class);
		//ms.doThing();
		
		
	}

}
