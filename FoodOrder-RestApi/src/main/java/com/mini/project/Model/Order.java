package com.mini.project.Model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name ="Orders")
public class Order {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date orderdate;
	@ManyToOne
	@JoinColumn(name ="customer_id")
	Customer customer;
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
	 * @return the orderDate
	kiswx */
	public Date getOrderDate() {
		return orderdate;
	}
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderdate = orderDate;
	}
	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	

}
