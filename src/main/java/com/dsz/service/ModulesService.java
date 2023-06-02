package com.dsz.service;

import java.util.List;
import java.util.Map;

import com.dsz.entity.Modules;
import com.dsz.util.Modulescs;

/**
 * 模块业务层
 */
public interface ModulesService {

	/**
	 * 根据用户id获取模块
	 * @param uid
	 * @return
	 */
	List<Modules> getModulesByUid(String uid);

	/**
	 * 获取所有模块，并以树结构展示
	 * @return
	 */
	List<Modules> getAll();

	/**
	 * 根据角色id 查询当前角色拥有哪些模块
	 * @param rid
	 * @return
	 */
	List<Integer> getByRid(Integer rid);

	/**
	 * 给用户添加 或 移除访问模块
	 * @param rid
	 * @param mid
	 * @return
	 */
	Map<String, Object> deleteRidAndInsert(Integer rid, List<Integer> mid);

	/**
	 * 新增父模块
	 * @param modules
	 * @return
	 */
	Map<String, Object> addfjd(Modules modules);

	/**
	 * 新增子模块
	 * @param modules
	 * @return
	 */
	Map<String, Object> add(Modules modules);
	
	/**
	 * 查询所有模块
	 * @return
	 */
	List<Modulescs> getAllAll();

	/**
	 * 查询所有父模块
	 * @return
	 */
	List<Modules> getAllF();

	/**
	 * 删除模块
	 * @param id
	 * @return
	 */
	Map<String, Object> del(Integer id);

	/**
	 * 修改模块
	 * @param modules
	 * @return
	 */
	Map<String, Object> edit(Modules modules);

}
