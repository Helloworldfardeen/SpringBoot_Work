package com.training.cdac.pms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.cdac.pms.model.Student;
import com.training.cdac.pms.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	StudentRepository studRepo;

	public Student saveDetails(Student s) {
		return studRepo.save(s);
	}

	public List<Student> showDetails() {
		return studRepo.findAll();
	}

	public Optional<Student> showDetailsById(int id) {
		return studRepo.findById(id);
	}

	public void DeleteById(int id) {
		studRepo.deleteById(id);
	}
	
//	public List<Student> evaluteByMarks(char grade)
//	{
//		return studRepo.searchbygrade(grade);
//	}
	
//	public Optional<Student> UpdateById(int id)
//	{
//		return studRepo.findById(id);
//	}
// for Entering multiple Entity...or array of object...
//	 public List<Student> createStudents(List<Student> students) {
//	        // Business logic to process the list of students
//	        // For example, save them to the database
//	        for (Student student : students) {
//	            // Save each student (pseudo-code)
//	            // studentRepository.save(student);
//	            System.out.println("Saving student: " + student.getStudentName());
//	        }
//	        return students;
//	    }	

}
