package com.zy.sso.login.Controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zy.sso.base.result.CodeMsg;
import com.zy.sso.base.result.Result;
import com.zy.sso.base.util.PasswordHelper;
import com.zy.sso.base.util.RedisTemplateUtil;
import com.zy.sso.systemManage.user.entity.UserEntity;
import com.zy.sso.systemManage.user.service.serviceImpl.UserServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "登录-zy-0" })
@SuppressWarnings(value = { "all" })
//@RequestMapping("/login")
public class LoginController {
	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping("/login")
	@ApiOperation(value = "登录测试")
	public Result<Object> demo(@RequestBody UserEntity user) {
		PasswordHelper pwd = new PasswordHelper();
		System.out.println("加密前:"+user.toString());
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
		try {
			token.setRememberMe(true);
			subject.login(token);
			
			Result<UserEntity> result = userServiceImpl.queryUserByUserName(user.getUserName());
	        UserEntity userEntity=result.getData();
	        String tokenId = (String) subject.getSession().getId();
	        System.out.println("当前登录用户的sessionId:"+tokenId);
	        userEntity.setToken(tokenId);
			userEntity.setPassword(null);
			//把用户信息保存进redis，采用hash
			RedisTemplateUtil.redisTemplate.opsForHash().put("login",user.getUserName(),userEntity);
			log.info("成功写入缓存:"+userEntity.toString());
//			subject.getSession().setAttribute("user", userEntity);
			
			return Result.success(userEntity);
		} catch (LockedAccountException lae) {
			lae.printStackTrace();
			return Result.error(CodeMsg.USER_PWD_ERROR);

		} catch (AuthenticationException e) {
			e.printStackTrace();
			return Result.error(CodeMsg.USER_PWD_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error(CodeMsg.BASE_SERVER_ERROR);
		}
	}
	
//	@GetMapping("/logout")
//	public Result<Object> logout(String userName){
//		//退出登录，清楚缓存中的用户信息
//		
//		return Result.success();
//	}
}
