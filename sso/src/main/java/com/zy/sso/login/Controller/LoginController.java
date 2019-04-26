package com.zy.sso.login.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zy.sso.base.result.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "登录-zy-0" })
public class LoginController {
	
	@GetMapping("/a")
	@ApiOperation(value = "登录测试")
	public Result<String> demo() {
		return Result.success();
	}
}
