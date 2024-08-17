package com.mini.project.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mini.project.Exception.ResourcesNotfoundException;
import com.mini.project.Model.Customer;
import com.mini.project.Model.Order;
import com.mini.project.Service.CustomerService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
public class customerController {
	@Autowired
	private CustomerService cs;
	@PostMapping("/saving")
	public ResponseEntity<Customer> savingController(@Validated @RequestBody Customer c)
			throws ResourcesNotfoundException {
		try {
			List<Order> order  = c.getOrder();
//			address.setDealer(d);
//			d.setAddress(address);
			Customer custo = cs.saveDetailsService(c);
			return ResponseEntity.status(HttpStatus.CREATED).body(custo);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/showing")
	public ResponseEntity<List<Customer>> showingdetailsCustomerDetails() {
		try {
			List<Customer> pojo = cs.showDetails();
			if (pojo.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			}
			return ResponseEntity.status(HttpStatus.OK).body(pojo);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("showingbyid/{id}")
	public ResponseEntity<Customer> showingByIdController(@PathVariable(value = "id") long id)
			throws ResourcesNotfoundException {
		try {
			Customer cce = cs.getByCustomerId(id).orElseThrow(() -> new ResourcesNotfoundException("Id not Found"));
			return ResponseEntity.status(HttpStatus.OK).body(cce);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<Map<String, Boolean>> removingDetailsById(@PathVariable(value = "id") long id) {
		try {
			Customer cce = cs.getByCustomerId(id).orElseThrow(() -> new ResourcesNotfoundException("Id not Found"));
			if (cce == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			cs.delectById(id);
			Map<String, Boolean> response = new HashMap<>();
			response.put("Deleted", Boolean.TRUE);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(null);
		}
	}

	@PutMapping("update/{id}")
	public ResponseEntity<Customer> updateByIdController(@PathVariable(value = "id") long id,
			@Validated @RequestBody Customer customer) throws ResourcesNotfoundException {
		Customer c = cs.getByCustomerId(id).orElseThrow(() -> new ResourcesNotfoundException("Id Not found"));
	    try {
             c.setName(customer.getName());
             c.setOrder(customer.getOrder());
             Customer UpdatedObject = cs.saveDetailsService(c);
             return ResponseEntity.status(HttpStatus.OK).body(UpdatedObject);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(null);
		}
	}

}
