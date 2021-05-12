package com.revature.controller;

import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.server.ResponseStatusException;

import com.revature.exceptions.DeleteFailedException;
import com.revature.exceptions.InsertFailedException;
import com.revature.exceptions.NoKnownUserException;
import com.revature.exceptions.PasswordIncorrectException;
import com.revature.exceptions.UsernameInUseException;
import com.revature.model.Task;
import com.revature.model.User;
import com.revature.service.IUserService;
import com.revature.service.UserService;

@Controller
//@RequestMapping(value = "/user")
//@SessionAttributes("user")
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")

public class UserController  {

	@Autowired
	private UserService userservice = new UserService();
	



	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(value="/user")
	public @ResponseBody User updateUser(@RequestBody User user) {
	
		System.out.println("Inside endpoint /User");
		
	
		
			try {
				return userservice.update(user);
			
		} catch (InsertFailedException e) {
			e.printStackTrace();
			throw new ResponseStatusException(
			          HttpStatus.CONFLICT, "Update Failed", e);
			
			
			
		}
		
			
	// REGISTER USER DOES THE SAME THING AS MAKING A NEW METHOD TO SAVE A USER
	}


	
		
	
	
	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping(value = "/Delete")
	public boolean unregister(@RequestBody User user,HttpSession session) {
		
		try {
			userservice.unregister(user);
			session.invalidate();
			return true;
		
	} catch (DeleteFailedException e) {
		e.printStackTrace();
		throw new ResponseStatusException(
		          HttpStatus.CONFLICT, "Update Failed", e);
		
		
		
	}
		
	}

	
	public String downloadMyData(HttpServletRequest request) {
		return userservice.downloadMyData(request.getParameter("email"),
										  request.getParameter("password"));
	}



	

}
