package com.dsz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsz.entity.Modules;
import com.dsz.mapper.ModulesMapper;
import com.dsz.service.ModulesService;
import com.dsz.util.Modulescs;

/**
 * 模块业务实现层
 */
@Service
@Transactional
public class ModulesServiceImpl implements ModulesService {
	
	//注入模块持久层
	@Autowired
	private ModulesMapper moMapper;
	
	/**
	 * 根据用户id获取模块
	 */
	@Override
	public List<Modules> getModulesByUid(String uid) {
		List<Modules> treeList = this.getTreeNodes(uid);
		List<Modules> treeNodes = new ArrayList<Modules>();
		for (Modules treeNode : treeList) {
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
	 * 根据用户id查询该用户拥有的模块
	 * @param loginname
	 * @return
	 */
	public List<Modules> getTreeNodes(String uid) {
		return moMapper.getModulesByUid(uid);
	}
	
	/**
	 * 查找父模块下的子模块
	 * @param treeNode
	 * @param treeList
	 * @return
	 */
	public Modules findChildren(Modules treeNode, List<Modules> treeList) {
		for (Modules item : treeList) {	//遍历集合
			if (treeNode.getId() == item.getParentid()) {
				if (treeNode.getChildren() == null) {
					treeNode.setChildren(new ArrayList<Modules>());
				}
				treeNode.getChildren().add(findChildren(item, treeList));
			}
		}
		return treeNode;
	}
	
	/**
	 * 获取所有模块
	 * @return
	 */
	public List<Modules> getTreeNodes() {
		return moMapper.getAllModules();
	}

	/**
	 * 获取所有模块，并以树结构展示
	 */
	@Override
	public List<Modules> getAll() {
		List<Modules> treeList = this.getTreeNodes();
		List<Modules> treeNodes = new ArrayList<Modules>();
		for (Modules treeNode : treeList) {
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
	 * 根据角色id 查询当前角色拥有哪些模块
	 */
	@Override
	public List<Integer> getByRid(Integer rid) {
		return moMapper.getByRid(rid);
	}

	/**
	 * 给用户添加 或 移除访问模块
	 */
	@Override
	public Map<String, Object> deleteRidAndInsert(Integer rid, List<Integer> mid) {
		Map<String, Object> map = new HashMap<>();
		int delNum = moMapper.deleteRid(rid);	//根据角色id删除当前角色拥有的全部模块
		int addNum = 0;
		for (int i = 0; i < mid.size(); i++) {
			Integer tempMid = mid.get(i);
			addNum = moMapper.insertRM(rid, tempMid);	//向角色和模块的中间表里添加数据
		}
		if (delNum > 0 || addNum > 0) {
			map.put("code", 1);
			map.put("msg", "设置成功！");
		} else {
			map.put("code", 0);
			map.put("msg", "设置失败");
		}
		return map;
	}

	/**
	 * 新增父模块
	 */
	@Override
	public Map<String, Object> addfjd(Modules modules) {
		Map<String, Object> map = new HashMap<>();
		String title = modules.getTitle();
		Modules num = moMapper.findByMname(title);
		if (num != null) {
			map.put("code", 0);
			map.put("msg", "此父节点名已存在");
			return map;
		}
		Integer parentid = 0;
		modules.setParentid(parentid);
		Integer moduSave = moMapper.addF(modules);
		if (moduSave > 0) {
			map.put("code", 1);
			map.put("msg", "增加成功");
		} else {
			map.put("code", 0);
			map.put("msg", "增加失败");
		}
		return map;
	}

	/**
	 * 新增子模块
	 */
	@Override
	public Map<String, Object> add(Modules modules) {
		Map<String, Object> map = new HashMap<>();
		Integer id = modules.getParentid();
		String title = modules.getTitle();
		int num = moMapper.findByMnamechildren(id, title);	//查找父模块下有没有此节点
		if (num > 0) {
			map.put("code", 0);
			map.put("msg", "此父模块下已有此节点");
			return map;
		}
		Integer moduSave = moMapper.add(modules);
		if (moduSave > 0) {
			map.put("code", 1);
			map.put("msg", "增加成功");
		} else {
			map.put("code", 0);
			map.put("msg", "增加失败");
		}
		return map;
	}
	
	/**
	 * 查询所有模块
	 */
	@Override
	public List<Modulescs> getAllAll() {
		return moMapper.getAllAll();
	}

	/**
	 * 查询所有父模块
	 */
	@Override
	public List<Modules> getAllF() {
		return moMapper.getAllF();
	}

	/**
	 * 删除模块
	 */
	@Override
	public Map<String, Object> del(Integer id) {
		Map<String, Object> map = new HashMap<>();
		//删除父模块前先查查当前数据下有没有子模块
		int delModulesNum = moMapper.delModulesNum(id);
		if (delModulesNum != 0) {
			map.put("code", 0);
			map.put("msg", "当前节点下有[" + delModulesNum + "]个子节点，您必须删除全部子节点才可以删除当前节点");
			return map;
		}
		//删除子模块前先查查有没有关联角色信息
		int checkRolesModu = moMapper.checkRolesModu(id);
		if (checkRolesModu > 0) {
			map.put("code", 0);
			map.put("msg", "当前模块被[" + checkRolesModu + "]个角色访问了，不能删除");
			return map;
		}
		Integer moduSave = moMapper.del(id);
		if (moduSave > 0) {
			map.put("code", 1);
			map.put("msg", "删除成功");
		} else {
			map.put("code", 0);
			map.put("msg", "删除失败");
		}
		return map;
	}

	/**
	 * 修改模块
	 */
	@Override
	public Map<String, Object> edit(Modules modules) {
		Map<String, Object> map = new HashMap<>();
		Integer moduSave = moMapper.edit(modules);
		if (moduSave > 0) {
			map.put("code", 1);
			map.put("msg", "修改成功");
		} else {
			map.put("code", 0);
			map.put("msg", "修改失败");
		}
		return map;
	}

}
