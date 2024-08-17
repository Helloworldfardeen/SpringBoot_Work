package com.tourandtraveller.model;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String lname;
	private int no_of_people;
	private Date arrival;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}
	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	/**
	 * @return the no_of_people
	 */
	public int getNo_of_people() {
		return no_of_people;
	}
	/**
	 * @param no_of_people the no_of_people to set
	 */
	public void setNo_of_people(int no_of_people) {
		this.no_of_people = no_of_people;
	}
	/**
	 * @return the arrival
	 */
	public Date getArrival() {
		return arrival;
	}
	/**
	 * @param arrival the arrival to set
	 */
	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}
	/**
	 * @return the leaving
	 */
	public Date getLeaving() {
		return leaving;
	}
	/**
	 * @param leaving the leaving to set
	 */
	public void setLeaving(Date leaving) {
		this.leaving = leaving;
	}
	private Date leaving;
	
}
