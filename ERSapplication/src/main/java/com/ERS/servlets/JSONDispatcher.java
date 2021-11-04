package com.ERS.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ERS.Controller.Controller;
import com.ERS.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONDispatcher {
	
public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		switch(req.getRequestURI()) {
		
		 case "/ERSapplication/getSessionUser.json":
			 System.out.println("in getSessionUser");
			 Controller.getSessionUser(req, res);
			 break;
			 
		 case "/ERSapplication/getAllTickets.json":
				System.out.println("in getSessionTickets");
				Controller.getSessionTickets(req, res);
				break;
		 case "/ERSapplication/getAllTicketsManager.json":
			 Controller.retrieveTicketsManager(req, res);
			 break;
 		 
		default:
			res.getWriter().write(new ObjectMapper().writeValueAsString(new User()));
		
		}
	}

}


