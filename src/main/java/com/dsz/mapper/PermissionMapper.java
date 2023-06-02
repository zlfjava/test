package com.dsz.mapper;

import java.util.List;

import com.dsz.entity.Permission;
import com.dsz.util.PermissionTreeTable;

/**
 * 权限持久层
 */
public interface PermissionMapper {

	/**
	 * 获取所有权限
	 * @return
	 */
	List<Permission> getAllPermission();

	/**
	 * 根据角色id获取该角色拥有的操作权限  用于勾选已拥有的权限
	 * @param rid
	 * @return
	 */
	List<Integer> getByRoleid(Integer rid);

	/**
	 * 根据角色id删除该角色的所有权限
	 * @param rid
	 * @return
	 */
	int delByRid(Integer rid);

	/**
	 * 给角色-权限表添加数据
	 * @param rid
	 * @param pid
	 * @return
	 */
	int addRolesPermission(Integer rid, int pid);

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
	 *新增子模块
	 * @param permission
	 * @return
	 */
	int add(Permission permission);

	/**
	 * 拿到模块id
	 * @param id
	 * @return
	 */
	Permission getPermissionmodule(int id);

	/**
	 * 删除前先查查当前数据下有没有子模块
	 * @param id
	 * @return
	 */
	int delModulesNum(Integer id);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Integer del(Integer id);

	/**
	 * 修改子模块
	 * @param permission
	 * @return
	 */
	Integer edit(Permission permission);

	/**
	 * treetable  获取所有
	 * @return
	 */
	List<PermissionTreeTable> findAllByCaoZuo();

	/**
	 * 根据角色id获取该角色拥有的模块
	 * @param rid
	 * @return
	 */
	List<Permission> getModilesByRid(Integer rid);

}
