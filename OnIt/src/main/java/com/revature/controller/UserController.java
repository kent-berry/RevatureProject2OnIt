package com.revature.controller;


import java.io.Serializable;
import java.time.LocalDate;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.*;
import com.revature.model.*;
import com.revature.service.*;

@Configuration
@RestController
public class UserController implements IUserController {

	//Method for Hashing password
	protected String hashPass(String pass) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] salt = new byte[16];
		KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt, 65536, 128);
		SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = f.generateSecret(spec).getEncoded();
		Base64.Encoder enc = Base64.getEncoder();
		return enc.encodeToString(hash);
	}
		
	@Autowired
	private HttpSession httpsession;
	
	@Autowired
	private IUserService userservice = new UserService();
	
	@PostMapping(value = "/register")
	public  @ResponseBody Serializable register(@RequestBody DtoRegisterUser dtoRegisterUser) {
		String hashedPass = "";
		try {
			hashedPass = hashPass(dtoRegisterUser.getPassword());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			return false;
		}
		
		//Check if user is already registered by trying to login
		if(userservice.login(dtoRegisterUser.getEmail(), hashedPass) == null) {
			User newUser = new User(dtoRegisterUser.getFirstname(), dtoRegisterUser.getLastname(), dtoRegisterUser.getEmail(), hashedPass);
			return userservice.register(newUser);
		} else {
			return null;
		}
	}

	@PostMapping(value = "/login")
	public @ResponseBody User login(@RequestBody DtoLoginUser dtoLoginUser) {
		String hashedPass = "";
		try {
			hashedPass = hashPass(dtoLoginUser.getPassword());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			return null;
		}
		
		
		User loggingUser = userservice.login(dtoLoginUser.getEmail(), hashedPass);
		if (loggingUser != null) {
			httpsession.setAttribute("loggedinUser", loggingUser);
		}
		return loggingUser;
	}

	@GetMapping(value = "/logout")
	public @ResponseBody boolean logout() { 
		httpsession.setAttribute("loggedinUser", null);
		httpsession.invalidate();
		return true;
	}

	@PostMapping(value = "/deleteAccount")
	public boolean unregister(@RequestBody DtoPassword dtoPassword) {
		if(httpsession.getAttribute("loggedinUser") != null) {
			User loggedinUser = (User) httpsession.getAttribute("loggedinUser");
			String hashedPass = "";
			try {
				hashedPass = hashPass(dtoPassword.getPassword());
				if(hashedPass.equals(loggedinUser.getPassword())) {
					return userservice.unregister(loggedinUser.getEmail(), hashedPass);
				} else {
					//js alert! telling the user that the provided password is not correct
					return false;
				}
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	@GetMapping(value = "/downloadMyData")
	public @ResponseBody String downloadMyData() {
		if(httpsession.getAttribute("loggedinUser") != null) {
			User loggedinUser = (User) httpsession.getAttribute("loggedinUser");
			return userservice.downloadMyData(loggedinUser.getEmail(), loggedinUser.getPassword());
		} else {
			return "No user data can be retreived, make sure user is registered and loggedin"; 
		}
	}

	
	@PostMapping(value = "/updateEmailReminders")
	public @ResponseBody boolean receiveEmailReminders(@RequestBody DtoInteger dtoInteger) {
		if(httpsession.getAttribute("loggedinUser") != null) {
			User loggedinUser = (User) httpsession.getAttribute("loggedinUser");
			loggedinUser.setReceiveEmailReminders(dtoInteger.getFormInteger());
			return userservice.receiveEmailReminders(loggedinUser);
		} else {
			return false;
		}
	}
	
	@PostMapping(value = "/updateDailyGoals")
	public @ResponseBody boolean setDailyGoals(@RequestBody DtoInteger dtoInteger) {
		if(httpsession.getAttribute("loggedinUser") != null) {
			User loggedinUser = (User) httpsession.getAttribute("loggedinUser");
			loggedinUser.setGoal(dtoInteger.getFormInteger());
			return userservice.setDailyGoals(loggedinUser);
		} else {
			return false;
		}
	}

	@PostMapping(value = "/updateUserInfo")
	public @ResponseBody boolean updateUserInfo(@RequestBody DtoUpdatedUser dtoUpdatedUser) {
		if(httpsession.getAttribute("loggedinUser") != null) {
			//We convert from DtoUpdatedUser to User
			
			//First convert password into hashed password only is isPasswordChanging.equals("y")
			if(dtoUpdatedUser.getIsPasswordChanging().equals("y")) {
				String hashedPass = "";
				try {
					hashedPass = hashPass(dtoUpdatedUser.getPassword());
				} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
					e.printStackTrace();
					return false;
				}
				
				User updatedUser = new User(dtoUpdatedUser.getId(), dtoUpdatedUser.getFirstname(), dtoUpdatedUser.getLastname(),
						dtoUpdatedUser.getEmail(), hashedPass, LocalDate.parse(dtoUpdatedUser.getAccountCreated()), 
						dtoUpdatedUser.getReceiveEmailReminders(), dtoUpdatedUser.getGoal());
				
				return userservice.updateUserInfo(updatedUser);
			} else {
				User updatedUser = new User(dtoUpdatedUser.getId(), dtoUpdatedUser.getFirstname(), dtoUpdatedUser.getLastname(),
						dtoUpdatedUser.getEmail(), dtoUpdatedUser.getPassword(), LocalDate.parse(dtoUpdatedUser.getAccountCreated()), 
						dtoUpdatedUser.getReceiveEmailReminders(), dtoUpdatedUser.getGoal());
				

				return userservice.updateUserInfo(updatedUser);
			}
		} else {
			return false;
		}
	}
	
	
	@PostMapping(value = "/addTask")
	public @ResponseBody Serializable createTask(@RequestBody DtoTask dtoTask) {
		// Create task out of the request, will use save() in dao
		if(httpsession.getAttribute("loggedinUser") != null) {
			User loggedinUser = (User) httpsession.getAttribute("loggedinUser");
			Task newTask = new Task(loggedinUser.getId(), dtoTask.getTaskName(), dtoTask.getNotes(), LocalDate.parse(dtoTask.getDueDate()), dtoTask.getReminder(), dtoTask.isRepeatable());
			return userservice.createTask(newTask);
		} else {
			return null;
		}
	}

	@PostMapping(value = "/updateTask")
	public @ResponseBody boolean updateTask(@RequestBody DtoUpdatedTask dtoUpdatedTask) {
		// we receive an updated task from the frontend, it should have the id of the task
		if(httpsession.getAttribute("loggedinUser") != null) {
			//We convert from DtoUpdatedTask to Task
			LocalDate dueDate = dtoUpdatedTask.getDueDate().equals("") ? null : LocalDate.parse(dtoUpdatedTask.getDueDate());
			LocalDate dateCompleted = dtoUpdatedTask.getDateCompleted().equals("") ? null : LocalDate.parse(dtoUpdatedTask.getDateCompleted());
			
			Task updatedTask = new Task(dtoUpdatedTask.getId(), dtoUpdatedTask.getUserId(),
										dtoUpdatedTask.getTaskName(), dtoUpdatedTask.getNotes(),
										LocalDate.parse(dtoUpdatedTask.getDateCreated()), dueDate, dateCompleted,
										dtoUpdatedTask.getReminder(), dtoUpdatedTask.isRepeatable());
			return userservice.updateTask(updatedTask);
		} else {
			return false;
		}
	}
	
	@PostMapping(value = "/deleteTask")
	public @ResponseBody boolean deleteTask(@RequestBody DtoString dtoString) { //dtoString is taskId from the frontend
		return userservice.deleteTask(dtoString.getFormString());
	}

	@GetMapping(value = "/viewTasks")
	public List<Task> viewTasks() { 
		if(httpsession.getAttribute("loggedinUser") != null) {
			User loggedinUser = (User) httpsession.getAttribute("loggedinUser");
			return userservice.viewTasks(loggedinUser.getId());
		} else {
			return null;
		}
		
	}

	@Override
	public boolean completeTask(HttpServletRequest request) {
		return userservice.completeTask(request.getParameter("taskId"));
	}

	@Override
	public List<Task> viewCompleted(HttpServletRequest request) {
		return userservice.viewCompleted();
	}

	@Override
	public boolean labelTask(HttpServletRequest request) {
		return userservice.labelTask(request.getParameter("taskId"), request.getParameter("labelId"));
	}

	@Override
	public List<Task> viewLabel(HttpServletRequest request) {
		return userservice.viewLabel(request.getParameter("labelId"));
	}

	@Override
	public boolean duedateTask(HttpServletRequest request) {
		return userservice.duedateTask(request.getParameter("taskId"), 
				                       LocalDate.parse(request.getParameter("dueDate")));
	}

	@Override
	public boolean viewDuedate(HttpServletRequest request) {
		return userservice.viewDuedate(LocalDate.parse(request.getParameter("dueDate")));
	}

	@Override
	public boolean setRepeatableTask(HttpServletRequest request) {
		return userservice.SetRepeatableTask(request.getParameter("taskId"),
				                             Boolean.parseBoolean(request.getParameter("repeatable")));
	}

	@Override
	public Object viewProgress(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object viewPastProgressGraph(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}