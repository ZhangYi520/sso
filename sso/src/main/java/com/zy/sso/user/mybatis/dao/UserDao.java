package com.zy.sso.user.mybatis.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zy.sso.user.entity.UserEntity;

@Repository
public interface UserDao {

	public UserEntity queryUserByUserName(@Param("userName") String userName);

}
