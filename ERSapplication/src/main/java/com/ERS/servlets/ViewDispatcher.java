package com.ERS.servlets;

import javax.servlet.http.HttpServletRequest;

import com.ERS.Controller.Controller;

public class ViewDispatcher {
	
        public static String process(HttpServletRequest req) {
		
		switch(req.getRequestURI()) {
		
		case "/ERSapplication/login.change":
			System.out.println("in login.change dispatcher");
			return Controller.login(req);
			
			
	     case "/ERSapplication/reimbursement.change":
		System.out.println("in reimbursement.change dispatcher");
		System.out.println(req.getParameter("amount"));
		return Controller.addReimbursement(req);
		//return "html/employeepage.html";
			
			
		case "/ERSapplication/register.change":
			System.out.println("in register.change dispatcher");
			return Controller.register(req);
			
			
		case "/ERSapplication/retrieveTickets.change":
			System.out.println("in ticket.change");
			return Controller.retrieveTickets(req);

		
		 default:
			 	System.out.println("in  default");
			 	return "html/unsuccessfullogin.html";
		
		}
		
	}


}
