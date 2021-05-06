import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import connection.ConnectionUtil;
import servlets.MasterServlet;
import model.Task;


// This main is for testing purposes, this is a WAR and no main class is needed at the end
public class Main {

	public final static ApplicationContext appContext = new ClassPathXmlApplicationContext("beans-annotations.xml");
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		
		//ConnectionUtil connectionUtil = new ConnectionUtil();
		//connectionUtil.getConnection();


		Task task = appContext.getBean("Task", Task.class);
		
		MasterServlet ms = appContext.getBean("MasterServlet", MasterServlet.class);
		ms.doThing();
		
		
	}

}
