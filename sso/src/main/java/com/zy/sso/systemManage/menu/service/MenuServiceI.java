package com.zy.sso.systemManage.menu.service;

import java.util.List;

import com.zy.sso.base.result.Result;
import com.zy.sso.systemManage.menu.entity.Menu;

public interface MenuServiceI {
	//根据用户名查询当前用户的角色信息
	Result<List<Menu>> selectList(String userName);

}
