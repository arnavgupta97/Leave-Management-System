package com.lms.entities;

import com.lms.constants.Constants;

public class Leave {

	private int noOfLeavesApplied;
	private String status;
	private String startDate;

	public Leave(int noOfLeavesApplied, String status, String startDate) {
		this.noOfLeavesApplied = noOfLeavesApplied;
		this.status = status;
		this.startDate = startDate;
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
		return Constants.maxLeave - noOfLeavesApplied;
	}
}
