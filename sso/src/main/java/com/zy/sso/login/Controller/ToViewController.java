package com.zy.sso.login.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.zy.sso.base.result.Result;
import com.zy.sso.base.util.RedisTemplateUtil;
import com.zy.sso.systemManage.menu.entity.Menu;
import com.zy.sso.systemManage.menu.service.impl.MenuServiceImpl;
import com.zy.sso.systemManage.user.entity.UserEntity;

@Controller
public class ToViewController {
	@Autowired
	private MenuServiceImpl menuServiceImpl;
	
	@GetMapping("/login")
	public String toLogin() {
		return "login";
	}
	
	@GetMapping("/index")
	public String toIndex(ModelMap mmap,String userName) {
		//进入主页时获取菜单、用户等信息
		UserEntity u=(UserEntity)RedisTemplateUtil.redisTemplate.opsForHash().get("login",userName);//获取redis中保存的用户信息
		Result<List<Menu>> resList = menuServiceImpl.selectList(userName);
		System.out.println(resList);
		mmap.put("user", u);
		mmap.put("menus", resList.getData());//从结果集中获取数据
		return "index";
	}
	
	@GetMapping("/main")
	public String toMain() {
		return "main";
	}
}
