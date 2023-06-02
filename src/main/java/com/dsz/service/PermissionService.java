package com.dsz.service;

import java.util.List;
import java.util.Map;

import com.dsz.entity.Permission;
import com.dsz.util.PermissionTreeTable;

/**
 * 权限业务层
 */
public interface PermissionService {

	/**
	 * 树结构  获取所有
	 * @return
	 */
	List<Permission> findAll(Integer rid);

	/**
	 * 根据角色id获取该角色拥有的操作权限  用于勾选已拥有的权限
	 * @param rid
	 * @return
	 */
	List<Integer> getByRoleid(Integer rid);

	/**
	 * 给角色新增  或  移除权限
	 * @param rid
	 * @param roleid
	 * @return
	 */
	Map<String, Object> deleteRidRoleid(Integer rid, List<Integer> roleid);

	/**
	 * 根据用户id获取该用户所拥有的权限
	 * @param uid
	 * @return
	 */
	List<Permission> checkQuanXian(String uid);
	
	/**
	 * 查询所有父模块
	 * @return
	 */
	List<Permission> getAllF();

	/**
	 * 新增子模块
	 * @param permission
	 * @return
	 */
	Map<String, Object> add(Permission permission);

	/**
	 * 删除模块
	 * @param id
	 * @return
	 */
	Map<String, Object> del(Integer id);

	/**
	 * 修改子模块
	 * @param permission
	 * @return
	 */
	Map<String, Object> edit(Permission permission);

	/**
	 * treetable  获取所有
	 * @return
	 */
	List<PermissionTreeTable> findAllByCaoZuo();

}
