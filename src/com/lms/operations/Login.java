package com.lms.operations;

import java.util.Scanner;

import com.lms.constants.Constants;
import com.lms.entities.User;
import com.lms.datasource.DataSource;
import com.lms.portal.AdminPortal;
import com.lms.portal.EmployeePortal;
import com.lms.portal.ManagerPortal;

public class Login {

	public static void main(String[] args) {
		getStarted();
	}

	public static void getStarted() {
		Scanner scanner = new Scanner(System.in);
		AdminPortal adminPortal = new AdminPortal();
		ManagerPortal managerPortal = new ManagerPortal();
		EmployeePortal employeePortal = new EmployeePortal();

		int ch;
		try {
			do {
				System.out.println("\n*********************************");
				System.out.println("1. Login as an Admin");
				System.out.println("2. Login as a Manager");
				System.out.println("3. Login as an Employee");
				System.out.println("4. Exit");
				System.out.println("*********************************");
				System.out.print("Enter your choice : ");
				ch = scanner.nextInt();
				switch (ch) {
				case 1:
					User admin = new User("admin", "admin", 452145, "admin@abyeti.com");
					DataSource.currentLoggedInUser = admin;
					DataSource.currentLoggedInUser.setUserRole(Constants.ADMIN);
					adminPortal.loginAmdin();
					break;
				case 2:
					
					User manager = new User("manager", "manager", 452145, "manager@abyeti.com");
					DataSource.currentLoggedInUser = manager;
					DataSource.currentLoggedInUser.setUserRole(Constants.MANAGER);
					managerPortal.loginManager();
					break;
				case 3:
					DataSource.currentLoggedInUser.setUserRole(Constants.EMPLOYEE);
					employeePortal.loginEmployee();
					break;
				case 4:
					System.exit(0);
					break;
				}
			} while (ch < 5);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		scanner.close();
	}
}
