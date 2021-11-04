package com.ERS.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import jdk.internal.org.jline.utils.Log;

public class ERSDbConnection {

	// This utility class, has the sole purpose of creating connections to the data base, this will help with eliminating repeated code
	//it will also help make our code more testable.

	
	
	ClassLoader classLoader = getClass().getClassLoader();
	InputStream is;
	Properties p = new Properties();
	
	public ERSDbConnection() {
		is = classLoader.getResourceAsStream("connection.properties");
		try {
			p.load(is);
		} catch (IOException e) {
			Log.warn("this is a warn level logging:SQL exceprtion");
			e.printStackTrace();
		}
	}

	public Connection getDbConnection() throws SQLException {	
		final String URL = p.getProperty("URL");
		final String USERNAME = p.getProperty("USERNAME");
		final String PASSWORD = p.getProperty("PASSWORD");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) { System.out.println("classnotfound exception");
		}
		
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	}

