package com.revature.controller;

import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.revature.model.Task;
import com.revature.model.User;
import com.revature.service.IUserService;
import com.revature.service.UserService;

@Controller
//@RequestMapping(value = "/user")
//@SessionAttributes("user")
public class UserController implements IUserController {

	@Autowired
	private IUserService userservice = new UserService();
	
	//@Autowired
	//private HttpSession httpsesh; 
	
	//this method made by kent to begin testing MVC
	@RequestMapping(method = RequestMethod.GET, value ="/test")
	public  @ResponseBody User test() {
		User user = new User();
		System.out.println("inside test mvc");
		return user;
	}
	
	
	@Override
	@PostMapping(value="/register")
	public  @ResponseBody boolean register(@RequestBody User incomingUser) {
//		return userservice.register(request.getParameter("firstname"), 
//				request.getParameter("lastName"), 
//				request.getParameter("email"), 
//				request.getParameter("password"));
		
		return userservice.register(incomingUser);
	}

	@Override
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@PostMapping(value="/login")
	//@ModelAttribute("user") //THIS SHOULD BE DOING OUR SESSION STORING FOR US NOW
	public @ResponseBody User login(@RequestBody User incomingUser) {
//		return userservice.login(request.getParameter("email"), 
//								 request.getParameter("password"));
		
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("LoggedIn", "True");
		User loggedIn = userservice.login(incomingUser);
		System.out.println(loggedIn);
		//httpsesh.setAttribute("user", loggedIn); //setting session user to logged in user
		return loggedIn;
		
		
		//we should also create our HTTP SESSION inside here too
	}
	@Override
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@PostMapping(value="/user")
	public @ResponseBody boolean updateUser(@RequestBody User user) {
	
		System.out.println("Inside endpoint /User");
		return userservice.register(user); // REGISTER USER DOES THE SAME THING AS MAKING A NEW METHOD TO SAVE A USER
	}

//	@Override
//	@RequestMapping(value = "/logout")
//	//@ModelAttribute("user")
//	public User logout(@SessionAttribute("user") User user) { //done using httpsession
//		
//		httpsesh.setAttribute("user", null);
//		user = null;
//		
//		return user;
//	}

	@Override
	@DeleteMapping(value = "/Delete")
	public boolean unregister(HttpServletRequest request) {
		return userservice.unregister(request.getParameter("email"), request.getParameter("password"));
	}

	@Override
	public String downloadMyData(HttpServletRequest request) {
		return userservice.downloadMyData(request.getParameter("email"),
										  request.getParameter("password"));
	}



//	@Override
//	@PostMapping(value = "/task/create")
//	public @ResponseBody boolean createTask(@RequestBody Task task){
//		// Create task out of the request
////		Task newTask = new Task(request.getParameter("userId"), 
////								request.getParameter("labelId"),
////								request.getParameter("taskName"),
////								request.getParameter("notes"),
////								LocalDate.parse(request.getParameter("dueDate")),
////								Integer.parseInt(request.getParameter("reminder")),
////								Boolean.parseBoolean(request.getParameter("repeatable")));
//		
//		return true; //userservice.createTask(newTask);
//	}
//
//	@Override
//	public boolean updateTask(HttpServletRequest request) {
//		// Create task out of the request
////		Task newTask = new Task(request.getParameter("userId"), 
////								request.getParameter("labelId"),
////								request.getParameter("taskName"),
////								request.getParameter("notes"),
////								LocalDate.parse(request.getParameter("dueDate")),
////								Integer.parseInt(request.getParameter("reminder")),
////								Boolean.parseBoolean(request.getParameter("repeatable")));
//
//		return true; //userservice.updateTask(newTask);
//	}
//
//	@Override
//	public boolean deleteTask(HttpServletRequest request) {
//		return userservice.deleteTask(request.getParameter("taskId"));
//	}
//
//	@Override
//	public List<Task> viewTasks(HttpServletRequest request) {
//		return userservice.viewTasks();
//	}
//
//	@Override
//	public boolean completeTask(HttpServletRequest request) {
//		return userservice.completeTask(request.getParameter("taskId"));
//	}
//
//	@Override
//	public List<Task> viewCompleted(HttpServletRequest request) {
//		return userservice.viewCompleted();
//	}
//
//	@Override
//	public boolean labelTask(HttpServletRequest request) {
//		return userservice.labelTask(request.getParameter("taskId"), request.getParameter("labelId"));
//	}
//
//	@Override
//	public List<Task> viewLabel(HttpServletRequest request) {
//		return userservice.viewLabel(request.getParameter("labelId"));
//	}
//
//	@Override
//	public boolean duedateTask(HttpServletRequest request) {
//		return userservice.duedateTask(request.getParameter("taskId"), 
//				                       LocalDate.parse(request.getParameter("dueDate")));
//	}
//
//	@Override
//	public boolean viewDuedate(HttpServletRequest request) {
//		return userservice.viewDuedate(LocalDate.parse(request.getParameter("dueDate")));
//	}
//
//	@Override
//	public boolean receiveEmailReminders(HttpServletRequest request) {
//		return userservice.receiveEmailReminders(Integer.parseInt(request.getParameter("receiveEmailReminders")));
//	}
//
//	@Override
//	public boolean setRepeatableTask(HttpServletRequest request) {
//		return userservice.SetRepeatableTask(request.getParameter("taskId"),
//				                             Boolean.parseBoolean(request.getParameter("repeatable")));
//	}
//
//	@Override
//	public boolean setDailyGoals(HttpServletRequest request) {
//		return userservice.setDailyGoals(Integer.parseInt(request.getParameter("numDesired")));
//	}
//
//	@Override
//	public Object viewProgress(HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Object viewPastProgressGraph(HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public User logout(User user) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//

	

}
