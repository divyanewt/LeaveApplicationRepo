package com.newt.leaveapplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class LeaveType {
	
	private int leaveTypeId;
	
	private String leaveType;
	public LeaveType(){
		
	}
	public LeaveType(int leaveTypeId, String leaveType) {
		super();
		this.leaveTypeId = leaveTypeId;
		this.leaveType = leaveType;
	}
	public int getLeaveTypeId() {
		return leaveTypeId;
	}
	public void setLeaveTypeId(int leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	@Override
	public String toString() {
		return "LeaveType [leaveTypeId=" + leaveTypeId + ", leaveType=" + leaveType + "]";
	}
	
}
