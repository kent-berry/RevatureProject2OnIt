package com.revature.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.DtoInteger;
import com.revature.dto.DtoLoginUser;
import com.revature.dto.DtoPassword;
import com.revature.dto.DtoRegisterUser;
import com.revature.dto.DtoString;
import com.revature.dto.DtoTask;
import com.revature.dto.DtoUpdatedTask;
import com.revature.dto.DtoUpdatedUser;
import com.revature.model.Task;
import com.revature.model.User;
import com.revature.service.IUserService;
import com.revature.service.UserService;

@Configuration
@CrossOrigin(origins = {"http://onitp2.s3-website.us-east-2.amazonaws.com", "http://localhost:4200", "http://localhost:4200/tasks"}, allowCredentials = "true")
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
	private IUserService userservice = new UserService();
	
	
	//@GetMapping(value = "/checkActiveSession")
	@RequestMapping(method=RequestMethod.GET, value="/checkActiveSession/{sessionToken}")
	public  @ResponseBody User checkActiveSession(@PathVariable("sessionToken") String sessionToken) {
		
		User authorizedUser = userservice.getUserFromSessionToken(sessionToken);
		
		if(authorizedUser != null) {
			return authorizedUser;
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

	@PostMapping(value = "/getUserById")
	public @ResponseBody User getUserById(@RequestBody DtoString dtoString) {
		User loggingUser = userservice.getUserById(dtoString.getFormString());
		return loggingUser;
	}
	
	
	/*
	 * Login --> If credentials match, generate a unique sessionToken for a User
	 * 				return this User (which also contains the sessionToken)
	 */
	
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
			User userWithSessionToken = userservice.generateSessionToken(loggingUser);
			// This value will be null if after x attempts, a unique session token could not be generated
			return userWithSessionToken;             
		}
		return loggingUser;
	}

	@PostMapping(value = "/logout")
	public @ResponseBody boolean logout(@RequestBody User u) { 
		userservice.deleteSessionToken(u);
		return true;
	}

	@PostMapping(value = "/deleteAccount")
	public boolean unregister(@RequestBody DtoPassword dtoPassword, @RequestBody User u) {
		if (u.getSessionToken() != null) {
			User loggedinUser = u;
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
	public @ResponseBody String downloadMyData(@RequestBody User u) {
		if(u.getSessionToken() != null) {
			User loggedinUser = u;
			return userservice.downloadMyData(loggedinUser.getEmail(), loggedinUser.getPassword());
		} else {
			return "No user data can be retreived, make sure user is registered and loggedin"; 
		}
	}

	
	@PostMapping(value = "/updateEmailReminders")
	public @ResponseBody boolean receiveEmailReminders(@RequestBody DtoInteger dtoInteger, @RequestBody User u) {
		if(u.getSessionToken() != null) {
			User loggedinUser = u;
			loggedinUser.setReceiveEmailReminders(dtoInteger.getFormInteger());
			return userservice.receiveEmailReminders(loggedinUser);
		} else {
			return false;
		}
	}
	
	@PostMapping(value = "/updateDailyGoals")
	public @ResponseBody boolean setDailyGoals(@RequestBody DtoInteger dtoInteger, @RequestBody User u) {
		if(u.getSessionToken() != null) {
			User loggedinUser = u;
			loggedinUser.setGoal(dtoInteger.getFormInteger());
			return userservice.setDailyGoals(loggedinUser);
		} else {
			return false;
		}
	}

	@PostMapping(value = "/updateUserInfo")
	public @ResponseBody boolean updateUserInfo(@RequestBody DtoUpdatedUser dtoUpdatedUser, @RequestBody User u) {
		if(u.getSessionToken() != null) {			
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
	public @ResponseBody Task createTask(@RequestBody DtoTask dtoTask, @RequestBody User u) {
		// Create task out of the request, will use save() in dao
		if(u.getSessionToken() != null) {
			User loggedinUser = u;
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
	public @ResponseBody Task updateTask(@RequestBody DtoUpdatedTask dtoUpdatedTask, @RequestBody User u) {
		// we receive an updated task from the frontend, it should have the id of the task
		if(u.getSessionToken() != null) {
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
	public @ResponseBody boolean deleteTask(@RequestBody DtoString dtoString, @RequestBody User u) { //dtoString is taskId from the frontend
		if(u.getSessionToken() != null) {
			return userservice.deleteTask(dtoString.getFormString());
		} else {
			return false;
		}
		
	}

	@GetMapping(value = "/viewTasks")
	public List<Task> viewTasks(@RequestBody User u) { 
		if(u.getSessionToken() != null) {
			User loggedinUser = u;
			return userservice.viewTasks(loggedinUser.getId());
		} else {
			return null;
		}
		
	}

	@PostMapping(value = "/completeTask")
	public @ResponseBody Task completeTask(@RequestBody DtoUpdatedTask dtoUpdatedTask, @RequestBody User u) {  
		if(u.getSessionToken() != null) {
			dtoUpdatedTask.setDateCompleted(LocalDate.now().toString());
			return updateTask(dtoUpdatedTask, u);
		} else {
			return null;
		}
	}

	@GetMapping(value = "/viewCompleted")
	public @ResponseBody List<Task> viewCompleted(@RequestBody User u) {
		if(u.getSessionToken() != null) {
			User loggedinUser = u;
			return userservice.viewCompleted(loggedinUser.getId());
		} else {
			return null;
		}
	}

	
	@PostMapping(value = "/duedateTask")
	public @ResponseBody Task duedateTask(@RequestBody DtoUpdatedTask dtoUpdatedTask, @RequestBody User u) { 
		if(u.getSessionToken() != null) {
			return updateTask(dtoUpdatedTask, u);
		} else {
			return null;
		}
	}


	@PostMapping(value = "/viewDuedate")
	public @ResponseBody List<Task> viewDuedate(@RequestBody DtoString upperBoundDate, @RequestBody User u) {
		if(u.getSessionToken() != null) {
			User loggedinUser = u;
			return userservice.viewDuedate(loggedinUser.getId(), upperBoundDate.getFormString());
		} else {
			return null;
		}
	}

	@PostMapping(value = "/setRepeatableTask")
	public @ResponseBody Task setRepeatableTask(@RequestBody DtoUpdatedTask dtoUpdatedTask, @RequestBody User u) {
		if(userservice.getUserFromSessionToken(u.getSessionToken()).getEmail()  .  equals(u.getEmail())) {
			return updateTask(dtoUpdatedTask, u);
		} else {
			return null;
		}
		
	}

}