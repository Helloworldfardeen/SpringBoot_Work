package com.mini.project.Model;

import java.util.List;

import jakarta.persistence.*;

@Entity
//@Table(name ="customer")
public class Customer{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	 @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Order> order;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the order
	 */
	public List<Order> getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(List<Order> order) {
		this.order = order;
	}
	
	

}
