package com.lms.portal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.lms.constants.Constants;
import com.lms.dao.User;
import com.lms.operations.Login;

public class AdminPortal {

	public void loginAmdin() {
		Scanner scanner = new Scanner(System.in);
		try {
			int ch = 0;
			System.out.println("\n*********************************");
			System.out.println("Welcome to Admin portal");
			System.out.print("Enter Username: ");
			String username = scanner.next();
			System.out.print("Enter Password: ");
			String password = scanner.next();
			do {
				if (username.equals("admin") && password.equals("admin")) {
					System.out.println("\n************  Admin Menu *********************");
					System.out.println("1. Add Employee");
					System.out.println("2. Delete Employee");
					System.out.println("3. List All Employee");
					System.out.println("4. LogOut");
					System.out.println("*********************************");
					System.out.print("Enter Your Choice: ");
					ch = scanner.nextInt();
					switch (ch) {
					case 1:
						addEmployee(scanner);
						break;
					case 2:
						if (employeeListSize() != 0) {
							delete(scanner);
						} else {
							System.out.println("List is Empty");
						}
						break;
					case 3:
						if (employeeListSize() != 0) {
							displayAllEmployee();
						} else {
							System.out.println("List is Empty");
						}
						break;
					case 4:
						Login.getStarted();
						break;
					}

				} else {
					System.out.println("Wrong Username or Password");
					System.out.print("Do you want to try again Yes/No: ");
					String choice = scanner.next();
					if (choice.equals("Yes")) {
						System.out.println("\n*********************************");
						System.out.println("Welcome to Admin portal");
						System.out.print("Enter Username: ");
						username = scanner.next();
						System.out.print("Enter Password: ");
						password = scanner.next();
					} else {
						return;
					}
				}
			} while (ch < 5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private final String url = "jdbc:postgresql://localhost/LMS";
	private final String user = "postgres";
	private final String password = "password";

	public Connection connect() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	private int employeeListSize() {

		int count = 0;
		try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
			String query = "Select count(id) from employee;";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return count;

	}

	private void displayAllEmployee() {
		try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
			String query = "Select * from employee;";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				int contact = rs.getInt("contact");
				String email = rs.getString("email");
				System.out.println(id + " " + name + " " + password + " " + contact + " " + email);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void addEmployee(Scanner sc) {

		System.out.print("Enter Employee Id to Add: ");
		int id = sc.nextInt();
		System.out.print("Enter Employee Name to Add: ");
		String name = sc.next();
		System.out.print("Enter Employee Password to Add: ");
		String password = sc.next();
		System.out.print("Enter Employee Contact to Add: ");
		int contact = sc.nextInt();
		System.out.print("Enter Employee Email to Add: ");
		String email = sc.next();

		User employee = new User(name, password, contact, email);
		employee.setUserRole(Constants.EMPLOYEE);

		String SQL = "INSERT INTO employee(id,name,password,contact,email) VALUES(?,?,?,?,?)";
		try (Connection conn = connect(); PreparedStatement statement = conn.prepareStatement(SQL)) {
			statement.setInt(1, id);
			statement.setString(2, name);
			statement.setString(3, password);
			statement.setInt(4, contact);
			statement.setString(5, email);
			statement.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void delete(Scanner sc) {

		System.out.print("Enter Employee Id to Delete: ");
		int empId = sc.nextInt();

		String SQL = "DELETE FROM employee WHERE id = ?";
		try (Connection conn = connect(); PreparedStatement statement = conn.prepareStatement(SQL);) {
			statement.setInt(1, empId);
			statement.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
