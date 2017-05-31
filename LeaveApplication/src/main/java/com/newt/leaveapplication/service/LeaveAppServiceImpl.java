package com.newt.leaveapplication.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newt.leaveapplication.dao.LeaveAppRepository;
import com.newt.leaveapplication.model.LeaveApplication;

@Service("LeaveAppImpl")
public class LeaveAppServiceImpl implements LeaveAppService {
	@Autowired
	LeaveAppRepository leaveAppRepo;

	public List<LeaveApplication> getAllLeave() {
		return leaveAppRepo.findAll();
	}

	public LeaveApplication addLeave(LeaveApplication leaveApp) {
		/*Date fromDate =leaveApp.getFromDate();
		Date toDate=leaveApp.getToDate();
		long diff = toDate.getTime()-fromDate.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);
		Integer i = (int) (long) diffDays;
		leaveApp.setNoOfDays(i+1);*/
		LeaveApplication leave=leaveAppRepo.save(leaveApp);

		return leave;
		
	}

	public List<LeaveApplication> getLeave(long empId) {
		
		return leaveAppRepo.findAll();
	}

	public LeaveApplication updateLeave(LeaveApplication leaveApp) {
		
		LeaveApplication leave=leaveAppRepo.save(leaveApp);
		
		return leave;
	
	}

	

	public void deleteleave(int leaveAppId) {
		leaveAppRepo.delete(leaveAppId);
		
	}

	public LeaveApplication getLeaveById(int leaveAppId) {
		return leaveAppRepo.findOne(leaveAppId);
	}

	public List<LeaveApplication> getLeaveByEmpId(String empId) {
		return leaveAppRepo.findByEmpId(empId);
	
	}

	@Transactional
	public void updateLeaveByEmpId(LeaveApplication leaveApp, int leaveAppId, String empId) {
		String comments=leaveApp.getComments();
		Date createDate=leaveApp.getCreateDate();
		Date FromDate=leaveApp.getFromDate();
		int halfDay=leaveApp.getHalf_day();
		int leaveStatusId=leaveApp.getLeaveStatusId();
		int leaveTypeId=leaveApp.getLeaveTypeId();
		String modifiedBy=leaveApp.getModifiedBy();
		Date modifiedDate=leaveApp.getModifiedDate();
		int noOfDays=leaveApp.getNoOfDays();
		Date toDate=leaveApp.getToDate();
		 leaveAppRepo.updateLeaveByEmpId(comments,createDate,FromDate,  halfDay,
				leaveStatusId,leaveTypeId,modifiedBy,modifiedDate,noOfDays,toDate,leaveAppId,empId);
	}

	@Override
	public int getMaxValue() {
		return leaveAppRepo.getMaxValue();
	}

	@Override
	public LeaveApplication findByLeaveAppIdAndEmpId(int leaveAppId, String empId) {
		// TODO Auto-generated method stub
		return leaveAppRepo.findByLeaveAppIdAndEmpId(leaveAppId, empId);
		
	}

	public List<String> getPendingByLeaveStatusId(int leaveStatusId) {
		  return leaveAppRepo.findByleaveStatusId(leaveStatusId);
		 }

	@Override
	public List<LeaveApplication> findBysupervisorID(String id) {
		// TODO Auto-generated method stub
		return leaveAppRepo.findBysupervisorID(id);
	}

	

	


}
