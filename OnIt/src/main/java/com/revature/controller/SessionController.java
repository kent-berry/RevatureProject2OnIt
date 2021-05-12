package com.revature.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.server.ResponseStatusException;

import com.revature.exceptions.InsertFailedException;
import com.revature.exceptions.NoKnownUserException;
import com.revature.exceptions.PasswordIncorrectException;
import com.revature.exceptions.UsernameInUseException;
import com.revature.model.User;
import com.revature.service.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")


public class SessionController {

	@Autowired
	private UserService userservice = new UserService();
	

	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(value="/login")
	
	public @ResponseBody User login(@RequestBody  User incomingUser,HttpSession session) {

		
		System.out.println(incomingUser);
		
		User loggedIn;
		try {
			
			
			loggedIn = userservice.login(incomingUser);
			
			
			session.setAttribute("email", loggedIn.getEmail());
			session.setAttribute("password", loggedIn.getPassword());
			session.setAttribute("firstName", loggedIn.getFirstName());
			session.setAttribute("lastName", loggedIn.getLastName());
			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("LoggedIn", "True");
			
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
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "/login")
	public User checkSession(HttpSession session) {
		User user = new User();
		
		user.setEmail((String) session.getAttribute("email"));
		user.setPassword((String) session.getAttribute("password"));
		user.setFirstName((String) session.getAttribute("fireName"));
		user.setLastName((String) session.getAttribute("lastNAme"));
		
		
		
//		session.setAttribute("email", loggedIn.getEmail());
//		session.setAttribute("password", loggedIn.getPassword());
//		session.setAttribute("firstName", loggedIn.getFirstName());
//		session.setAttribute("lastName", loggedIn.getLastName());
		return user;
		
	}
	
	@GetMapping(value="/logout")
	@ResponseStatus(code = HttpStatus.ACCEPTED) 
	public void logout(HttpSession session)
	{
		session.invalidate();
		
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
