package com.zy.sso.systemManage.menu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.sso.base.result.Result;
import com.zy.sso.base.util.TreeUtils;
import com.zy.sso.systemManage.menu.entity.Menu;
import com.zy.sso.systemManage.menu.mybatis.dao.MenuMapper;
import com.zy.sso.systemManage.menu.service.MenuServiceI;

@Service
public class MenuServiceImpl implements MenuServiceI{
	@Autowired
	private MenuMapper menuMapper;
	@Override
	public Result<List<Menu>> selectList(String userName) {
		// TODO Auto-generated method stub
		List<Menu> selectList = menuMapper.selectList();//获取菜单数据
		List<Menu> childPerms = TreeUtils.getChildPerms(selectList, 0);//转换成tree结构
		return Result.success(childPerms);
	}

}
