package com.zy.sso.systemManage.user.service;

import com.zy.sso.base.result.Result;
import com.zy.sso.systemManage.user.entity.UserEntity;

public interface UserServiceI {
	
	/** 根据用户名获取用户所有信息
	 * @param name
	 * @return 
	 */
	public Result<UserEntity> queryUserByUserName(String name);
}
