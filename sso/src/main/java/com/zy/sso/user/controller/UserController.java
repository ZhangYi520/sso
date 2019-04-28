package com.zy.sso.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.zy.sso.base.result.Result;
import com.zy.sso.user.entity.UserEntity;
import com.zy.sso.user.service.serviceImpl.UserServiceImpl;

/**
 * @author 用户管理
 *
 */
@RestController
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	public Result<UserEntity> queryList(){
		return Result.success();
	}
}
