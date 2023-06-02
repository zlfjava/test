package com.dsz.util;

import org.springframework.util.DigestUtils;

/**
 * MD5加密工具类
 */
public class MD5Util {
	
	//前置盐
	private String firstSalt = "qqfeiche";
	//后置盐
	private String lastSalt = "wangzherongyao";
	
	/**
	 * 传入参数，获取加密后的密码
	 * @param password
	 * @return
	 */
	public String getMD5Pwd(String password) {
		//拼接前置盐和后置盐
		password = firstSalt + password + lastSalt;
		//返回加密后的密码
		return DigestUtils.md5DigestAsHex(password.getBytes());
	}

}
