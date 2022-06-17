package com.home.amazon.provisional.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AuroraConnectionSimpleJDBC {
	
	protected Connection getConnection() throws SQLException, ClassNotFoundException {
		String url = "jdbc:mysql://pocdatabase8.cluster-ci4bqd15smsv.us-east-2.rds.amazonaws.com:3306";
		String username = "admin";
		String password = "XtQXUvivnanK7AGv7CEN";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

}
