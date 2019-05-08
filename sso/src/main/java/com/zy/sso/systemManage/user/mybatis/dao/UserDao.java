package com.zy.sso.systemManage.user.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zy.sso.systemManage.paramVo.UserPara;
import com.zy.sso.systemManage.user.entity.UserEntity;


@Repository
public interface UserDao {

	public UserEntity queryUserByUserName(@Param("userName") String userName);
	
	/**获取用户列表
	 * @param u 条件
	 */
	public List<UserEntity> getUserList(UserPara u);

}
