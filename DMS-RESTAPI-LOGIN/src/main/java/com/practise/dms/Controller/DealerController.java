package com.practise.dms.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practise.dms.Model.Address;
import com.practise.dms.Model.Dealer;
import com.practise.dms.ResourcesNotFoundException.ResourceNotFoundException;
import com.practise.dms.Service.DealerService;

/*
{
	"email": "deep1@gmail.com",
	"fname": "Deepak",
	"lname": "Malhotra",
	"password": "Hello123",
	"dateofbirth": "2017-02-04",
	"phonenumber": "9247230764",
	"address": {
	    "street": "Park Street",
	    "city": "Kolatta",
	    "pincode": "360041"
	}
	}
	*/
@RestController
@RequestMapping("/api")
public class DealerController {
	@Autowired
	private DealerService dService;

	@PostMapping("/registration") // http://localhost:8082/DMS/api/registration
	public ResponseEntity<String> registrationController(@Validated @RequestBody Dealer d) {
		try {
			Address address = d.getAddress();
			// Establish the bi-directional relationship
			address.setDealer(d);
			d.setAddress(address);
			Dealer registrationDealer = dService.saveDetails(d);
			if (registrationDealer != null) {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body("Registration Succesfull");
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration Failed");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An Error Occur" + e.getMessage());
		}
	}

	@PostMapping("/login")
	public ResponseEntity<Boolean> loginController(@Validated @RequestBody Dealer d) throws ResourceNotFoundException {
		try {
			String email = d.getEmail();
			String password = d.getPassword();
			Boolean isCheck = false;
			Dealer dealer = dService.findByEmail(email)
					.orElseThrow(() -> new ResourceNotFoundException("User Not Found of this Email.."));
			if (password.equals(dealer.getPassword()) && email.equals(dealer.getEmail())) {
				isCheck = true;
			}
			return ResponseEntity.status(HttpStatus.OK).body(isCheck);
		} catch (Exception e) {
                return ResponseEntity.internalServerError().body(null);
		}
	}
//	@GetMapping("/showDetails") // http://localhost:8082/DMS/api/showDetails
//	public ResponseEntity<List<DealerAndAddressProjection>> showDetailsController() throws ResourceNotFoundException {
//		try {
//			List<DealerAndAddressProjection> selectedFields = dService.showDealerDetails();
//			return ResponseEntity.status(HttpStatus.OK).body(selectedFields);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//
//		}
//	}

}
