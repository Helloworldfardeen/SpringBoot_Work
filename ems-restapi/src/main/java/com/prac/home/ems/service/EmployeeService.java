package com.prac.home.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prac.home.ems.model.EmployeeModel;
import com.prac.home.ems.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository empRepository;

	public EmployeeModel savingOneServ(EmployeeModel emp) {
		return empRepository.save(emp);
	}

	public List<EmployeeModel> savingAllServ(List<EmployeeModel> emp) {
		return empRepository.saveAll(emp);
	}

	public List<EmployeeModel> showAllDetails() {
		return empRepository.findAll();
	}

	public Optional<EmployeeModel> showById(long empcode) {
		return empRepository.findById(empcode);
	}

	public void DeleteById(long empcode) {
		empRepository.deleteById(empcode);
	}
	
}
