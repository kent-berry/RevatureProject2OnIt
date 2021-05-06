package com.revature.servlet;

import javax.servlet.http.HttpServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.revature.controller.UserController;

@Component("MasterServlet")
public class MasterServlet extends HttpServlet {

	private UserController uController;
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		
//		
//	
//		
//		
//		HttpServletRequest  request;
//        HttpServletResponse response;
//        
//        if (!(req instanceof HttpServletRequest &&
//                resp instanceof HttpServletResponse)) {
//            throw new ServletException("non-HTTP request or response");
//        }
//
//        request = (HttpServletRequest) req;
//        response = (HttpServletResponse) resp;
//       
//	
//        

		
	}
	
	public void doThing()
	{
		System.out.println("kent sucks");
		
	}
}