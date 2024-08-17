package com.training.cdac.pms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	//http://localhost:8083/productapp/
	@GetMapping("/")
	public String viewhomepg() {
		return "index";
	}

}
