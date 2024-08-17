package com.mini.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini.project.Model.Customer;

public interface CustomerRepository  extends JpaRepository<Customer, Long> {

}
