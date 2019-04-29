package com.zy.sso.login.Controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zy.sso.base.result.CodeMsg;
import com.zy.sso.base.result.Result;
import com.zy.sso.base.util.PasswordHelper;
import com.zy.sso.user.entity.UserEntity;
import com.zy.sso.user.mybatis.dao.UserDao;
import com.zy.sso.user.service.serviceImpl.UserServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "登录-zy-0" })
@SuppressWarnings(value = { "all" })
public class LoginController {
	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping("/login")
	@ApiOperation(value = "登录测试")
	public Result<Object> demo(@RequestBody UserEntity user) {
		PasswordHelper pwd = new PasswordHelper();
		System.out.println("加密前:"+user.toString());
		pwd.encryptPassword(user);
		System.out.println("加密后:"+user.toString());
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
		try {
			subject.login(token);
//			Result<UserEntity> result = userServiceImpl.queryUserByUserName(user.getUserName());
//	        UserEntity userEntity=result.getData();
//			System.out.println("登陆成功");
//			subject.getSession().setAttribute("user", userEntity);
			return Result.success();
		} catch (LockedAccountException lae) {
			lae.printStackTrace();

		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		return Result.error(CodeMsg.BASE_LOGIN_ERROR);

	}
}
