package com.zy.sso.systemManage.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system")
public class ToSysViewController {
	@GetMapping("/toUser")
	public String toUser() {
		return "system/userList";
	}
	
	@GetMapping("/toRole")
	public String toRole() {
		return "system/roleList";
	}
}
