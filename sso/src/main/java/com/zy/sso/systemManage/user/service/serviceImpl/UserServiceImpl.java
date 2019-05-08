package com.zy.sso.systemManage.user.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zy.sso.base.result.CodeMsg;
import com.zy.sso.base.result.Result;
import com.zy.sso.systemManage.paramVo.UserPara;
import com.zy.sso.systemManage.user.entity.UserEntity;
import com.zy.sso.systemManage.user.mybatis.dao.UserDao;
import com.zy.sso.systemManage.user.service.UserServiceI;

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
	
	@Override
	public Result<List<UserEntity>> getUserList(UserPara u) {
		PageHelper.startPage(u.getPage(), u.getPageSize());
		List<UserEntity> l=userDao.getUserList(u);
		System.out.println("server:"+l.toString());
		return Result.success(l);
	}

}
