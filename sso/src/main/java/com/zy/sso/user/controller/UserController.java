package com.zy.sso.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zy.sso.base.result.Result;
import com.zy.sso.base.util.RedisTemplateUtil;
import com.zy.sso.user.entity.UserEntity;
import com.zy.sso.user.service.serviceImpl.UserServiceImpl;

/**
 * @author 用户管理
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@GetMapping("/getUser")
	public Result<UserEntity> getUser(){
		UserEntity u=(UserEntity)RedisTemplateUtil.redisTemplate.opsForHash().get("login","zy");//获取redis
		return Result.success(u);
	}
	
	@GetMapping("/delUser")
	public Result<UserEntity> delUser(){
		RedisTemplateUtil.redisTemplate.opsForHash().delete("login","zy");
		return Result.success();
	}
}
