package com.prac.home.ems.model;

import jakarta.persistence.*;

@Entity
@Table(name ="employee")
public class EmployeeModel{
	@SequenceGenerator(name = "emp_seq", initialValue = 1000, allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "emp_seq")
    long empcode;
	String empname;
	String department;
	long salary;
	public long getEmpcode() {
		return empcode;
	}
	public void setEmpcode(long empcode) {
		this.empcode = empcode;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	

}
