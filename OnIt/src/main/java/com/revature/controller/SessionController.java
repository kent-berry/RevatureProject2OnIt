package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.server.ResponseStatusException;

import com.revature.exceptions.InsertFailedException;
import com.revature.exceptions.NoKnownUserException;
import com.revature.exceptions.PasswordIncorrectException;
import com.revature.exceptions.UsernameInUseException;
import com.revature.model.User;
import com.revature.service.UserService;

@Controller
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
@SessionAttributes("user")
@ControllerAdvice
public class SessionController {

	@Autowired
	private UserService userservice = new UserService();

	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@PostMapping(value="/login")
	@ModelAttribute("user") //THIS SHOULD BE DOING OUR SESSION STORING FOR US NOW
	public @ResponseBody User login(@ModelAttribute("session") User incomingUser, ModelMap model) {

		
		System.out.println(incomingUser);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("LoggedIn", "True");
		User loggedIn;
		try {
			
			
			loggedIn = userservice.login(incomingUser);
			
		//	model.addAttribute("UserName", loggedIn.getEmail());
		//	model.addAttribute("password", loggedIn.getPassword());
			return loggedIn;
			
		} catch (NoKnownUserException e) {
			
			e.printStackTrace();
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "No known user with this username", e);
		} catch (PasswordIncorrectException e) {
			
			e.printStackTrace();
			throw new ResponseStatusException(
			          HttpStatus.BAD_REQUEST, "Password Incorrect", e);
		}
		

		
		
		
		//we should also create our HTTP SESSION inside here too
	}
	
	@PostMapping(value="/logout")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@ModelAttribute("user") 
	public void logout(@RequestBody User incomingUser)
	{
		
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(value="/register")
	public  @ResponseBody boolean register(@RequestBody User incomingUser) {

		
			try 
			{
				return userservice.register(incomingUser);
			} 
			catch (UsernameInUseException e)
			{
				{
				e.printStackTrace();
				throw new ResponseStatusException(
				          HttpStatus.NOT_FOUND, "Registration Failed", e);
				}
				
			} 
			catch (PasswordIncorrectException e) 
			{
				e.printStackTrace();
				throw new ResponseStatusException(
				          HttpStatus.NOT_FOUND, "Registration Failed", e);
				    }
			
	    	 catch (InsertFailedException e) 
			{
	    		e.printStackTrace();
	        throw new ResponseStatusException(
	          HttpStatus.NOT_FOUND, "Registration Failed", e);
			}
		
	}
}
