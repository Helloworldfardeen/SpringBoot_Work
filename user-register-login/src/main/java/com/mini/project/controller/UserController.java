package com.mini.project.controller;

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

import com.mini.project.exception.ResourcesNotFoundException;
import com.mini.project.model.AboutUser;
import com.mini.project.model.ShowProfile;
import com.mini.project.model.User;
import com.mini.project.service.UserService;

@RestController
@RequestMapping("/api") // http://localhost:8082/url/api/save
public class UserController {
	@Autowired
	UserService us;

	@PostMapping("/save")
	public ResponseEntity<String> savingController(@Validated @RequestBody User u) {
		try {
			AboutUser au = u.getAboutUser(); // Retrieve AboutUser details from User
			au.setUser(u); // Set the User object in AboutUser (bidirectional relationship)
			u.setAboutUser(au); // Set AboutUser object in User
			User resgis = us.saveingDetails(u);
			if (resgis == null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Registration Failed");
			}
			return ResponseEntity.status(HttpStatus.CREATED).body("Registration Successfull");

		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("DataBases,ServerError");
		}
	}

	@PostMapping("/login")
	public ResponseEntity<Boolean> userLogin(@Validated @RequestBody User u) {
		String email = u.getEmail();
		String password = u.getPassword();
		User modelUser;
		try {
			modelUser = us.getbyEmail(email).orElseThrow(() -> new ResourcesNotFoundException("Not Found"));

			if (email.equals(modelUser.getEmail()) && password.equals(modelUser.getPassword())) {
				return ResponseEntity.ok(Boolean.TRUE);
			}
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Boolean.FALSE);
		} catch (ResourcesNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	 @GetMapping("/profiles")
	    public ResponseEntity<List<ShowProfile>> getProfiles() {
	        try {
	            List<ShowProfile> profiles = us.showingDetailsService();
	            if (profiles.isEmpty()) {
	                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	            }
	            return ResponseEntity.ok(profiles);
	        } catch (Exception e) {
	            return ResponseEntity.internalServerError().build();
	        }
	    }
//	@GetMapping("/show")
//	public ResponseEntity<List<ShowProfile>> getuserdetails ()
//	{
//		try {
//			List<ShowProfile> sp = us.showingDetailsService();
//			return ResponseEntity.status(HttpStatus.OK).body(sp);
//			
//			
//		} catch (Exception e) {
//			
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//		}
//	}
}