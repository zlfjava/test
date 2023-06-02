package com.dsz.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dsz.entity.Modules;
import com.dsz.service.ModulesService;
import com.dsz.util.Modulescs;
import com.dsz.util.TreeTableModel;

/**
 * 模块控制器
 */
@RestController	//相当于@Controller + @ResponseBody的作用
@CrossOrigin	//解决跨域数据传输问题
public class ModulesController {
	
	//注入模块业务层
	@Autowired
	private ModulesService moService;
	
	/**
	 * 根据用户id获取模块
	 * @return
	 */
	@RequestMapping(value = "/modules/getModulesByUid")
	public List<Modules> getModulesByUid(String uid) {
		return moService.getModulesByUid(uid);
	}
	
	/**
	 * 获取所有模块，并以树结构展示
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/modules/getAll")
	public List<Modules> getAll() {
		return moService.getAll();
	}
	
	/**
	 * 根据角色id 查询当前角色拥有哪些模块
	 * @param rid
	 * @return
	 */
	@RequestMapping(value = "/modules/getByRid")
	public List<Integer> getByRid(Integer rid) {
		return moService.getByRid(rid);
	}
	
	/**
	 * 给用户添加 或 移除访问模块
	 * @param rid
	 * @param mid
	 * @return
	 */
	@RequestMapping(value = "/modules/deleteRidAndInsert")
	public Map<String, Object> deleteRidAndInsert(Integer rid, @RequestParam("mid")List<Integer> mid) {
		return moService.deleteRidAndInsert(rid, mid);
	}
	
	/**
	 * 新增父模块
	 * @param modules
	 * @return
	 */
	@RequestMapping(value = "/modules/addfjd")
	public Map<String, Object> addfjd(Modules modules) {
		return moService.addfjd(modules);
	}
	
	/**
	 * 新增子模块
	 * @param modules
	 * @return
	 */
	@RequestMapping(value = "/modules/add")
	public Map<String, Object> add(Modules modules) {
		return moService.add(modules);
	}
	
	/**
	 * 查询所有模块
	 * @return
	 */
	@RequestMapping("/modules/getAllAll")
	public TreeTableModel getAllAll() {
		List<Modulescs> list = moService.getAllAll();
		return new TreeTableModel(list);
	}
	
	/**
	 * 查询所有父模块
	 * @return
	 */
	@RequestMapping("/modules/getAllF")
	public List<Modules> getAllF() {
		return moService.getAllF();
	}
	
	/**
	 * 删除模块
	 * @param id
	 * @return
	 */
	@RequestMapping("/modules/del")
	public Map<String, Object> del(Integer id) {
		return moService.del(id);
	}
	
	/**
	 * 修改模块
	 * @param modules
	 * @return
	 */
	@RequestMapping(value = "/modules/edit")
	public Map<String, Object> edit(Modules modules) {
		return moService.edit(modules);
	}

}
