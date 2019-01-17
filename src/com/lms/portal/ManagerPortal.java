package com.lms.portal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.lms.constants.Constants;

import com.lms.operations.Login;

public class ManagerPortal {

	public void loginManager() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		try {
			int ch = 0;
			System.out.println("WELCOME TO MANAGER PORTAL");
			System.out.print("Enter Username: ");
			String username = sc.next();
			System.out.print("Enter Password: ");
			String password = sc.next();
			do {
				if (username.equals("manager") && password.equals("manager")) {
					System.out.println("*****HOME PAGE*****");
					System.out.println("1. List All Leaves");
					System.out.println("2. Approve Leave");
					System.out.println("3. Reject Leave");
					System.out.println("4. Logout");
					System.out.print("Enter Your Choice: ");
					ch = sc.nextInt();
					switch (ch) {
					case 1: {
						if (leaveListSize() != 0) {
							getLeaveAppliedList();
						} else {
							System.out.println("\n**********************");
							System.out.println("No applied leaves so far");
							System.out.println("**********************\n");
						}
						break;
					}
					case 2: {
						if (leaveListSize() != 0) {
							approveLeave();
						} else {
							System.out.println("\n**********************");
							System.out.println("No applied leaves so far");
							System.out.println("**********************\n");
						}
						break;
					}
					case 3: {
						if (leaveListSize() != 0) {
							rejectLeave();
						} else {
							System.out.println("\n**********************");
							System.out.println("No applied leaves so far");
							System.out.println("**********************\n");
						}
						break;
					}
					case 4:
						Login.getStarted();
						break;
					}
				} else {
					System.out.println("Wrong Username or Password");
					System.out.print("Do you want to try again Yes/No: ");
					String choice = sc.next();
					if (choice.equals("Yes")) {
						System.out.println("WELCOME TO MANAGER PORTAL");
						System.out.println("Enter Username: ");
						username = sc.next();
						System.out.println("Enter Password: ");
						password = sc.next();
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

	public int leaveListSize() {

		int count = 0;
		try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
			String query = "Select count(id) from leaves;";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return count;
	}

	private void approveLeave() {
		System.out.println("\n********************************");
		System.out.println("Approved Leaves For the Employees:");
		String SQL1 = "SELECT * FROM leaves;";
		String SQL2 = "UPDATE leaves SET applied_leaves=?, remaining_leaves=?,status=? WHERE id=?;";
		try (Connection conn = connect();
				Statement statement = conn.createStatement();
				PreparedStatement pstatement = conn.prepareStatement(SQL2)) {
			ResultSet rs = statement.executeQuery(SQL1);
			while (rs.next()) {
				int id = rs.getInt("id");
				int applied_leaves = rs.getInt("applied_leaves");
				int remaining_leaves = rs.getInt("remaining_leaves");
				if (applied_leaves > 0 && remaining_leaves >= 0) {
					pstatement.setInt(1, 0);
					pstatement.setInt(2, remaining_leaves);
					pstatement.setString(3, "Approved");
					pstatement.setInt(4, id);
					pstatement.executeUpdate();
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("********************************\n");
	}

	private void rejectLeave() {
		System.out.println("\n********************************");
		System.out.println("Rejected Leaves For the Employees:");
		String SQL1 = "SELECT * FROM leaves;";
		String SQL2 = "UPDATE leaves SET applied_leaves=?, remaining_leaves=?,status=? WHERE id=?;";
		try (Connection conn = connect();
				Statement statement = conn.createStatement();
				PreparedStatement pstatement = conn.prepareStatement(SQL2)) {
			ResultSet rs = statement.executeQuery(SQL1);
			while (rs.next()) {
				int id = rs.getInt("id");
				int applied_leaves = rs.getInt("applied_leaves");
				int remaining_leaves = rs.getInt("remaining_leaves");
				if (applied_leaves > 0 && remaining_leaves < 0) {
					int z = applied_leaves + remaining_leaves;
					if (z > Constants.maxLeave) {
						z = Constants.maxLeave;
					}
					pstatement.setInt(1, 0);
					pstatement.setInt(2, z);
					pstatement.setString(3, "Rejected");
					pstatement.setInt(4, id);
					pstatement.executeUpdate();
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("********************************\n");
	}

	private void getLeaveAppliedList() {

		try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
			String query = "Select * from leaves;";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("id");
				int applied_leaves = rs.getInt("applied_leaves");
				int remaining_leaves = rs.getInt("remaining_leaves");
				String status = rs.getString("status");
				String start_date = rs.getString("start_date");
				System.out
						.println(id + " " + applied_leaves + " " + remaining_leaves + " " + status + " " + start_date);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
