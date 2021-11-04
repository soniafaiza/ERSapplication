package com.ERS.Service;

import java.util.List;

import com.ERS.dao.ReimbursementDaoImpl;
import com.ERS.dao.UserDaoImpl;
import com.ERS.model.Reimbursement;
import com.ERS.model.User;

public class Service {
	private ReimbursementDaoImpl rDao;
	private UserDaoImpl uDao;
	
	public Service() {
		this.rDao = new ReimbursementDaoImpl();
		this.uDao = new UserDaoImpl();

	}
	public Service(ReimbursementDaoImpl rDao, UserDaoImpl uDao) {
		super();
		this.rDao = rDao;
		this.uDao = uDao;
	}
	public List<User> getAllUsers() {
		return uDao.getAll();
	}
	
	public List<Reimbursement> getAllReimbursement() {
		return rDao.getAll();
	}

	public List<Reimbursement> getAllReimbursementbyUser(User user) {
		return rDao.getAllByname(user);
	}
	
	
	public User getUserVerify(String username, String password) {
		
		 User user = uDao.getUserByUsername(username);
		 if(user != null) {
			 if(user.getPassword().equals(password)) {
				 return user;
			 }
		 }
		
		return null;

}
	
	public void insertUser(User user) {
		uDao.insert(user);
	}
	
	
	public void insertReimbursement(Reimbursement r) {
		rDao.insert(r);
	}

	public User login(String username, String password) {
		User u = uDao.getUserByUsername(username);
		if (u != null) {
			if (u.getPassword().equals(password)) {
				return u;
			}
		} return null;
		  
	}
	
}
