package com.jersey.dao;

import java.util.List;

import javax.persistence.Query;
//import javax.ws.rs.core.Response;

import com.jersey.entities.Employee;
import com.jersey.entities.Leave;
import com.jersey.entities.Project;
import com.jersey.operations.Login;

public class ManagerDao {

	@SuppressWarnings("unchecked")
	public List<Leave> getLeaveList() {

		List<Leave> leaveList = null;
		try {
			String queryStr = "select leave from Leave leave";
			Query query = Login.session.createQuery(queryStr);
			leaveList = query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return leaveList;
	}

	public Leave approveLeave() {

		List<Leave> leave = getLeaveList();
        Leave leave2=null;
		for (Leave leaves : leave) {

			if (leaves.getNoOfLeavesApplied() > 0 && leaves.getNoOfLeavesRemaining() >= 0) {
				leaves.setStatus("Approved");
				leaves.setNoOfLeavesApplied(0);
				leave2=leaves;
			} else if (leaves.getNoOfLeavesApplied() >= 0 && leaves.getNoOfLeavesRemaining() <= 0) {
				leaves.setStatus("Rejected");
				int x = leaves.getNoOfLeavesApplied();
				int y = leaves.getNoOfLeavesRemaining();
				leaves.setNoOfLeavesRemaining(x + y);
				leaves.setNoOfLeavesApplied(0);
				leave2=leaves;
			}
			if (Login.session.getTransaction().isActive()) {
				Login.session.update(leaves);
				Login.session.getTransaction().commit();
			} else {
				Login.session.beginTransaction();
				Login.session.update(leaves);
				Login.session.getTransaction().commit();
			}
		}
		return leave2;
	}

	public Leave rejectLeave() {

		List<Leave> leave = getLeaveList();
        Leave leave2=null;
		for (Leave leaves : leave) {
			if (leaves.getNoOfLeavesApplied() >= 0 && leaves.getNoOfLeavesRemaining() <= 0) {
				leaves.setStatus("Rejected");
				int x = leaves.getNoOfLeavesApplied();
				int y = leaves.getNoOfLeavesRemaining();
				leaves.setNoOfLeavesRemaining(x + y);
				leaves.setNoOfLeavesApplied(0);
				leave2=leaves;
			}
			if (Login.session.getTransaction().isActive()) {
				Login.session.update(leaves);
				Login.session.getTransaction().commit();
			} else {
				Login.session.beginTransaction();
				Login.session.update(leaves);
				Login.session.getTransaction().commit();
			}
		}
		return leave2;
	}

	public Project assignProject(String projectName, int projectManagerId, int empId) {
		Project project = new Project(projectName, projectManagerId);

		Employee employee = Login.session.get(Employee.class, empId);

		if (employee != null) {

			(employee.getProject()).add(project);
			if (Login.session.getTransaction().isActive()) {
				Login.session.save(employee);
				Login.session.getTransaction().commit();
			} else {
				Login.session.beginTransaction();
				Login.session.save(employee);
				Login.session.getTransaction().commit();
			}
		} 
		
		return project;
	}

	@SuppressWarnings("unchecked")
	public List<Project> getProjectList() {
		
		List<Project> projectList = null;
		try {
			String queryStr = "select project from Project project";
			Query query = Login.session.createQuery(queryStr);
			projectList = query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return projectList;
	}
}
