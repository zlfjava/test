package com.dsz.service;

import java.util.List;
import java.util.Map;

import com.dsz.entity.Roles;
import com.dsz.util.FenyeUtil;

/**
 * 角色业务层
 * @author 宋占成
 *
 */
public interface RolesService {

	/**
	 * 获取所有角色
	 * @return
	 */
	List<Roles> getAll();

	/**
	 * 根据用户id查询该用户拥有哪些角色
	 * @param uid
	 * @return
	 */
	List<Roles> getUserRoles(String uid);

	/**
	 * 多条件分页展示
	 * @param fy
	 * @return
	 */
	FenyeUtil<Roles> findAll(FenyeUtil<Roles> fy);

	/**
	 * 添加角色
	 * @param roles
	 * @return
	 */
	FenyeUtil<Roles> addRoles(Roles roles);

	/**
	 * 修改角色名
	 * @param roles
	 * @return
	 */
	FenyeUtil<Roles> editRoles(Roles roles);

	/**
	 * 删除   同时实现批量删除和单个删除
	 * @param rids
	 * @return
	 */
	Map<String, Object> deleteRid(List<Integer> rids);

}
