package com.zy.sso.systemManage.user.service;

import java.util.List;

import com.zy.sso.base.result.Result;
import com.zy.sso.systemManage.paramVo.UserPara;
import com.zy.sso.systemManage.user.entity.UserEntity;

public interface UserServiceI {
	
	/** 根据用户名获取用户所有信息
	 * @param name
	 * @return 
	 */
	public Result<UserEntity> queryUserByUserName(String name);

	/**查询用户列表
	 * @return
	 */
	Result<List<UserEntity>> getUserList(UserPara u);
}
