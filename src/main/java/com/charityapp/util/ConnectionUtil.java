package com.charityapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	public static Connection getConnection()
	{
	
		String driverName = "com.mysql.cj.jdbc.Driver";
		
		String url = "jdbc:mysql://localhost:3306/charity_app";
		
		String userName = "root";
		String password = "root";
		
		Connection conn = null;
		
		try {
		
			Class.forName(driverName);
			
			/** Get Connection **/
			conn = DriverManager.getConnection( url, userName, password );
		
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return conn;
	
	}
}
