package projects.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import projects.exception.DbException;

public class DbConnection 
{
	
	// CONSTANT VARIABLES
	private static String     HOST = "localhost";
	private static String PASSWORD = "project";
	private static String   SCHEMA = "project";
	private static String    USER  = "project";
	private static    int     PORT =  3306;
	
	// METHODS
	public static Connection getConnection()
	{
		String uri = String.format("jdbc:mysql://%s  :  %d  /   %s   ?  user = %s  &  password = %s  &  useSSL=false", 
				                    HOST,  PORT, SCHEMA, USER,  PASSWORD );
		System.out.println("Connection with uri = " + uri);
		
		try {
			Connection conn = DriverManager.getConnection(uri);
			System.out.println("Successfull Connection!");
			return conn;
		} catch (SQLException e) {
			System.out.println("Unable to get connection at " + uri);
			throw new DbException("Unable to get connection at \" + uri");
		}
	}
		

}// CLASS