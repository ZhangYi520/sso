package com.zy.sso.user.mybatis.dao;

import org.springframework.stereotype.Repository;

import com.zy.sso.user.entity.UserEntity;

@Repository
public interface UserDao {

	public UserEntity queryUserByUserName(String userName);
	

}
