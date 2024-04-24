package com.duong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping
	public String homeControllerHanddler() {
		return "this is home controller";
	}
	
	@GetMapping("/home")
	public String homeControllerHanddler2() {
		return "this is home controller2";
	}
	
	@GetMapping("/codewithduong")
	public String homeControllerHanddler3() {
		return "Hello Duong";
	}
}
