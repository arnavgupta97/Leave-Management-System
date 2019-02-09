package com.jersey.dao;

import com.jersey.entities.Employee;
import com.jersey.entities.Leave;
import com.jersey.operations.Login;

public class EmployeeDao {

	public Leave leaveDetails(int id) {

		Employee employee = Login.session.get(Employee.class, id);
		if(employee.getLeave()!=null) {
		return employee.getLeave();
		}
		return (new Leave(0,0,"Not Applied",""));
	}

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

	public Leave cancelLeave(int id) {

		Employee employee = Login.session.get(Employee.class, id);

		Leave leave = Login.session.get(Leave.class, employee.getLeave().getLeaveId());
		int x = leave.getNoOfLeavesApplied();
		int y = leave.getNoOfLeavesRemaining();
		leave.setNoOfLeavesApplied(0);
		leave.setNoOfLeavesRemaining(x + y);
		leave.setStatus("Cancelled");
		leave.setStartDate("NA");

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

	public Leave applyForLeave(int id, int days, String startDate) {

		Employee employee = Login.session.get(Employee.class, id);
            Leave leave2=null;
			if (employee.getLeave() == null) {
				Leave leave = new Leave(days, 20 - days, "Pending", startDate);
				employee.setLeave(leave);
				if (Login.session.getTransaction().isActive()) {
					Login.session.save(employee);
					Login.session.getTransaction().commit();
				} else {
					Login.session.beginTransaction();
					Login.session.save(employee);
					Login.session.getTransaction().commit();
				}
				leave2= leave;
			} else if(!employee.getLeave().getStatus().equals("Pending")){
				Leave leave = Login.session.get(Leave.class, employee.getLeave().getLeaveId());
				int x = leave.getNoOfLeavesRemaining();
				leave.setNoOfLeavesApplied(days);
				leave.setNoOfLeavesRemaining(x - days);
				leave.setStatus("Pending");
				leave.setStartDate(startDate);

				if (Login.session.getTransaction().isActive()) {
					Login.session.update(leave);
					Login.session.getTransaction().commit();
				} else {
					Login.session.beginTransaction();
					Login.session.update(leave);
					Login.session.getTransaction().commit();
				}
				leave2= leave;
			}
			return leave2;
		}
	}
