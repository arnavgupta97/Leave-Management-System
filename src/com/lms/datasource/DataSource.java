package com.lms.datasource;

import java.util.HashMap;

import com.lms.entities.Leave;
import com.lms.entities.Employee;

public class DataSource {
	public static HashMap<Integer, Employee> employeeList = new HashMap<>();
	public static HashMap<Integer, Leave> leaveList = new HashMap<>();
	public static Employee currentLoggedInUser;
}
