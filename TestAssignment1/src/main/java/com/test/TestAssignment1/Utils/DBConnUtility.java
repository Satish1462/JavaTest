package com.test.TestAssignment1.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;


@Configuration
@PropertySources({@PropertySource(value="file:/c:/Props/DbProps.properties",ignoreResourceNotFound=true)})
public class DBConnUtility {

	
	
	
	private final Logger app_logger =LogManager.getLogger(this.getClass());
		
		@Autowired
		private Environment env;
		
		
		private static String Db_driver;
		
		private static String Db_connection;
		
		private static String Db_user;
		
		private static String Db_password;

		

		@Autowired(required=true)
		public static void setDb_driver(@Value("${db_driver:DB_DRIVER}")String db_driver) {
			DBConnUtility.Db_driver = db_driver;
		}

		
		@Autowired(required=true)
		public static void setDb_connection(@Value("${db_connection:DB_CONNECTION}")String db_connection) {
			DBConnUtility.Db_connection = db_connection;
		}

		
		@Autowired(required=true)
		public static void setDb_user(@Value("${db_user:DB_USER}")String db_user) {
			DBConnUtility.Db_user = db_user;
		}

		
		@Autowired(required=true)
		public static void setDb_password(@Value("${db_password:DB_PASSWORD}")String db_password) {
			DBConnUtility.Db_password = db_password;
		}
		
		
		public String getProperty(String propname){
			
			return env.getProperty(propname);
		}


		/*public Environment getEnv() {
			return env;
		}*/


		public static String getDb_driver() {
			return Db_driver;
		}


		public static String getDb_connection() {
			return Db_connection;
		}


		public static String getDb_user() {
			return Db_user;
		}


		public static String getDb_password() {
			return Db_password;
		}
		
		public static void readProperties()
		{
			Properties prop = new Properties();
			InputStream propInput = null;
			
			try{
				propInput = new FileInputStream("C:\\Props\\DbProps.properties");
				prop.load(propInput);
				
				DBConnUtility.Db_driver=prop.getProperty("DB_DRIVER");
				DBConnUtility.Db_connection = prop.getProperty("DB_CONNECTION");
				DBConnUtility.Db_user = prop.getProperty("DB_USER");
				DBConnUtility.Db_password = prop.getProperty("DB_PASSWORD");
				
			}catch(IOException e){
				e.printStackTrace();
				
			}
			
		}	
}
