package com.jdbc.registration.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.registration.Entity.Registration;

@SuppressWarnings("static-access")
public class EmployeeDAO {

	private static Connection con;

	public EmployeeDAO(Connection con) {
		super();
		this.con = con;
	}

	// Insert Query
	private static final String INSERT_SQL_QUERY = "insert into register" + "(firstname,lastname,username,password)"
			+ "values(?,?,?,?)";

	// Update Query
	private static final String UPDATE_SQL_QUERY = "update register set firstname=?,lastname=?,username=?,password=? where id=?";

	// Delete Query
	private static final String DELETE_SQL_QUERY = "delete from register where id=?";

	// Select Query for id
	private static final String SELECT_SQL_QUERY_BY_ID = "select * from register where id=?";

	// Select Query
	private static final String SELECT_SQL_QUERY_LIST = "select * from register";

	// Logic for registering the details of the Employee

	public static boolean registerEmployee(Registration register) {

		boolean flag = false;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(INSERT_SQL_QUERY);
			ps.setString(1, register.getFirstname());
			ps.setString(2, register.getLastname());
			ps.setString(3, register.getUsername());
			ps.setString(4, register.getPassword());

			int insertedRecord = ps.executeUpdate();
			if (insertedRecord == 1 || insertedRecord > 0) {
				flag = true;
			}
			System.out.println("The no of registration completed:- " + insertedRecord);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flag;
	}

	// Logic for updating an Employee records
	public static boolean updateEmployee(Registration register) {
		boolean flag = false;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(UPDATE_SQL_QUERY);
			ps.setString(1, register.getFirstname());
			ps.setString(2, register.getLastname());
			ps.setString(3, register.getUsername());
			ps.setString(4, register.getPassword());
			ps.setInt(5, register.getId());

			int updatedRecords = ps.executeUpdate();
			if (updatedRecords == 1 || updatedRecords > 0) {
				flag = true;
			}
			System.out.println("The no of records updated:- " + updatedRecords);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flag;
	}

	// Logic for deleting the records

	public static boolean deleteEmployee(int id) {

		boolean flag = false;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(DELETE_SQL_QUERY);
			ps.setInt(1, id);

			int deletedRecord = ps.executeUpdate();
			if (deletedRecord == 1 || deletedRecord > 0) {
				flag = true;
			}
			System.out.println("The number of records deleted:- " + deletedRecord);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flag;

	}

	// Logic for retrieving the records using id
	public static Registration getRegistrationById(int id) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Registration register = null;
		try {
			ps = con.prepareStatement(SELECT_SQL_QUERY_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				register = new Registration();
				register.setFirstname(rs.getString(2));
				register.setLastname(rs.getString(3));
				register.setUsername(rs.getString(4));
				register.setPassword(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return register;

	}

	// Logic for retrieving the records using List
	public List<Registration> getAllRegisterUsers() {

		List<Registration> lists = new ArrayList<Registration>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(SELECT_SQL_QUERY_LIST);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String firstname = rs.getString(2);
				String lastname = rs.getString(3);
				String username = rs.getString(4);
				String password = rs.getString(5);

				lists.add(new Registration(id, firstname, lastname, username, password));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;

	}

}
