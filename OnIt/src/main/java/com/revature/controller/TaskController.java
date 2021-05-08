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
import org.springframework.web.bind.annotation.PathVariable;
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

import com.revature.exceptions.NoKnownUserException;
import com.revature.model.Task;
import com.revature.model.User;
import com.revature.service.IUserService;
import com.revature.service.TaskService;
import com.revature.service.UserService;

@Controller
public class TaskController {

	@Autowired
	private TaskService taskservice = new TaskService();
	
	
	@PostMapping(value ="/task")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public  @ResponseBody boolean createTask(@RequestBody Task incomingTask) {
		System.out.println(incomingTask);
		try {
			return taskservice.createTask(incomingTask);
		} catch (NoKnownUserException e) {
			
			e.printStackTrace();
			throw new ResponseStatusException(
			          HttpStatus.CONFLICT, "No user matching Task's userID", e);
		}
	}
	
	@DeleteMapping(value ="/task")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public  @ResponseBody boolean DeleteTask(@RequestBody Task incomingTask) {
		System.out.println("DELETING TASK");
		try {
			return taskservice.deleteTask(incomingTask);
		} catch (NoKnownUserException e) {
			
			e.printStackTrace();
			throw new ResponseStatusException(
			          HttpStatus.CONFLICT, "No user matching Task's userID", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value ="/task/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public  @ResponseBody Task GetTask(@PathVariable("id") int id) {
		System.out.println("GETTING TASK");
		try {
			return taskservice.getTask(id);
		} catch (NoKnownUserException e) {
			e.printStackTrace();
			throw new ResponseStatusException(
			          HttpStatus.CONFLICT, "No user matching Task's userID", e);
		}
	}
//	
//	@ResponseStatus(code = HttpStatus.ACCEPTED)
//	@PostMapping(value="/ticket/update")
//	public @ResponseBody boolean updateTicket(@RequestBody Task task) {
//	
//		System.out.println("Inside endpoint /task/update");
//		return taskservice.updateTask(task); // REGISTER USER DOES THE SAME THING AS MAKING A NEW METHOD TO SAVE A USER
//	}

}
