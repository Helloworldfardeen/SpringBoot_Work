package com.training.cdac.pms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "StudentData")
public class Student {

	@Id
//	@GeneratedValue//if you write the result then you wont able to implement auto increment.
	private int studentID;
	private String StudentName;
	private double StudentMarks;
//	 @Column(name = "grade")
	private char StudentGrade;

//	public Student(int studentID, String studentName, double studentMarks, char studentGrade) {
//		super();
//		this.studentID = studentID;
//		StudentName = studentName;
//		StudentMarks = studentMarks;
//		StudentGrade = studentGrade;
//	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public String getStudentName() {
		return StudentName;
	}

	public void setStudentName(String studentName) {
		StudentName = studentName;
	}

	public double getStudentMarks() {
		return StudentMarks;
	}

	public void setStudentMarks(double studentMarks) {
		StudentMarks = studentMarks;
	}

	public char getStudentGrade() {
		return StudentGrade;
	}

	public void setStudentGrade(char studentGrade) {
		StudentGrade = studentGrade;
	}

}
