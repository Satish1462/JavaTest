package com.test.TestAssignment1.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.TestAssignment1.Dao.EmployeeRegistrationDao;
import com.test.TestAssignment1.Pojo.Employee;

@Service
public class EmpRegistrationService {
	
	@Autowired
	private EmployeeRegistrationDao empDao;
	
	private final Logger app_logger =LogManager.getLogger(this.getClass());
	
	public int saveEmpDetails(String firstName,String lastName,int empAge,double empSalary,int empExperience){
		int count =0;
		Employee emp = new Employee();
		
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setEmpAge(empAge);
		emp.setEmpSalary(empSalary);
		emp.setEmpExperience(empExperience);
		app_logger.info("About to call DAO method to save emp details");
		try {
			count=empDao.saveEmployeeDetails(emp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			app_logger.error("Error saving emp details ",e);
		}
		
		return count;
		
		
		
	}

}
