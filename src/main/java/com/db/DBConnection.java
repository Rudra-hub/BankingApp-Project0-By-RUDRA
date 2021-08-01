package com.db;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	public static Connection getDBconnection() {
		Connection connection = null;
		Properties properties = new Properties();

// Path for DB.properties access

		try {
			FileReader reader = new FileReader(
					"C:\\Users\\Rudra\\Downloads\\Compressed\\Project0-bankingManagementSystem\\DB.properties");
			try {
				properties.load(reader);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String driver = null;
		String url = null;
		String username = null;
		String password = null;

//Access of Driver, Jdbc-url, Username, Password from DB.properties file

		driver = properties.getProperty("driver");
		url = properties.getProperty("url");
		username = properties.getProperty("username");
		password = properties.getProperty("password");

		try {
			Class.forName(driver); // Type 4 driver

			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;
	}

}
