package com.training.cdac.pms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.training.cdac.pms.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
//	@Query("Select s from Student s  where s.grade = :studentGrade")
//	public List<Student> searchbygrade(@Param("name") char name);

}
