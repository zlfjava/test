package com.dsz.mapper;

import java.util.List;

import com.dsz.entity.Userscheck;
import com.dsz.util.FenyeUtil;

/**
 * 用户打卡记录持久层
 */
public interface UserscheckMapper {
	
	/**
	 * 修改打卡记录
	 * @param uc
	 * @return 数据库受影响行数
	 */
	Integer updateUsersCheck(Userscheck uc);

	/**
	 * 查询当天是否有打卡记录
	 * @param uid
	 * @param username
	 * @param istime
	 * @return Userscheck实体类
	 */
	Userscheck getUserscheck(String uid, String username, String istime);
	
	/**
	 * 获取所有用户
	 * @param fy
	 * @return	所有用户
	 */
	List<Userscheck> getAll(FenyeUtil<Userscheck> fy);

	/**
	 * 获取所有用户条数
	 * @param fy
	 * @return 数据总条数
	 */
	Integer getCount(FenyeUtil<Userscheck> fy);

	/**
	 * 新增打卡记录
	 * @param ucid
	 * @param uid
	 * @param username
	 * @param idcard
	 * @param signstatus
	 * @param signout
	 * @param istime
	 * @return 数据库受影响行数
	 */
	Integer addUsersCheck(String ucid, String uid, String username, String uphone, String signstatus, String signout,
			String istime);
	/**
	 * 根据一组id查询一组实体 
	 * @param ucid
	 * @return List<Userscheck>
	 */
	List<Userscheck> findAllByUcid(List<String> ucid);
	
	/**
	 * 根据打卡记录id查询打卡记录
	 * @param id
	 * @return
	 */
	Userscheck getOneUsersCheck(String id);

}
