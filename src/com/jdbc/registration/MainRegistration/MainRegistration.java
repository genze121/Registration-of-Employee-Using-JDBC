package com.jdbc.registration.MainRegistration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import com.jdbc.registration.Connection.DBConnectionProvider;
import com.jdbc.registration.DAO.EmployeeDAO;
import com.jdbc.registration.Entity.Registration;

public class MainRegistration {

	public static void main(String[] args) {

		@SuppressWarnings("unused")
		Register register = new Register("Java", "JDBC");
		Register.showMenu();
	}
}

class Register {

	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	public static String USER;
	public static String USERID;

	@SuppressWarnings("static-access")
	public Register(String user, String uid) {
		super();
		this.USER = user;
		this.USERID = uid;
	}

	@SuppressWarnings("static-access")
	public static void showMenu() {

		EmployeeDAO dao = new EmployeeDAO(DBConnectionProvider.getConnection());

		char options = '\0';
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome:- " + USER);
		System.out.println("Your ID is:- " + USERID);

		if (USER.equals("Java") && USERID.equals("JDBC")) {
			System.out.println("Username and Userid is correct.You have now access to register!!");
		} else {
			System.out.println("Invalid Credentials!!");
		}

		System.out.println("======================================================================");

		System.out.println("A.REGISTRATION");
		System.out.println("B.UPDATING THE RECORDS");
		System.out.println("C.DELETING RECORDS");
		System.out.println("D.RETRIEVING RECORDS BY ID");
		System.out.println("E.VIEW REGISTRATION");
		System.out.println("F.EXIT");

		System.out.println("======================================================================");

		do {
			System.out.println("Choose your options!!");
			options = scanner.next().charAt(0);

			switch (options) {

			case 'A':
				System.out.println("Perform the Registration Operation.");

				Registration register = new Registration();
				while (true) {
					System.out.println("Enter the First Name:-");
					String firstname = scanner.next();
					System.out.println("Enter the Last Name:-");
					String lastname = scanner.next();
					System.out.println("Enter the User Name:-");
					String username = scanner.next();
					System.out.println("Enter the Password:-");
					String password = scanner.next();

					register.setFirstname(firstname);
					register.setLastname(lastname);
					register.setUsername(username);
					register.setPassword(password);

					boolean registration = dao.registerEmployee(register);
					if (registration) {
						System.out.println("Registration Successfully Completed!!!");
					} else {
						System.out.println("Registration failed!!!");
					}

					System.out.println("Do you want to register once again? If then type [Yes|No]");
					String option = scanner.next();

					if (option.equalsIgnoreCase("NO")) {
						break;
					}
				}
				System.out.println("======================================================================");

				DBConnectionProvider.closeResources_1(ps, con);
				break;

			case 'B':
				System.out.println("Updating the records.");
				Registration updateRegister = new Registration();

				System.out.println("Enter the id to update the record:-");
				int id = scanner.nextInt();
				System.out.println("Enter the first name:-");
				String firstname = scanner.next();
				System.out.println("Enter the last name:-");
				String lastname = scanner.next();
				System.out.println("Enter the username:-");
				String username = scanner.next();
				System.out.println("Enter the password:-");
				String password = scanner.next();

				updateRegister.setId(id);
				updateRegister.setFirstname(firstname);
				updateRegister.setLastname(lastname);
				updateRegister.setUsername(username);
				updateRegister.setPassword(password);

				boolean updateTest = dao.updateEmployee(updateRegister);
				if (updateTest) {
					System.out.println("Updated Successfully!!");
				} else {
					System.out.println("Something went wrong!!");
				}

				System.out.println("======================================================================");

				DBConnectionProvider.closeResources_1(ps, con);
				break;

			case 'C':
				System.out.println("Deleting the Records.");
				while (true) {
					System.out.println("Enter the id to delete the records:-");
					int delId = scanner.nextInt();

					boolean deleteTest = dao.deleteEmployee(delId);
					if (deleteTest) {
						System.out.println("Records deleted successfully!!!");
					} else {
						System.out.println("Something went wrong!!!");
					}

					System.out.println("Do you want to delete more records.If you want then type [Yes|No]");
					String yourChoice = scanner.next();

					if (yourChoice.equalsIgnoreCase("NO")) {
						break;
					}
				}

				System.out.println("======================================================================");

				DBConnectionProvider.closeResources_1(ps, con);
				break;

			case 'D':
				System.out.println("Retrieving records using ID:-");
				System.out.println("Enter the id to fetch the records:-");
				int retrieveId = scanner.nextInt();

				Registration retriveTest = dao.getRegistrationById(retrieveId);
				if (retriveTest == null) {
					System.out.println("Employee Details are not available!!");
				} else {
					System.out.println("======================================================================");
					System.out.println("Your records are......");
					System.out.println("First Name:- " + retriveTest.getFirstname());
					System.out.println("Last Name:- " + retriveTest.getLastname());
					System.out.println("Username:- " + retriveTest.getUsername());
					System.out.println("Password:- " + retriveTest.getPassword());
				}
				System.out.println("======================================================================");

				DBConnectionProvider.closeResources_2(rs, ps, con);
				break;

			case 'E':
				System.out.println("Viewing the Registration.");
				List<Registration> list = dao.getAllRegisterUsers();
				if (list.isEmpty()) {
					System.out.println("Registration is not Successfull!!");
				} else {
					for (Registration items : list) {
						System.out.println("ID = " + items.getId());
						System.out.println("First Name = " + items.getFirstname());
						System.out.println("Last Name = " + items.getLastname());
						System.out.println("Username = " + items.getUsername());
						System.out.println("Password = " + items.getPassword());
						System.out.println("======================================================================");
					}

				}
				DBConnectionProvider.closeResources_2(rs, ps, con);
				break;

			case 'F':
				System.out.println("Exiting the Registration Application!!");
				break;

			default:
				System.out.println("Invalid choice selected.Please try again!!");
				break;

			}
		} while (options != 'F');
	}
}
