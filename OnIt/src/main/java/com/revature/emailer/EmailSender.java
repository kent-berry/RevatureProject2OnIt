package com.revature.emailer;

import java.time.LocalDate;
import java.time.Period;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.revature.dao.ITaskDao;
import com.revature.dao.IUserDao;

import com.revature.model.Task;
import com.revature.model.User;
@Component("EmailSender")
@Transactional
public class EmailSender {

	
	
	@Autowired
	private ITaskDao tdao;
	@Autowired
	private IUserDao udao;
	@Autowired
    private Mailer mailer;
	
	
	
	
	public void EmailNotifications()
	{
		Calendar today = Calendar.getInstance();
		List<User> users =  udao.getAllUser();
		LocalDate now = LocalDate.now();
		System.out.println(now);
		
		for(User u:users)
		{
			System.out.println(u);
			List<Task> tasks = tdao.selectTasks(u.getId());
			if(tasks != null)
			{
				for(Task t:tasks)
				{
					System.out.println(t.getDueDate());
					

					if(t.getDueDate() != null)
					{
						Period p = Period.between(now, t.getDueDate());
						if(p.getDays() <= t.getReminder()&& t.getDateCompleted() == null)
						{
					        mailer.sendMail("us",u.getEmail(),"Test from our app","Inside our email");
						}
					}
					
				}
			}
			
		}
		
	}
}
