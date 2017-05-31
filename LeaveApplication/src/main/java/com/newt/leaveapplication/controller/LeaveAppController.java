package com.newt.leaveapplication.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.newt.leaveapplication.model.Employee;
import com.newt.leaveapplication.model.LeaveApplication;
import com.newt.leaveapplication.model.LeaveBalance;
import com.newt.leaveapplication.service.LeaveAppService;

@RestController
@RequestMapping("/LeaveAppController")
@CrossOrigin(maxAge=3600)
public class LeaveAppController {
	@Autowired
	LeaveAppService leaveAppService;
	LeaveBalance bal=null;
	
	RestTemplate restTemplate= new RestTemplate();
	
	@RequestMapping(method= RequestMethod.GET,value="/get", produces = "application/json")
	public List<LeaveApplication> getAllLeaveApplication(){
		List<LeaveApplication> leaveList=leaveAppService.getAllLeave();
		return leaveList;
		
	}

	@RequestMapping(method=RequestMethod.POST,value="/add")
	public String addLeaveApplication(@RequestBody LeaveApplication leaveApp){
		String employeeServiceName = "192.168.2.72:8769";
	     String employeeUrl = "http://" + employeeServiceName;
	     
	     String leaveBalanceServiceName="192.168.2.72:8767";
	     String leavebalanceUrl="http://" + leaveBalanceServiceName;
	     
		Calendar cal=Calendar.getInstance();
		Date currentDate=cal.getTime();
		Date fromDate =leaveApp.getFromDate();
		Date toDate=leaveApp.getToDate();
		long diff = toDate.getTime()-fromDate.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);
		Integer i = (int) (long) diffDays;
		
		
		leaveApp.setNoOfDays(i+1);
		leaveApp.setCreateDate(currentDate);
		leaveApp.setModifiedDate(currentDate);
		leaveApp.setLeaveAppId(leaveAppService.getMaxValue());
		
		System.out.println("Adding :"+leaveApp);
		
		String id=leaveApp.getEmpId();
	    String empId=leaveApp.getEmpId();
	    
	    Employee employee=restTemplate.getForObject(employeeUrl +"/employee/getEmployeeById/{empId}", Employee.class,empId);
	    
	    leaveApp.setSupervisorId(employee.getSupervisorID());
	    List<LeaveApplication> leave=leaveAppService.getLeaveByEmpId(id);
	    
	    Iterator<LeaveApplication> itr=leave.iterator();
	   
	   if(leave!=null){
	    while(itr.hasNext()){
	     LeaveApplication application=itr.next();
	     if(application.getLeaveStatusId()==1)
	     {
	      return "Already you applied for leave it is not approved";
	     }
	    }
	   }
	   int noOfDays=leaveApp.getNoOfDays();
	   int leaveTypeId=leaveApp.getLeaveTypeId();
	   String leaveEmpId=leaveApp.getEmpId();
	  LeaveBalance leaveBalance= restTemplate.getForObject(leavebalanceUrl +"/leavebalance/get/leavebalancebyempIdandLeavetypeId/{empId}/{leaveTypeId}/{noOfDays}", LeaveBalance.class,leaveEmpId,leaveTypeId,noOfDays);
	   
