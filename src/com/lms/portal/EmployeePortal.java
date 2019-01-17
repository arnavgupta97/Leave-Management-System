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

public class EmployeePortal {

	public void loginEmployee() {
		Scanner sc = new Scanner(System.in);

		try {
			int ch = 0;
			System.out.println("Welcome to Employee portal");
			System.out.print("Enter User ID: ");
			int userid = sc.nextInt();
			System.out.print("Enter Password: ");
			String password = sc.next();
			do {
				if (validateEmployeeCredentials(userid, password)) {
					System.out.println("\n************  Employee Menu *********************");
					System.out.println("1. Apply For Leave");
					System.out.println("2. Cancel Leave");
					System.out.println("3. Leave Status");
					System.out.println("4. Remaining Leaves");
					System.out.println("5. Update Details");
					System.out.println("6. LogOut");
					System.out.print("Enter Your Choice: ");
					ch = sc.nextInt();

					switch (ch) {
					case 1: {
						applyForLeave(userid, sc);
						break;
					}
					case 2:
						cancelLeave(userid);
						break;
					case 3:
						leaveStatus(userid);
						break;
					case 4:
						remainingLeaves(userid);
						break;
					case 5:
						updateDetails(userid, sc);
						break;
					case 6:
						Login.getStarted();
						break;
					}
				} else {

					System.out.print("Do you want to try again Yes/No: ");
					String choice = sc.next();
					if (choice.equals("Yes")) {
						System.out.println("Welcome to Employee portal");
						System.out.println("Enter Username: ");
						userid = sc.nextInt();
						System.out.println("Enter Password: ");
						password = sc.next();
					} else {
						return;
					}
				}
			} while (ch < 7);
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

	private boolean validateEmployeeCredentials(int id, String password) {
		boolean flag = false;
		String SQL = "SELECT id,password FROM employee";

		try (Connection conn = connect();
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(SQL);) {
			while (rs.next()) {
				int UserId = rs.getInt("id");
				String Password = rs.getString("password");
				System.out.println(UserId + " " + Password);
				if (UserId == id && Password.equals(password)) {
					flag = true;
					break;
				} else {
					flag = false;
				}
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return flag;
	}

	private void applyForLeave(int id, Scanner sc) {
		String SQL1 = "SELECT * FROM leaves";
		String SQL2 = "INSERT INTO leaves(id,applied_leaves,remaining_leaves,status,start_date) VALUES(?,?,?,?,?)";
		String SQL3 = "UPDATE leaves SET applied_leaves=?, remaining_leaves=?, status=?, start_date=? WHERE id=?";
		System.out.print("Enter No of Days for Leave: ");
		int days = sc.nextInt();
		System.out.print("Enter Start Date for Leave: ");
		String date = sc.next();
		boolean leaveApplied = false;
		try (Connection conn = connect(); Statement statement = conn.createStatement();) {
			ResultSet rs = statement.executeQuery(SQL1);
			while (rs.next()) {
				String status = rs.getString("status");
				int userId = rs.getInt("id");
				if (id == userId
						&& (status.equals("Approved") || status.equals("Rejected") || status.equals("Canceled"))) {
					PreparedStatement pstatement = conn.prepareStatement(SQL3);
					leaveApplied = true;
					int remaining_leaves = rs.getInt("remaining_leaves") - days;
					pstatement.setInt(1, days);
					pstatement.setInt(2, remaining_leaves);
					pstatement.setString(3, "Pending");
					pstatement.setString(4, date);
					pstatement.setInt(5, id);
					System.out.println("\n**************************");
					System.out.println("Leaves Applied Successfully");
					System.out.println("\n**************************");
					pstatement.executeUpdate();
				} else if (status.equals("Pending")) {
					leaveApplied = true;
					System.out.println("\n**************************");
					System.out.println("Leaves Already Applied");
					System.out.println("\n**************************");
				}
			}
			if (!leaveApplied) {
				PreparedStatement pstatement = conn.prepareStatement(SQL2);
				pstatement.setInt(1, id);
				pstatement.setInt(2, days);
				pstatement.setInt(3, 20);
				pstatement.setString(4, "Pending");
				pstatement.setString(5, date);
				System.out.println("\n**************************");
				System.out.println("Leaves Applied Successfully");
				System.out.println("\n**************************");
				pstatement.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void cancelLeave(int id) {

		String SQL1 = "SELECT * FROM leaves";
		String SQL2 = "UPDATE leaves SET applied_leaves=?, remaining_leaves=?, status=?, start_date=? WHERE id=?";
		try (Connection conn = connect();
				Statement statement = conn.createStatement();
				PreparedStatement pstatement = conn.prepareStatement(SQL2)) {
			ResultSet rs = statement.executeQuery(SQL1);
			while (rs.next()) {
				int userId = rs.getInt("id");
				if (userId == id) {
					int applied_leaves = rs.getInt("applied_leaves");
					int remaining_leaves = rs.getInt("remaining_leaves");
					int x = applied_leaves + remaining_leaves;
					if (x > Constants.maxLeave) {
						x = Constants.maxLeave;
					}
					pstatement.setInt(1, 0);
					pstatement.setInt(2, x);
					pstatement.setString(3, "Canceled");
					pstatement.setString(4, rs.getString("start_date"));
					pstatement.setInt(5, id);
					pstatement.executeUpdate();
					return;
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\n**********************");
		System.out.println("Not Able to Cancel");
		System.out.println("**********************\n");
	}

	private void leaveStatus(int id) {

		try (Connection conn = connect(); Statement statement = conn.createStatement()) {
			String SQL = "SELECT id,status FROM leaves";
			ResultSet rs = statement.executeQuery(SQL);
			while (rs.next()) {
				int userId = rs.getInt("id");
				String status = rs.getString("status");
				if (userId == id) {
					System.out.println(status);
					return;
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Leaves Not Applied");
	}

	private void remainingLeaves(int id) {

		try (Connection conn = connect(); Statement statement = conn.createStatement()) {
			String SQL = "SELECT id,remaining_leaves FROM leaves";
			ResultSet rs = statement.executeQuery(SQL);
			while (rs.next()) {
				int userId = rs.getInt("id");
				int remaining_leaves = rs.getInt("remaining_leaves");
				if (userId == id) {
					System.out.println("\n************************************");
					System.out.println("Remaining Leaves: " + remaining_leaves);
					System.out.println("************************************\n");
					return;
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Remaining Leaves: 20");
	}

	private void updateDetails(int id, Scanner sc) {

		String SQL = "UPDATE employee SET name=?, password=?, contact=?, email=? WHERE id=?";

		try (Connection conn = connect(); PreparedStatement statement = conn.prepareStatement(SQL)) {
			System.out.print("Enter Employee New Name: ");
			String name = sc.next();
			statement.setString(1, name);
			System.out.print("Enter Employee New Password: ");
			String password = sc.next();
			statement.setString(2, password);
			System.out.print("Enter Employee New Contact: ");
			int contact = sc.nextInt();
			statement.setInt(3, contact);
			System.out.print("Enter Employee New Email: ");
			String email = sc.next();
			statement.setString(4, email);
			statement.setInt(5, id);
			statement.executeUpdate();
			System.out.println("\n**********************");
			System.out.println("Updated Successfully");
			System.out.println("**********************\n");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
