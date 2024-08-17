package com.demo.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.batch.entity.Customer;
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
