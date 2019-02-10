package com.jersey.messenger;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jersey.dao.ManagerDao;
import com.jersey.entities.Employee;
import com.jersey.entities.Leave;
import com.jersey.entities.Project;

@Path("manager")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ManagerResource {

	@GET
	public List<Leave> getLeaves() {
		ManagerDao manager = new ManagerDao();
		List<Leave> leave = manager.getLeaveList();
		return leave;
	}

	@GET
	@Path("getProjects")
	public List<Project> getProjects() {
		ManagerDao manager = new ManagerDao();
		List<Project> project = manager.getProjectList();
		return project;
	}

	@POST
	@Path("approve")
	public Leave approveLeaves() {
		ManagerDao manager = new ManagerDao();
		return manager.approveLeave();
	}

	@POST
	@Path("assignProject")
	public Project assignProjects(Employee employee) {
		
		ManagerDao manager = new ManagerDao();
		int empId=employee.getId();
		Project project=null;
		for(Project p : employee.getProject()) {
			project=p;
			break;
		}
		String projectName=project.getProjectName();
		int managerId = project.getProjectManagerId();
		
		System.out.println(employee);
		
		return manager.assignProject(projectName, managerId, empId);
		
	}
	
}
