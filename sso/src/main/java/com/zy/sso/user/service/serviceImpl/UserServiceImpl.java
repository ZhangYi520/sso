package com.zy.sso.user.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.sso.base.result.CodeMsg;
import com.zy.sso.base.result.Result;
import com.zy.sso.user.entity.UserEntity;
import com.zy.sso.user.mybatis.dao.UserDao;
import com.zy.sso.user.service.UserServiceI;

@Service
public class UserServiceImpl implements UserServiceI {
	@Autowired
	private UserDao userDao;
	@Override
	public Result<UserEntity> queryUserByUserName(String userName) {
		if(null != userName && !"".equals(userName)) {
			UserEntity userEntity = userDao.queryUserByUserName(userName);
			if(userEntity != null) {
				return Result.success(userEntity);
			}
		}
		return Result.error(CodeMsg.BASE_SERVER_ERROR);
	}

}
