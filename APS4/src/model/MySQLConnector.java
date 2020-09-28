package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {
	private final static String DATABASE = "mercado";
	private final static String URL = "jdbc:mysql://localhost:3306/" + DATABASE;
	private final static String USER = "root";
	private final static String PASS = "";
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASS);
		}catch(SQLException e) {
			e.getMessage();
		}
		
		return connection;
	}
}
