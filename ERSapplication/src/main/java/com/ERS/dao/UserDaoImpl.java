package com.ERS.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ERS.model.Reimbursement;
import com.ERS.model.User;

public class UserDaoImpl implements GenericDao<User>{
	private ERSDbConnection ERSCon;
	private static final Logger log = Logger.getLogger(UserDaoImpl.class);
	
	
	public UserDaoImpl() {
		
	}
	
	public UserDaoImpl(ERSDbConnection ERSCon) {
		this.ERSCon = ERSCon;
	}

	@Override
	public List<User> getAll() {	
		 List<User> userList = new ArrayList<>();	
         try(Connection con = ERSCon.getDbConnection()){
	
	        String sql = "select * from USERS";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	
	        while(rs.next()) {
		    userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),  
				rs.getString(5), rs.getString(6), rs.getInt(7)));
		    }
	
        }catch(SQLException e) {
	                             log.fatal("Sql exception occured");
	                             e.printStackTrace();
        }
     return userList;
     }
	 
	
	public User getUserByUsername(String username) {
		UserDaoImpl uDao = new UserDaoImpl(ERSCon);
		for (User user : uDao.getAll()) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public User getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(User entity) {
            try(Connection con = ERSCon.getDbConnection()){
           String sql = "{?= call insert_User(?,?,?,?,?,?)}";
           CallableStatement cs = con.prepareCall(sql);
                             cs.registerOutParameter(1, Types.VARCHAR);
                             cs.setString(2, entity.getUsername());
                             cs.setString(3, entity.getPassword());
                             cs.setString(4, entity.getFirstname());
                             cs.setString(5, entity.getLastname());
                             cs.setString(6, entity.getEmail());
                             cs.setInt(7, entity.getRoleId());
                             cs.execute();
             } catch (SQLException e) {
    		  log.warn("SQLException occurred!");
    		  e.printStackTrace();
    		}
		
	}

	@Override
	public void delete(User entity) {
		// TODO Auto-generated method stub
		
		
	}
}

	
	

