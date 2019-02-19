package com.jersey.dao;

import com.jersey.entities.Employee;
import com.jersey.entities.Leave;
import com.jersey.operations.Login;

public class EmployeeDao {

//	public Leave leaveDetails(int id) {
//
//		Employee employee = Login.session.get(Employee.class, id);
//		if(employee.getLeave()!=null) {
//		return employee.getLeave();
//		}
//		return (new Leave(0,0,0,"Not Applied","",""));
//	}

	public Employee updateDetails(int id, String name, String password, int contact, String email) {

		Employee employee = Login.session.get(Employee.class, id);

		employee.setName(name);
		employee.setPassword(password);
		employee.setContact(contact);
		employee.setEmail(email);

		if (Login.session.getTransaction().isActive()) {
			Login.session.update(employee);
			Login.session.getTransaction().commit();
		} else {
			Login.session.beginTransaction();
			Login.session.update(employee);
			Login.session.getTransaction().commit();
		}
		return employee;
	}

	public Leave cancelLeave(int leaveId) {

		Leave leave = Login.session.get(Leave.class, leaveId);
		int x = leave.getNoOfLeavesApplied();
		int y = leave.getNoOfLeavesRemaining();
		leave.setNoOfLeavesApplied(0);
		leave.setNoOfLeavesRemaining(x + y);
		leave.setStatus("Cancelled");
//		leave.setStartDate("NA");
//		leave.setDetails("NA");

		if (Login.session.getTransaction().isActive()) {
			Login.session.update(leave);
			Login.session.getTransaction().commit();
		} else {
			Login.session.beginTransaction();
			Login.session.update(leave);
			Login.session.getTransaction().commit();
		}
      
		return leave;
	}

	public Leave applyForLeave(int id, int days, String startDate, String details) {

		Leave leave = new Leave(id, days, 0, "Pending", startDate,details);
		Employee employee = Login.session.get(Employee.class, id);
		if(employee!=null) {
		(employee.getLeave()).add(leave);
		if (Login.session.getTransaction().isActive()) {
			Login.session.save(employee);
			Login.session.getTransaction().commit();
		} else {
			Login.session.beginTransaction();
			Login.session.save(employee);
			Login.session.getTransaction().commit();
		}
		}
		return leave;
	}
	}
