package com.training.cdac.Demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
/*
 * Overall, @RestController is a powerful annotation in Spring Boot that
 * simplifies the development of RESTful web services by combining the
 * functionality of @Controller and @ResponseBody, providing automatic
 * conversion of Java objects to JSON or XML, and making the intent of the class
 * clear.
 */
public class HelloWorldController {
	@GetMapping("/")
	public String sayhello() {
		return "hello World TO Spring boot";
	}

	@GetMapping("/cdac")
	public String sayhelloAgain() {
		return "hello World to Springboot from Cdac";
	}

}