	   if(leaveBalance!=null){
	  
	    leaveAppService.addLeave(leaveApp);
	    return "Applied successfully";
	}
	else{
		return "you dont have sufficient leave balance";
	}
	}

	@RequestMapping(method=RequestMethod.GET,value="/{empId}", produces = "application/json")
	public  List<LeaveApplication> getLeaveApplicationByEmpId(@PathVariable("empId") String empId){
		return leaveAppService.getLeaveByEmpId(empId);
		
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/delete/{leaveAppId}")
	public String deleteLeaveApplication(@PathVariable("leaveAppId")int leaveAppId){
		leaveAppService.deleteleave(leaveAppId);
		return "deleted sucessfully";
		
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/updateLeave/{leaveappId}/{empId}")
	public String updateLeaveByEmpId(@RequestBody LeaveApplication leaveApp, @PathVariable("leaveappId") int leaveappId, @PathVariable("empId") String empId) throws JsonProcessingException{
		
		String serviceName = "192.168.2.72:8767";
		  String url = "http://" + serviceName;
		  Date fromDate =leaveApp.getFromDate();
			Date toDate=leaveApp.getToDate();
			long diff = toDate.getTime()-fromDate.getTime();
			long diffDays = diff / (24 * 60 * 60 * 1000);
			Integer j = (int) (long) diffDays;
			leaveApp.setNoOfDays(j+1);
		  int noOfLeave = leaveApp.getNoOfDays();
		int leavestatus=leaveApp.getLeaveStatusId();
		if (leavestatus==2){
			LeaveBalance balanceList[] = null;
			   balanceList = restTemplate.getForObject(url + "/leavebalance/get/byempId/{empId}", LeaveBalance[].class,leaveApp.getEmpId());
			   for (int i = 0; i < balanceList.length; i++) {
			    System.out.println(balanceList[i]);
			     bal=balanceList[i];
			    System.out.println("test 1........."+bal.getCreateDate());
			    System.out.println("test2.........."+bal.getModifiedBy());
			    int oldleavecount=bal.getLeaveCount();
			    int newLeaveCount=oldleavecount-noOfLeave;
			    if(leaveApp.getLeaveTypeId()==bal.getLeaveTypeId()){
			    	 System.out.println("test 1........."+bal.getCreateDate());
					    System.out.println("test2.........."+bal.getModifiedBy());
			    	bal.setCreateDate(balanceList[i].getCreateDate());
			    	bal.setModifiedBy(bal.getModifiedBy().toString());
			  
			        bal.setLeaveCount(newLeaveCount);
			        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			        String json = ow.writeValueAsString(bal);
			        System.out.println("test3....."+json);
			        restTemplate.put(url + "/leavebalance/update",bal);
			        //restTemplate.put(url + "/leavebalance/update", "{  \"createDate\": \"2017-05-16\",  \"empId\": \"newtin1000\",  \"leaveBalId\": 1,  \"leaveCount\": 3,  \"leaveType\": \"sickleave\",  \"leaveTypeId\": 1,  \"modifiedBy\": \"newtin1001\",  \"modifiedDate\": \"2017-05-16\"}");
			        }
			        }
			   
		}
		
		 
		 leaveAppService.updateLeaveByEmpId(leaveApp, leaveappId, empId);
		 return "updated sucessfully";
	
	}
	@RequestMapping(method= RequestMethod.GET,value="/getByLeaveAppIdAndEmpId/{leaveAppId}/{empId}", produces = "application/json")
	public LeaveApplication findByLeaveAppIdAndEmpId(@PathVariable("leaveAppId")int leaveAppId,@PathVariable("empId")String empId){
		return leaveAppService.findByLeaveAppIdAndEmpId(leaveAppId, empId);
	}
	
	@RequestMapping(method= RequestMethod.GET,value="/getEmpByPendingStatus/{supervisorId}", produces = "application/json")
	 public List<Employee> getPendingByLeaveStatusId(@PathVariable("supervisorId")String id){
	  String serviceName = "192.168.2.72:8769";
	     String url = "http://" + serviceName;
	     List<LeaveApplication> ListleaveApplication=leaveAppService.findBysupervisorID(id);
	     
	  
	  List<Employee>employeeList=new ArrayList<Employee>();
	  Iterator<LeaveApplication> itr=ListleaveApplication.iterator();
	  while(itr.hasNext()){
		  LeaveApplication leaveApp=itr.next();
		  if(leaveApp.getLeaveStatusId()==1){
		  String empId=leaveApp.getEmpId();
	   System.out.println("empId"+empId);
	   
	   Employee employee=restTemplate.getForObject(url +"/employee/getEmployeeById/{empId}", Employee.class,empId);
	   employeeList.add(employee);
		  }
	   
	  }
	  return employeeList;
	  
	 }
	
	
}


