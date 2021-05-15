package com.revature.controller;

import java.time.LocalDate;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.*;
import com.revature.model.*;
import com.revature.service.*;

@Configuration
@CrossOrigin(origins = {"http://onitp2.s3-website.us-east-2.amazonaws.com", "http://localhost:4200"}, allowCredentials = "true")
@RestController
public class UserController  {

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
	
	@PostMapping(value = "/getUserById")
	private @ResponseBody User getUserById(@RequestBody DtoString dtoString) {
		User loggingUser = userservice.getUserById(dtoString.getFormString());
		return loggingUser;
	}
	
	@GetMapping(value = "/checkActiveSession")
	public  @ResponseBody User checkActiveSession() {
		if(httpsession.getAttribute("id") != null) {
			String id = (String) httpsession.getAttribute("id");
			DtoString dtoString = new DtoString();
			dtoString.setFormString(id);
			return getUserById(dtoString);
		} else {
			return null;
		}
	}
	
	@PostMapping(value = "/register")
	public  @ResponseBody User register(@RequestBody DtoRegisterUser dtoRegisterUser) {
		String hashedPass = "";
		try {
			hashedPass = hashPass(dtoRegisterUser.getPassword());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			return null;
		}
		
		//Check if user is already registered by trying to login
		if(userservice.login(dtoRegisterUser.getEmail(), hashedPass) == null) {
			User newUser = new User(dtoRegisterUser.getFirstname(), dtoRegisterUser.getLastname(), dtoRegisterUser.getEmail(), hashedPass);
			userservice.register(newUser);
			return newUser;
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
			httpsession.setAttribute("id", loggingUser.getId());
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
	public @ResponseBody Task createTask(@RequestBody DtoTask dtoTask) {
		// Create task out of the request, will use save() in dao
		if(httpsession.getAttribute("loggedinUser") != null) {
			User loggedinUser = (User) httpsession.getAttribute("loggedinUser");
			Task newTask = new Task(loggedinUser.getId(), dtoTask.getTaskName(), dtoTask.getNotes(), LocalDate.parse(dtoTask.getDueDate()), 
					dtoTask.getReminder(), dtoTask.isRepeatable(),
					dtoTask.getTaskLabel(), dtoTask.getLatitude(), dtoTask.getLongitude());
			
					userservice.createTask(newTask);
					
					return newTask;
		} else {
			return null;
		}
	}

	@PostMapping(value = "/updateTask")
	public @ResponseBody Task updateTask(@RequestBody DtoUpdatedTask dtoUpdatedTask) {
		// we receive an updated task from the frontend, it should have the id of the task
		if(httpsession.getAttribute("loggedinUser") != null) {
			//We convert from DtoUpdatedTask to Task
			System.out.println("dateCompleted: " + dtoUpdatedTask.getDateCompleted());
			
			LocalDate dueDate = null;
			if(dtoUpdatedTask.getDueDate() != null) {
				dueDate = LocalDate.parse(dtoUpdatedTask.getDueDate());
			} 
			
			LocalDate dateCompleted = null;
			if(dtoUpdatedTask.getDateCompleted() != null) {
				dateCompleted = LocalDate.parse(dtoUpdatedTask.getDateCompleted());
			}
			
			Task updatedTask = new Task(dtoUpdatedTask.getId(), dtoUpdatedTask.getUserId(),
										dtoUpdatedTask.getTaskName(), dtoUpdatedTask.getNotes(),
										LocalDate.parse(dtoUpdatedTask.getDateCreated()), dueDate, dateCompleted,
										dtoUpdatedTask.getReminder(), dtoUpdatedTask.isRepeatable(),
										dtoUpdatedTask.getTaskLabel_fk(), dtoUpdatedTask.getLatitude(), dtoUpdatedTask.getLongitude());
			boolean couldUpdate = userservice.updateTask(updatedTask);
			if (couldUpdate) {
				return updatedTask;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	@PostMapping(value = "/deleteTask")
	public @ResponseBody boolean deleteTask(@RequestBody DtoString dtoString) { //dtoString is taskId from the frontend
		if(httpsession.getAttribute("loggedinUser") != null) {
			return userservice.deleteTask(dtoString.getFormString());
		} else {
			return false;
		}
		
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

	@PostMapping(value = "/completeTask")
	public @ResponseBody Task completeTask(@RequestBody DtoUpdatedTask dtoUpdatedTask) {  
		if(httpsession.getAttribute("loggedinUser") != null) {
			dtoUpdatedTask.setDateCompleted(LocalDate.now().toString());
			return updateTask(dtoUpdatedTask);
		} else {
			return null;
		}
	}

	@GetMapping(value = "/viewCompleted")
	public @ResponseBody List<Task> viewCompleted() {
		if(httpsession.getAttribute("loggedinUser") != null) {
			User loggedinUser = (User) httpsession.getAttribute("loggedinUser");
			return userservice.viewCompleted(loggedinUser.getId());
		} else {
			return null;
		}
	}

	
	@PostMapping(value = "/duedateTask")
	public @ResponseBody Task duedateTask(@RequestBody DtoUpdatedTask dtoUpdatedTask) { 
		if(httpsession.getAttribute("loggedinUser") != null) {
			return updateTask(dtoUpdatedTask);
		} else {
			return null;
		}
	}


	@PostMapping(value = "/viewDuedate")
	public @ResponseBody List<Task> viewDuedate(@RequestBody DtoString upperBoundDate) {
		if(httpsession.getAttribute("loggedinUser") != null) {
			User loggedinUser = (User) httpsession.getAttribute("loggedinUser");
			return userservice.viewDuedate(loggedinUser.getId(), upperBoundDate.getFormString());
		} else {
			return null;
		}
	}

	@PostMapping(value = "/setRepeatableTask")
	public @ResponseBody Task setRepeatableTask(@RequestBody DtoUpdatedTask dtoUpdatedTask) {
		if(httpsession.getAttribute("loggedinUser") != null) {
			return updateTask(dtoUpdatedTask);
		} else {
			return null;
		}
		
	}

}