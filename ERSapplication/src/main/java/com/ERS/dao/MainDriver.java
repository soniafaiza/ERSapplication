package com.ERS.dao;

import com.ERS.model.Reimbursement;
import com.ERS.model.User;

public class MainDriver {
	public static void main(String[]args) {
		
		ERSDbConnection con = new ERSDbConnection();
		ReimbursementDaoImpl RDao = new ReimbursementDaoImpl(con); // we are manually injecting the DBConnection  
		System.out.println(RDao.getAll());

		//Reimbursement Reimb = new Reimbursement(100.00, null,"Food", 2600);
		//System.out.println("java");
		 //RDao.insert(Reimb);	
		 //System.out.print(RDao.insert(Reimb));
		
		  //UserDaoImpl UDao = new UserDaoImpl(con);
     	//System.out.println(UDao.getAll());
		 //UDao.insert(new User(2,"user1","Password1","Sonia","Ricci","sonia@rev.net",1));
	}

}
