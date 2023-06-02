package com.dsz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsz.entity.Permission;
import com.dsz.mapper.PermissionMapper;
import com.dsz.service.PermissionService;
import com.dsz.util.PermissionTreeTable;

/**
 * 权限业务实现层
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
	
	//注入权限持久层
	@Autowired
	private PermissionMapper perMapper;
	
	
	/**
	 * 获取所有权限
	 * @return
	 */
	public List<Permission> getTreeNodes(Integer rid) {
		//根据rid来查询该角色所拥有的模块
		List<Permission> list = perMapper.getModilesByRid(rid);
		for (Permission permission : list) {
			System.out.println("权限：" + permission);
		}
		return list;
	}
	
	/**
	 * 查找父模块下的子模块
	 * @param treeNode
	 * @param treeList
	 * @return
	 */
	public Permission findChildren(Permission treeNode, List<Permission> treeList) {
		for (Permission item : treeList) {	//遍历集合
			if (treeNode.getId() == item.getParentid()) {
				if (treeNode.getChildren() == null) {
					treeNode.setChildren(new ArrayList<Permission>());
				}
				treeNode.getChildren().add(findChildren(item, treeList));
			}
		}
		return treeNode;
	}

	/**
	 * 获取所有模块     返回的是layui树结构需要的数据
	 */
	@Override
	public List<Permission> findAll(Integer rid) {
		List<Permission> treeList = this.getTreeNodes(rid);
		List<Permission> treeNodes = new ArrayList<Permission>();
		for (Permission treeNode : treeList) {
			if (treeNode.getParentid() == 0) {
				treeNodes.add(findChildren(treeNode, treeList));
			}
		}
		if (treeNodes.size() > 0) {
			return treeNodes;
		}
		return null;
	}

	/**
	 * 根据角色id获取该角色拥有的操作权限  用于勾选已拥有的权限
	 */
	@Override
	public List<Integer> getByRoleid(Integer rid) {
		return perMapper.getByRoleid(rid);
	}

	/**
	 * 给角色新增  或  移除权限
	 */
	@Override
	public Map<String, Object> deleteRidRoleid(Integer rid, List<Integer> roleid) {
		Map<String, Object> map = new HashMap<String, Object>();
		int delNum = perMapper.delByRid(rid);	//根据角色id删除该角色的所有权限
		int addNum = 0;
		for (int i = 0; i < roleid.size(); i++) {	//遍历选中的权限数据
			int pid = roleid.get(i);	//取出id (添加时需要的参数)
			addNum = perMapper.addRolesPermission(rid, pid);	//执行添加操作
		}	
		if (delNum > 0 || addNum > 0) {	//判断是否执行成功
			map.put("code", 1);
			map.put("msg", "设置成功！");
		} else {
			map.put("code", 0);
			map.put("msg", "设置失败");
		}
		return map;
	}

	/**
	 * 根据用户id获取该用户所拥有的权限
	 */
	@Override
	public List<Permission> checkQuanXian(String uid) {
		return perMapper.checkQuanXian(uid);
	}
	
	/**
	 * 查询所有父模块
	 */
	@Override
	public List<Permission> getAllF() {
		return perMapper.getAllF();
	}


	/**
	 * 新增子模块
	 */
	@Override
	public Map<String, Object> add(Permission permission) {
		Map<String, Object> map = new HashMap<String, Object>();
		int id = permission.getParentid();
		//根据当前模块id 查询
		Permission permissiId = perMapper.getPermissionmodule(id);
		//拿到模块所属的id
		int permissionmodule = permissiId.getPermissionmodule();
		permission.setPermissionmodule(permissionmodule);
		int delNum = perMapper.add(permission);	//根据角色id删除该角色的所有权限
		if (delNum > 0) {	//判断是否执行成功
			map.put("code", 1);
			map.put("msg", "添加成功！");
			return map;
		} else {
			map.put("code", 0);
			map.put("msg", "添加失败");
			return map;
		}
	}

	/**
	 * 删除权限
	 */
	@Override
	public Map<String, Object> del(Integer id) {
		Map<String, Object> map = new HashMap<>();
		//删除前先查查当前数据下有没有子模块
		int delModulesNum = perMapper.delModulesNum(id);
		if (delModulesNum != 0) {
			map.put("code", 0);
			map.put("msg", "当前节点下有[" + delModulesNum + "]个子节点，您必须删除全部子节点才可以删除当前节点");
			return map;
		}
		Integer moduSave = perMapper.del(id);
		if (moduSave > 0) {
			map.put("code", 1);
			map.put("msg", "删除成功");
			return map;
		} else {
			map.put("code", 0);
			map.put("msg", "删除失败");
			return map;
		}
	}


	/**
	 * 修改子模块
	 */
	@Override
	public Map<String, Object> edit(Permission permission) {
		Map<String, Object> map = new HashMap<>();
		Integer moduSave = perMapper.edit(permission);
		if (moduSave > 0) {
			map.put("code", 1);
			map.put("msg", "修改成功");
			return map;
		} else {
			map.put("code", 0);
			map.put("msg", "修改失败");
			return map;
		}
	}
	
	/**
	 *  treetable  获取所有
	 */
	@Override
	public List<PermissionTreeTable> findAllByCaoZuo() {
		return perMapper.findAllByCaoZuo();
	}

}
