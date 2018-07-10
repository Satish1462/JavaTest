package com.test.TestAssignment1.Pojo;

public class Employee {

	private String firstName;
	private String lastName;
	private int empAge;
	private double empSalary;
	private int empExperience;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getEmpAge() {
		return empAge;
	}
	public void setEmpAge(int empAge) {
		this.empAge = empAge;
	}
	public double getEmpSalary() {
		return empSalary;
	}
	public void setEmpSalary(double empSalary) {
		this.empSalary = empSalary;
	}
	public int getEmpExperience() {
		return empExperience;
	}
	public void setEmpExperience(int empExperience) {
		this.empExperience = empExperience;
	}
	
	public String toString(){
		
		return "employee details are:" + "empName is :" +this.firstName+this.lastName+"empAge is:" +this.empAge+"empexperience is :" + this.empExperience;
	}
	
}
