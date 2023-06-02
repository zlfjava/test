package com.dsz.mapper;

import java.util.List;

import com.dsz.entity.Modules;
import com.dsz.util.Modulescs;
/**
 *模块持久层
 */
public interface ModulesMapper {

	/**
	 * 根据用户id获取模块
	 * @param uid
	 * @return
	 */
	List<Modules> getModulesByUid(String uid);

	/**
	 *  获取所有模块
	 * @return
	 */
	List<Modules> getAllModules();

	/**
	 * 根据角色id 查询当前角色拥有哪些模块
	 * @param rid
	 * @return
	 */
	List<Integer> getByRid(Integer rid);

	/**
	 * 根据角色id删除当前角色拥有的全部模块
	 * @param rid
	 * @return
	 */
	int deleteRid(Integer rid);

	/**
	 * 向角色和模块的中间表里添加数据
	 * @param rid
	 * @param mid
	 * @return
	 */
	int insertRM(Integer rid, Integer mid);

	/**
	 * 查询该模块名是否存在
	 * @param mname
	 * @return
	 */
	Modules findByMname(String title);

	/**
	 * 新增父模块
	 * @param modules
	 * @return
	 */
	Integer addF(Modules modules);

	/**
	 * 查询所有模块
	 * @return
	 */
	List<Modulescs> getAllAll();

	/**
	 * 查询所有夫模块
	 * @return
	 */
	List<Modules> getAllF();

	/**
	 * 新增子模块
	 * @param modules
	 * @return
	 */
	Integer add(Modules modules);

	/**
	 * 删除模块
	 * @param id
	 * @return
	 */
	Integer del(Integer id);

	/**
	 * 修改模块
	 * @param modules
	 * @return
	 */
	Integer edit(Modules modules);

	/**
	 * 删除前先查查当前数据下有没有子模块
	 * @param id
	 * @return
	 */
	int delModulesNum(Integer id);

	/**
	 * 删除子模块前先查查有没有关联角色信息
	 * @param id
	 * @return
	 */
	int checkRolesModu(Integer id);

	/**
	 * 查找父模块下有没有此节点
	 * @param id
	 * @param title
	 * @return
	 */
	int findByMnamechildren(Integer id, String title);

}
