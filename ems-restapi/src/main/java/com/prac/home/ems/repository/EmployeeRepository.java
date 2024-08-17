package com.prac.home.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prac.home.ems.model.EmployeeModel;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long>
{
//	try this afterwward..
//@Query("select e from employee e where lower(replace( e.empname,' ','')) "
//		+ "like lower(concat(replace(:empname,' ',''),'%'))")
//List<EmployeeModel> findNameWithIgnoreSpaces(@Param("empname") String name);
}
