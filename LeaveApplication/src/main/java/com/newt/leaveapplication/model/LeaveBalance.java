package com.newt.leaveapplication.model;


import java.util.Date;




import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;


public class LeaveBalance {
	
	
	
	@Override
	public String toString() {
		return "LeaveBalance [leaveBalId=" + leaveBalId + ", leaveTypeId=" + leaveTypeId + ", leaveCount=" + leaveCount
				+ ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + ", modifiedBy=" + modifiedBy
				+ ", empId=" + empId + ", leaveType=" + leaveType + "]";
	}

	private int leaveBalId;
	
	private int leaveTypeId;
	
	private int leaveCount;
	
	private Date createDate;
	
	
	private Date modifiedDate;
	
	private String modifiedBy;
	
	private String empId;
	
	private String leaveType;
	 
	
	 public LeaveBalance(){}

	

	public String getLeaveType() {
		return leaveType;
	}



	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}



	public LeaveBalance(int leaveBalId, int leaveTypeId, int leaveCount,
			Date createDate, Date modifiedDate, String modifiedBy, String empId) {
		super();
		this.leaveBalId = leaveBalId;
		this.leaveTypeId = leaveTypeId;
		this.leaveCount = leaveCount;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		this.empId = empId;
	}

	public int getLeaveBalId() {
		return leaveBalId;
	}

	public void setLeaveBalId(int leaveBalId) {
		this.leaveBalId = leaveBalId;
	}

	public int getLeaveTypeId() {
		return leaveTypeId;
	}

	public void setLeaveTypeId(int leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	public int getLeaveCount() {
		return leaveCount;
	}

	public void setLeaveCount(int leaveCount) {
		this.leaveCount = leaveCount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}
	
	

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	

	

	
	

	 
}