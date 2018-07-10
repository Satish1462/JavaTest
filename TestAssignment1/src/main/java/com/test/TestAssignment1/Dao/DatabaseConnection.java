package com.test.TestAssignment1.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Repository;

import com.test.TestAssignment1.Utils.DBConnUtility;


@Repository
public class DatabaseConnection {
	
	@Autowired
	private DBConnUtility dbUtil;
	
	private JdbcTemplate jdbctemplate;
	
	private final Logger app_logger =LogManager.getLogger(this.getClass());
	
	Connection conn = null;
	
	public Connection getJdbcConnection(){
		
		if(conn == null){
			dbUtil.readProperties();
			System.out.println("Db driver is:" +dbUtil.getDb_driver());
			System.out.println("Db conn is:" + dbUtil.getDb_connection());
			System.out.println("Db user is:" +dbUtil.getDb_user());
			
			try{
				Class.forName(dbUtil.getDb_driver());
			}catch(ClassNotFoundException ex){
				ex.printStackTrace();
				app_logger.error("error registering driver class is:", ex);
			}
			  
			try{
				conn =DriverManager.getConnection(dbUtil.getDb_connection(), dbUtil.getDb_user(), dbUtil.getDb_password());
				return conn;
			}catch(SQLException e){
				e.printStackTrace();
				app_logger.error("error getting DB connection is:",e);
			}
		}
		System.out.println("Connection is:" + conn.toString());
		return conn;
	}
	
	public JdbcTemplate getJdbcTemplate(){
		
		if(conn==null){
			getJdbcConnection();
		}
		
		jdbctemplate = new JdbcTemplate(new SingleConnectionDataSource(conn,true));
		app_logger.info("Got the jdbctemplate");
		return jdbctemplate;
	}

}
