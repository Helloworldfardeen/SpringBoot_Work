package com.mini.project.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini.project.Model.Customer;
import com.mini.project.Repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	 private CustomerRepository cr;
	public Customer saveDetailsService(Customer customer)
	{
		return cr.save(customer);
	}
	public List<Customer> showDetails ()
	{
		return cr.findAll();
	}
	public Optional<Customer> getByCustomerId(long id)
	{
		return cr.findById(id);
	}
	public void delectById(long id) {
	  cr.deleteById(id);
	}

}
