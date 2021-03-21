package com.jdbc.registration.Connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionProvider {

	private static String driver;
	static {
		try {
			driver = "com.mysql.cj.jdbc.Driver";
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public static Connection getConnection() {

		Properties prop = new Properties();
		Connection con = null;
		try {
			File file = new File("src/db.properties");
			FileInputStream fis = new FileInputStream(file);
			if (file.exists()) {
				System.out.println("You have the access to perform the connection operations!!");
				prop.load(fis);

				String JDBC_URL = prop.getProperty("url");
				String USERNAME = prop.getProperty("user");
				String PASSWORD = prop.getProperty("password");

				System.out.println("======================================================================");

				// Validating Username and Password of MYSQL DB
				if (USERNAME.equals("root") && PASSWORD.equals("root")) {
					System.out.println("Username and Password is correct for MYSQL DB!!");
				} else {
					System.out.println("Invalid Credentials!!");
				}

				// Validating and Establishing the Connection

				if (con == null || con != null) {
					con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
					System.out.println("Connection is Established successfully!!!");
				} else {
					System.out.println("Connection is not Established Successfully!!!");
				}
			} else {
				System.out.println("You are not authorized to use!!");
			}
			System.out.println("======================================================================");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return con;
	}

	// Closing the Resources-1
	public static void closeResources_1(PreparedStatement ps, Connection con) {

		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (con != null && !con.isClosed()) {
				con.close();
			}
			System.out.println("Connection is Closed!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Closing the Resources-2
	public static void closeResources_2(ResultSet rs, PreparedStatement ps, Connection con) {

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (con != null && !con.isClosed()) {
				con.close();
			}
			System.out.println("Connection is Closed!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
