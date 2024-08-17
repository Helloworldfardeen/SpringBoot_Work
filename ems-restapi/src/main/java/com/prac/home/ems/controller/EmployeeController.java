package com.prac.home.ems.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prac.home.ems.exception.ResourceNotFoundException;
import com.prac.home.ems.model.EmployeeModel;
import com.prac.home.ems.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	@Autowired
	EmployeeService empService;

	@PostMapping("/employeeSaveOne") // http://localhost:8082/ems/api/employeeSaveOne
	public ResponseEntity<EmployeeModel> savingOneController(@Validated @RequestBody EmployeeModel emp) {
		try {
			EmployeeModel pojo = empService.savingOneServ(emp);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(pojo);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PostMapping("/employeeSaveAll") // URL//http://localhost:8082/ems/api/employeeSaveAll
	public ResponseEntity<List<EmployeeModel>> savingAllController(@Validated @RequestBody List<EmployeeModel> emp) {
		try {
			List<EmployeeModel> pojo = empService.savingAllServ(emp);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(pojo);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/employeeShowALL") // URL -> //http://localhost:8082/ems/api/employeeShowALL
	public ResponseEntity<List<EmployeeModel>> showDetailsAll() {
		List<EmployeeModel> pojo = empService.showAllDetails();
		if (pojo.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(pojo);
	}

	@GetMapping("/employeeShowById/{id}") // URL -> //http://localhost:8082/ems/api/employeeShowById/id
	public ResponseEntity<EmployeeModel> showDetailsByid(@PathVariable(value = "id") long empcode)
			throws ResourceNotFoundException {
		EmployeeModel pojo = empService.showById(empcode)
				.orElseThrow(() -> new ResourceNotFoundException("Empcode Not Match..."));
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(pojo);
	}

	@DeleteMapping("/employeeDeleteById/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteDataById(@PathVariable(value = "id") long empcode)
			throws ResourceNotFoundException {
//		EmployeeModel pojo = empService.showById(empcode)
//				.orElseThrow(() -> new ResourceNotFoundException("Empcode Not Match..."));
		empService.DeleteById(empcode);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", true);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/employeeUpdateById/{id}")
	public ResponseEntity<EmployeeModel> UpdateDataById(@PathVariable(value = "id") long empcode,
			@Validated @RequestBody EmployeeModel var) throws ResourceNotFoundException {
		try {
			EmployeeModel pojo = empService.showById(empcode)
					.orElseThrow(() -> new ResourceNotFoundException("Your Id Not Match.."));
			pojo.setEmpname(var.getEmpname());
			pojo.setDepartment(var.getDepartment());
			pojo.setSalary(var.getSalary());
			EmployeeModel updatedData = empService.savingOneServ(pojo);
			return ResponseEntity.status(HttpStatus.OK).body(updatedData);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(null);
		}
	}

}
