package com.zy.sso.login.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ToViewController {
	
	@GetMapping("/login")
	public String toLogin() {
		return "login/login";
	}
	
	@GetMapping("/main")
	public String toMain() {
		return "main";
	}
}
