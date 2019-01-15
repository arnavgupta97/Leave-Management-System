package com.lms.data.source;

import java.util.HashMap;

import com.lms.dao.Leave;
import com.lms.dao.User;

public class DataSource {
	public static HashMap<Integer, User> employeeList = new HashMap<>();
	public static HashMap<Integer, Leave> leaveList = new HashMap<>();
	public static User currentLoggedInUser;
}
