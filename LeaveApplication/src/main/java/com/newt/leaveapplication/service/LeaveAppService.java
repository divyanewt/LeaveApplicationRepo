package com.newt.leaveapplication.service;

import java.util.List;
import com.newt.leaveapplication.model.LeaveApplication;

public interface LeaveAppService {
	 public List<LeaveApplication> getAllLeave() ;
	 public LeaveApplication addLeave(LeaveApplication leaveApp);
	 public LeaveApplication getLeaveById(int leaveAppId);
	 public LeaveApplication updateLeave(LeaveApplication leaveApp);
	 public void deleteleave(int leaveAppId);
	 public List<LeaveApplication> getLeaveByEmpId(String empId);
	 public void updateLeaveByEmpId(LeaveApplication leaveApp, int leaveId, String empId);
	 public int getMaxValue();
	 public LeaveApplication findByLeaveAppIdAndEmpId(int leaveAppId,String empId);
	 public List<String> getPendingByLeaveStatusId(int leaveStatusId);
	 public List<LeaveApplication>findBysupervisorID(String id);
	
	 
}
