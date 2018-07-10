package com.test.TestAssignment1.Dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.test.TestAssignment1.Pojo.Employee;

@Repository
public class EmployeeRegistrationDao extends DatabaseConnection{
	
	private final Logger app_logger =LogManager.getLogger(this.getClass());

	private static String EMP_REG_QUERY= "insert into employee_details(emp_id,emp_fname,emp_lname,emp_age,emp_salary,emp_exp) values(Emp_seq_ID.NEXTVAL,?,?,?,?,?)";
	
  public int saveEmployeeDetails(Employee emp)throws Exception{
		
	  app_logger.info("About to execute save query to save emp details");
		
		int count = getJdbcTemplate().update(EMP_REG_QUERY, new Object[]{emp.getFirstName(),emp.getLastName(),emp.getEmpAge(),emp.getEmpSalary(),emp.getEmpExperience()});
		
		return count;
	}
	
  /*Below is the scenario for Transactional update, if any client passes bunch/batch of Employee objects to insert in a single request
   * we will process one by one if for any employer record it fails it will rollback entire thing will not update a single record
   */
  
  @Transactional
  public void book(Employee... Employees) {
	 
      for (Employee emp : Employees) {
    	  
    	  app_logger.info("inserting" + emp + " in a seat...");
    	  getJdbcTemplate().update(EMP_REG_QUERY, new Object[]{emp.getFirstName(),emp.getLastName(),emp.getEmpAge(),emp.getEmpSalary(),emp.getEmpExperience()});
      }
  }
	
	
}
