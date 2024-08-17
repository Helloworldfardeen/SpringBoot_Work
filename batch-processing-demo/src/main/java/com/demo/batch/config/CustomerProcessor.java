package com.demo.batch.config;

import org.springframework.batch.item.ItemProcessor;

import com.demo.batch.entity.Customer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerProcessor implements ItemProcessor<Customer, Customer> {

	private static final Logger log = LoggerFactory.getLogger(CustomerProcessor.class);

//	@Override
//	public Customer process( final Customer customer) throws Exception {
//		
//		final int id = customer.getId();
//		final String firstName = customer.getFirstName().toUpperCase();
//		final String lastName = customer.getLastName().toUpperCase();
//		final String email = customer.getEmail().toUpperCase();
//		final String gender = customer.getGender().toUpperCase();
//		final String contractNo = customer.getContactNo().toUpperCase();
//		final String country = customer.getCountry().toUpperCase();
//		final String dob = customer.getDob().toUpperCase();
//		final Customer transformedPerson = new Customer(id,firstName,lastName,email,gender,contractNo,country,dob);
//		log.info("Converting (" + customer + ") into (" + transformedPerson + ")");
//
//		return transformedPerson;

		 @Override
		    public Customer process(Customer customer) throws Exception {
		        if(customer.getCountry().equals("United States")) {
		            return customer;
		        }else{
		            return null;
		        }
		    }
	}




	
	

