package com.dsz.mapper;

import java.util.List;

import com.dsz.entity.Roles;
import com.dsz.util.FenyeUtil;

/**
 * 角色持久层
 */
public interface RolesMapper {

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
	 * 获取所有数据
	 * @param fy
	 * @return
	 */
	List<Roles> findAll(FenyeUtil<Roles> fy);

	/**
	 * 获取数据总条数
	 * @param fy
	 * @return
	 */
	Integer getCount(FenyeUtil<Roles> fy);

	/**
	 * 添加角色
	 * @param roles
	 * @return
	 */
	int addRoles(Roles roles);

	/**
	 * 修改角色名
	 * @param roles
	 * @return
	 */
	int editRoles(Roles roles);

	/**
	 * 删除   同时实现批量删除和单个删除
	 * @param rids
	 * @return
	 */
	Integer deleteRid(List<Integer> rids);

	/**
	 * 根据一个条件（角色名）查询角色（用于添加，查询角色名是否重复）
	 * @param roles
	 * @return
	 */
	List<Roles> getOne(Roles roles);

	/**
	 * 删除角色前查查有没有相关的权限
	 * @param rids
	 * @return
	 */
	int checkListByIds(List<Integer> rids);

}
