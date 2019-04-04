package com.zy.sso.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demo {

	@GetMapping("/test")
	public String to() {
		return "11";
	}
}
