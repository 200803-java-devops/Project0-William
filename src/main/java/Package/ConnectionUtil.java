package main.java.Package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//this class gets and returns the connection to the database

public class ConnectionUtil {
	
	private static final String username = "postgres";
	private static final String password = "96discgolf1";
	private static final String url = "jdbc:postgresql://localhost:5432/stocks";
	private static Connection connection;
	
	
	public static synchronized Connection getConnection() throws SQLException {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(url, username, password);
		}
		
		
		
		return connection;
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

