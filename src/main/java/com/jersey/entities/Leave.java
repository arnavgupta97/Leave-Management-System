package com.jersey.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement (name="leave")
public class Leave {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int leaveId;
	private int noOfLeavesApplied;
	private int noOfLeavesRemaining;
	private String status;
	private String startDate;

	public Leave() {
		
	}
	public Leave(int noOfLeavesApplied, int noOfLeavesRemaining, String status, String startDate) {
		this.noOfLeavesApplied = noOfLeavesApplied;
		this.noOfLeavesRemaining = noOfLeavesRemaining;
		this.status = status;
		this.startDate = startDate;
	}

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public void setNoOfLeavesRemaining(int noOfLeavesRemaining) {
		this.noOfLeavesRemaining = noOfLeavesRemaining;
	}

	public int getNoOfLeavesApplied() {
		return noOfLeavesApplied;
	}

	public void setNoOfLeavesApplied(int noOfLeavesApplied) {
		this.noOfLeavesApplied = noOfLeavesApplied;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public int getNoOfLeavesRemaining() {
		return noOfLeavesRemaining;
	}
	
	@Override
	public String toString() {
		return "Leave [leaveId=" + leaveId + ", noOfLeavesApplied=" + noOfLeavesApplied + ", noOfLeavesRemaining="
				+ noOfLeavesRemaining + ", status=" + status + ", startDate=" + startDate + "]";
	}
	
	
}
