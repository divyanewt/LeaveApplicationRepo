package com.newt.leaveapplication.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.newt.leaveapplication.model.LeaveApplication;

@Repository
public interface LeaveAppRepository extends JpaRepository<LeaveApplication, Integer> {
	
	 public long count() ;
	 public List< LeaveApplication> findAll() ;
	 public void delete(Integer leaveAppId);
	 public LeaveApplication findOne(Integer leaveAppId);
	 public <S extends  LeaveApplication> List<S> save(Iterable<S> leaveAppliacation) ;
	 public List<LeaveApplication> findByEmpId(String empId) ;
	 public LeaveApplication findByLeaveAppIdAndEmpId(int leaveAppId,String empId);
	@Modifying
	@Transactional
	 @Query(value="update tbl_leave_application u set u.comments = ?1, u.create_Date = ?2,"
	 		+ "u.from_Date=?3 , u.half_day=?4, u.leave_Status_Id=?5,"
	 		+ "u.leave_Type_Id=?6, u.modified_By=?7,u.modified_Date=?8,"
	 		+ "u.no_Of_Days=?9,u.to_Date=?10  where u.leave_App_Id=?11 and u.emp_Id=?12",nativeQuery=true)
	
	public void updateLeaveByEmpId(String comments, Date createDate, Date fromDate, int halfDay,
			int leaveStatusId, int leaveTypeId, String modifiedBy, Date modifiedDate, int noOfDays, Date toDate,
			int leaveAppId, String empId);
	
	
	@Query(value="select max(leave_App_Id)+1 from tbl_leave_application ", nativeQuery=true)
	public int getMaxValue();
	
	@Query(value="select l.emp_id  from TBL_Leave_Application l where l.LEAVE_STATUS_ID=?1",nativeQuery=true)
	 public List<String> findByleaveStatusId(int leaveStatusId) ;
	
	public List<LeaveApplication> findBysupervisorID(String id);
	 
	
}
