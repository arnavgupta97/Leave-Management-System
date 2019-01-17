package com.lms.datasource;

import java.util.HashMap;

import com.lms.entities.Leave;
import com.lms.entities.User;

public class DataSource {
	public static HashMap<Integer, User> employeeList = new HashMap<>();
	public static HashMap<Integer, Leave> leaveList = new HashMap<>();
	public static User currentLoggedInUser;
}
