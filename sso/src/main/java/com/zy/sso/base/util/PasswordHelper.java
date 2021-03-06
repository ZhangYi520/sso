package com.zy.sso.base.util;


import org.apache.shiro.crypto.hash.SimpleHash;

import com.zy.sso.systemManage.user.entity.UserEntity;



/**
 * 
 **************************************************
 * @Description:后台密码util
 * @author:ZY
 * @date:2019年01月14日
 **************************************************
 */
public class PasswordHelper {
	private static final String algorithmName = "md5";
	private static final int hashIterations = 2;

	public static String encryptPassword(UserEntity e) {
		//String salt=randomNumberGenerator.nextBytes().toHex();
		String newPassword = new SimpleHash(algorithmName,e.getPassword(),e.getUserName(), hashIterations).toHex();
		//String newPassword = new SimpleHash(algorithmName, user.getPassword()).toHex();
		e.setPassword(newPassword);
		return newPassword;
	}
	public static void main(String[] args) {
		UserEntity user = new UserEntity();
		user.setUserName("zy1");
		user.setPassword("1234");
		PasswordHelper.encryptPassword(user);
		System.out.println(user.toString());
	}
}
