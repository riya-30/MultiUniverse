package com.assignment.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	public static Connection getConnection() throws Exception
	{
		try
		{
			String connectionURL = "jdbc:mysql://localhost:3306/universe?useSSL=false";
			Connection connection = null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, "root", "admin@8901");
			return connection;
		}
		catch (SQLException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw e;
		}
	}

}
