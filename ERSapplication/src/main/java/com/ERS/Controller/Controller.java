package com.ERS.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ERS.Service.Service;
import com.ERS.dao.ERSDbConnection;
import com.ERS.dao.ReimbursementDaoImpl;
import com.ERS.dao.UserDaoImpl;
import com.ERS.model.Reimbursement;
import com.ERS.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Controller {
	private static final Logger log = Logger.getLogger(HttpServletRequest.class);
	
	public static String login(HttpServletRequest req) {
		ERSDbConnection ERSCon = new ERSDbConnection();
		UserDaoImpl uDao =new UserDaoImpl(ERSCon);
		ReimbursementDaoImpl rDao = new ReimbursementDaoImpl(ERSCon);
		Service serv = new Service(rDao, uDao);
	
		if(!req.getMethod().equals("POST")) { //prevent login if they are not using an HTTP post method
			return "html/unsuccessfullogin.html";
		}
				
		//next we will process out the information that is sent in the request.
		User user = serv.login(req.getParameter("username"), req.getParameter("password"));
		if (user == null) {
			System.out.println("in the  null user");
			
			return "html/unsuccessfullogin.html";
		
		}else {
			req.getSession().setAttribute("currentUser", user);
			if (user.getRoleId() == 1) {
				//req.getSession().setAttribute("currentUser", user);
				// redirect to financialManager html page
				
				System.out.println("in the  manager user");
				
				return "html/manager.html";
				
			} else {
				// redirect to employee html page
				
				System.out.println("in the  employee page"); 
				
				return "html/employeepage.html";
			}
			}	
	}
		public static void getSessionUser(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
			System.out.println("in controller getSessionUser");
			User user = (User)req.getSession().getAttribute("currentUser"); // grabbing the current user that is stored in the session during the login
			res.getWriter().write(new ObjectMapper().writeValueAsString(user)); // process of STRINGFYING into JSON=>the logged in user will be sent as a JSON to the front-end
		}
		public static void getSessionTickets(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
			System.out.println("in controller getSessionTickets");
			ERSDbConnection dbCon = new ERSDbConnection();
			UserDaoImpl uDao = new UserDaoImpl(dbCon);
			ReimbursementDaoImpl rDao = new ReimbursementDaoImpl(dbCon);
			Service serv = new Service(rDao, uDao);
			
			User sessUser = (User)req.getSession().getAttribute("currentUser");
			System.out.println("in sess user " + sessUser );
			List<Reimbursement> rList = new ArrayList<>();
			System.out.println("in getSessiontickets");
			
			rList= serv.getAllReimbursementbyUser(sessUser);
			res.getWriter().write(new ObjectMapper().writeValueAsString(rList));

		}
		
		
		public static String register(HttpServletRequest req) {
			System.out.println("in controller register form");
			log.info("registering new user");
			
			ERSDbConnection dbCon = new ERSDbConnection();
			UserDaoImpl uDao = new UserDaoImpl(dbCon);
			ReimbursementDaoImpl rDao = new ReimbursementDaoImpl(dbCon);
			Service serv = new Service(rDao, uDao);
			
			
			if (!req.getMethod().equals("POST")) { //if user is not using an HTTP post we  prevent submission
				return "html/unsuccessfullogin.html";
			} 
			// process the request info
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String firstname = req.getParameter("firstname");
			String lastname = req.getParameter("lastname");
			String email = req.getParameter("email");
			
			serv.insertUser(new User(username, password, firstname, lastname, email, 2));	
			return "html/register.html";
			
		}
		
		public static String addReimbursement(HttpServletRequest req) {
			System.out.println("in controller addReimbursement");
			log.info("addReimbursement.");
			ERSDbConnection dbCon = new ERSDbConnection();
			UserDaoImpl uDao = new UserDaoImpl(dbCon);
			ReimbursementDaoImpl rDao = new ReimbursementDaoImpl(dbCon);
			Service serv = new Service(rDao,uDao);
			
			if (!req.getMethod().equals("POST")) { // if user is not using an HTTP post we  prevent submission
				return "html/unseccessful.html";
			} 
			// process the request info 
		    double amount = Double.parseDouble(req.getParameter("amount"));	
			String description = req.getParameter("description");	
			int type = -1; 
			System.out.println(req.getParameter("Expense-type"));
			switch(req.getParameter("reimbursement-type")) {
				case "lodging": type = 1;
					break;
				case "travel":type = 2;
					break;
				case "food":type = 3;
					break;
				case "other":
					type = 4;	
			}
			
			User sessUser = (User)req.getSession().getAttribute("currentUser");
			serv.insertReimbursement(new Reimbursement(amount, description, 1, type, sessUser.getUserId()));// add reimbursement ticket to the DB
			
			return "html/employeepage.html";

	}
		
		
		public static String retrieveTickets(HttpServletRequest req) { 
			ERSDbConnection dbCon = new ERSDbConnection();
			UserDaoImpl uDao = new UserDaoImpl(dbCon);
			ReimbursementDaoImpl rDao = new ReimbursementDaoImpl(dbCon);
			Service serv = new Service(rDao, uDao);
			User user = (User) req.getSession().getAttribute("currentUser");
			
			List<Reimbursement> rList = serv.getAllReimbursement();
			
			//List<Reimbursement> rList = serv.getAllReimbursementbyUser(user);

			
			req.getSession().setAttribute("userTickets", rList);
			req.getSession().setAttribute("currentUser", user);
			return "html/retrieveTickets.html";
		}

		
		public static void retrieveTicketsManager(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException { 
			ERSDbConnection dbCon = new ERSDbConnection();
			UserDaoImpl uDao = new UserDaoImpl(dbCon);
			ReimbursementDaoImpl rDao = new ReimbursementDaoImpl(dbCon);
			Service serv = new Service(rDao, uDao);
			User user = (User) req.getSession().getAttribute("currentUser");
			
			List<Reimbursement> rList = serv.getAllReimbursement();
			
			//List<Reimbursement> rList = serv.getAllReimbursementbyUser(user);

			req.getSession().setAttribute("userTickets", rList);
			req.getSession().setAttribute("currentUser", user);
			System.out.println(rList);
			
			res.getWriter().write(new ObjectMapper().writeValueAsString(rList));
		
			
		}
		
		
		public static String viewTickets(HttpServletRequest req) { 
		
			ERSDbConnection dbCon = new ERSDbConnection();
			UserDaoImpl uDao = new UserDaoImpl(dbCon);
			ReimbursementDaoImpl rDao = new ReimbursementDaoImpl(dbCon);
			Service serv = new Service(rDao,uDao);
			User user = (User) req.getSession().getAttribute("currentUser");
			
			List<Reimbursement> rList = serv.getAllReimbursement();
			req.getSession().setAttribute("allTickets", rList);
			req.getSession().setAttribute("currentUser", user);
			return "html/Reimbursementtickets.html";


}
}


