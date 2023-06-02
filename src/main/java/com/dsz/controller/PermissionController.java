package com.dsz.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dsz.entity.Permission;
import com.dsz.service.PermissionService;
import com.dsz.util.PermissionTreeTable;
import com.dsz.util.TreeTableModel;

/**
 * 权限管理控制器
 */
@RestController	//相当于@Controller + @ResponseBody的作用
@CrossOrigin	//解决跨域数据传输问题
public class PermissionController {
	
	//注入权限业务层
	@Autowired
	private PermissionService perService;
	
	/**
	 * 树结构  获取所有
	 * @return
	 */
	@RequestMapping(value = "/permission/findAll")
	public List<Permission> findAll(Integer rid) {
		return perService.findAll(rid);
	}
	
	/**
	 * 根据角色id获取该角色拥有的操作权限  用于勾选已拥有的权限
	 * @param rid
	 * @return
	 */
	@RequestMapping(value = "/permission/getByRoleid")
	public List<Integer> getByRoleid(Integer rid) {
		return perService.getByRoleid(rid);
	}
	
	/**
	 * 给角色新增  或  移除权限
	 * @param rid
	 * @param roleid
	 * @return
	 */
	@RequestMapping(value = "/permission/deleteRidRoleid")
	public Map<String, Object> deleteRidRoleid(Integer rid, @RequestParam("roleid")List<Integer> roleid) {
		return perService.deleteRidRoleid(rid, roleid);
	}
	
	/**
	 * 根据用户id获取该用户所拥有的权限
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/permission/checkQuanXian")
	public List<Permission> checkQuanXian(String uid) {
		return perService.checkQuanXian(uid);
	}
	
	
	/**
	 * treetable  获取所有
	 * @return
	 */
	@RequestMapping(value = "/permission/findAllByCaoZuo")
	public TreeTableModel findAllByCaoZuo() {
		List<PermissionTreeTable>  list = perService.findAllByCaoZuo();
		return new TreeTableModel(list);
	}
	
	/**
	 * 查询所有父模块
	 * @return
	 */
	@RequestMapping(value = "/permission/getAllF")
	public List<Permission> getAllF() {
		return perService.getAllF();
	}
	
	/**
	 * 新增子模块
	 * @param permission
	 * @return
	 */
    @RequestMapping(value = "/permission/add")
    public Map<String, Object> add(Permission permission) {
		return perService.add(permission);
    }
    
    /**
     * 删除模块
     * @param permission
     * @return
     */
    @RequestMapping(value = "/permission/del")
    public Map<String, Object> del(Integer id) {
		return perService.del(id);
    }
    
    /**
     * 修改子模块
     * @param permission
     * @return
     */
    @RequestMapping(value = "/permission/edit")
    public Map<String, Object> edit(Permission permission) {
		return perService.edit(permission);
    }

}
