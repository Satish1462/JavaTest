package com.comcast.client;

import java.util.ArrayList;

import com.comcast.pojo.Employee;
import com.comcast.pojo.EmployeeDeatils;

public class EmployeeInfo {
	
	public EmployeeDeatils edetails = new EmployeeDeatils();
	ArrayList <Employee>l = new ArrayList<Employee>();
	
	public EmployeeInfo(){
		
		Employee emp1 = new Employee("ramu",32,500);
		
		Employee emp2= new Employee("gopi",34,600);
		
		Employee emp3 = new Employee("Suresh",36,700);
		
		Employee emp4 = new Employee("Pravesh",36,700);
		
		Employee emp5 = new Employee("Geetha",36,700);
		
		Employee emp6 = new Employee("John",36,700);
		
		Employee emp7 = new Employee("Peter",36,700);
		
		Employee emp8 = new Employee("Nancy",36,700);
		
		Employee emp9 = new Employee("Curtis",36,700);
		
		Employee emp10 = new Employee("Kathy",36,700);
		Employee emp11 = new Employee("Stokes",36,700);
		
		Employee emp12 = new Employee("Jhonson",36,700);
		l.add(emp1);
		l.add(emp2);
		l.add(emp3);
		l.add(emp4);
		l.add(emp5);
		l.add(emp6);
		l.add(emp7);
		l.add(emp8);
		l.add(emp9);
		l.add(emp10);
		l.add(emp11);
		l.add(emp12);
		edetails.setEmp(l);
	}

	
	
	public ArrayList<Employee> getEmployeeDeatails(){
		
		//System.out.println(edetails.getEmp());
		return edetails.getEmp();
		
	}
	
	
	
	
}
