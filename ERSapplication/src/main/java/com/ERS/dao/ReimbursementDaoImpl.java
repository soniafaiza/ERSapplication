package com.ERS.dao;

import java.sql.Timestamp;

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

public class ReimbursementDaoImpl implements GenericDao<Reimbursement> {
	//private ERSDbConnection ErsCon;
	private static final Logger log = Logger.getLogger(ReimbursementDaoImpl.class);
	
	private ERSDbConnection ERSCon; //we are setting  the class up to have its instances be ready of dependency injection by other entities.
	
	public ReimbursementDaoImpl() {
	}
	
	public ReimbursementDaoImpl(ERSDbConnection ERSCon) {
		this.ERSCon = ERSCon;
	}
	
	     @Override
	            public List<Reimbursement> getAll() {
		               List<Reimbursement> ReimbList = new ArrayList<>();
		
		            try(Connection con = ERSCon.getDbConnection()){
			
			        String sql = "select * from REIMBURSEMENT";
			        PreparedStatement ps = con.prepareStatement(sql);
			        ResultSet rs = ps.executeQuery();
			
			        while(rs.next()) {
				    ReimbList.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4),  
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
				    }
			
		           }catch(SQLException e) {
			                             log.fatal("Sql exception occured");
			                             e.printStackTrace();
		           }
		        return ReimbList;}

	@Override
	public Reimbursement getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	               @Override
	               public void update(Reimbursement entity) {
	            	   try (Connection con = ERSCon.getDbConnection()) {
	           			String sql = "update Reimbursements set Reim_RESOLVED=?, REIMB_STATUS_ID=? where REIMB_ID=?";
	           			PreparedStatement ps = con.prepareStatement(sql);
	           			ps.setTimestamp(1, entity.getResolved());
	           			ps.setInt(2, entity.getStatusId());
	           			ps.setInt(3, entity.getReimbursementId());
	           			ps.execute();
	           		} catch (SQLException e) {
	           			log.warn("SQLException occurred!");
	           		}
	           	}
                   @Override
	               public void insert(Reimbursement entity) {
                        try(Connection con = ERSCon.getDbConnection()){
			
			           String sql = "{?= call insert_REIMBURSEMENT(?,?,?,?,?,?)}";
			           CallableStatement cs = con.prepareCall(sql);
			
			                             cs.registerOutParameter(1, Types.VARCHAR);
			                             cs.setDouble(2, entity.getAmount());
			         
			                             cs.setString(3, entity.getDescription());
			                             cs.setInt(4, entity.getAuthor());
			                             cs.setInt(5, entity.getResolver());
			                             cs.setInt(6, entity.getStatusId());
			                             cs.setInt(7, entity.getTypeId());
			                             cs.execute();
                         } catch (SQLException e) {
                		  log.fatal("SQLException occurred!");
                		  e.printStackTrace();
                		}

                	}
	@Override
	public void delete(Reimbursement entity) {
		// TODO Auto-generated method stub
		
	}

	 public List<Reimbursement> getAllByname(User user) {
         List<Reimbursement> ReimbList = new ArrayList<>();

      try(Connection con = ERSCon.getDbConnection()){

      String sql = "select * from REIMBURSEMENT where reimb_author=?";
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setInt(1, user.getUserId());
      ResultSet rs = ps.executeQuery();

      while(rs.next()) {
	    ReimbList.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4),  
			rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
	    }

     }catch(SQLException e) {
                           log.fatal("Sql exception");
                           e.printStackTrace();
     }
  return ReimbList;}

	
	
	
}
	