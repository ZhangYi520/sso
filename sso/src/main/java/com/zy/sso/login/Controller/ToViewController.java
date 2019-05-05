package com.zy.sso.login.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.zy.sso.base.util.RedisTemplateUtil;
import com.zy.sso.user.entity.UserEntity;

@Controller
public class ToViewController {
	
	@GetMapping("/login")
	public String toLogin() {
		return "login";
	}
	
	@GetMapping("/index")
	public String toIndex(ModelMap mmap,String userName) {
		//进入主页时获取菜单、用户等信息
		UserEntity u=(UserEntity)RedisTemplateUtil.redisTemplate.opsForHash().get("login",userName);//获取redis中保存的用户信息
		mmap.put("user", u);
		return "index";
	}
	
	@GetMapping("/main")
	public String toMain() {
		return "main";
	}
}
