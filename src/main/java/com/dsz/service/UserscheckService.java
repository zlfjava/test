package com.dsz.service;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsz.entity.Userscheck;
import com.dsz.util.FenyeUtil;


/**
 * 打卡业务层
 */
public interface UserscheckService {

	/**
	 * 用户打卡
	 * @param uid
	 * @param username
	 * @return
	 */
	Integer userCheck(String uid, String username, String idcard);

	/**
	 * 用户签退
	 * @param uid
	 * @param username
	 * @return
	 * @throws ParseException 
	 */
	Map<String, Object> signOut(String uid, String username) throws ParseException;

	/**
	 * 检查是否签退了
	 * @param uid
	 * @param username
	 * @return
	 */
	Integer checkSignOut(String uid, String username);
	
	/**
	 * 多条件分页查询
	 * @param fy
	 * @return
	 */
	FenyeUtil<Userscheck> getAll(FenyeUtil<Userscheck> fy);
	
	/**
	 * 导出打卡记录
	 * @param request
	 * @param response
	 * @param ids
	 */
	void exportUserscheck(HttpServletRequest request, HttpServletResponse response, String ids);

}
