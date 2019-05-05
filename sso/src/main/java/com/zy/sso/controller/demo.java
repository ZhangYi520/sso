package com.zy.sso.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demo {

	@GetMapping("/test")
	@RequiresRoles("超级管理员")//shiro角色注解，拥有该角色才能访问
	@RequiresPermissions("/select")//shiro权限注解，拥有该权限才能访问
	public String to() {
		return "11";
	}
	
	@GetMapping("/test2")
	@RequiresPermissions("/delete")
	public String to2() {
		return "2019年4月28日11:21:48";
	}
	
	@GetMapping("/test3")
	@RequiresRoles("超级管理员")//shiro角色注解，拥有该角色才能访问
	@RequiresPermissions("/s")
	public String to3() {
		return "2019年4月28日11:21:48";
	}
}
