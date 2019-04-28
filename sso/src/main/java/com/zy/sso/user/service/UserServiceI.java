package com.zy.sso.user.service;

import com.zy.sso.user.entity.UserEntity;

public interface UserServiceI {
	
	/** 根据用户名获取用户所有信息
	 * @param name
	 * @return 
	 */
	public UserEntity findByName(String name);
}
