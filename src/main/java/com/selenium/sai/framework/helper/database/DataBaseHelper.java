package com.selenium.sai.framework.helper.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import com.selenium.sai.framework.helper.logger.LoggerHelper;

public class DataBaseHelper {
	
	private static Logger log = LoggerHelper.getLogger(DataBaseHelper.class);
	private static String dbUrl = "jdbc:mysql://localhost:3306/Employeeportal";
	// Username
	private static String username = "root";
	// Database Password
	private static String password = "root";
	private static String driverName="com.mysql.cj.jdbc.Driver";
	// Create Connection to DB
	private static Connection connection;
	private static DataBaseHelper instance=null;
	
	public DataBaseHelper() {
		
		connection=getSingleInstanceConnection();
	}

	private Connection getSingleInstanceConnection() {
		try {
			// load JDBC driver or register the driver
			Class.forName(driverName);

			try {
				connection = DriverManager.getConnection(dbUrl, username, password);
				if(connection!=null) {
					log.info("Connected to database...");
					
				}

			} catch (SQLException e) {
				log.error("Failed to create Data base connection..." + e);
			}
		} catch (ClassNotFoundException e) {
			log.error("Data base driver is not intialized..." +e);

		}
		return connection;

	}
	
	
	public static DataBaseHelper getInstance() {
		
		if(instance==null) {
			instance= new DataBaseHelper();
		}
		return instance;
		
	}
	
	//not neccessary but we can use by creating the object of database helper class 
	public Connection getConnection() {
		return connection;
		
	}
	
	
	public static ResultSet getResultSet(String sql) {
		instance = DataBaseHelper.getInstance();
		connection= instance.getConnection();
		log.info("Executing query: "+sql);
		try {
			Statement stmt= connection.createStatement();
			ResultSet  result= stmt.executeQuery(sql);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
