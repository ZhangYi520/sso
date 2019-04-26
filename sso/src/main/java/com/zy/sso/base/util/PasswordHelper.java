package com.zy.sso.base.util;


import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 
 **************************************************
 * @Description:后台密码util
 * @author:CSX
 * @date:2019年01月14日
 **************************************************
 */
public class PasswordHelper {
	private String algorithmName = "md5";
	private int hashIterations = 2;

	public String encryptPassword(/* UserVo vo */) {
		//String salt=randomNumberGenerator.nextBytes().toHex();
		String newPassword = new SimpleHash(algorithmName, "密码",  "盐", hashIterations).toHex();
		//String newPassword = new SimpleHash(algorithmName, user.getPassword()).toHex();
//		vo.setPassword(newPassword);
		return newPassword;
	}
	public static void main(String[] args) {
		PasswordHelper passwordHelper = new PasswordHelper();
//		UserVo user = new UserVo();
//		user.setUsername("admin1");
//		user.setPassword("123456");
		
		System.out.println(passwordHelper.encryptPassword());
	}
}
