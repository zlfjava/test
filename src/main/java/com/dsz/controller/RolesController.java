package com.dsz.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dsz.entity.Roles;
import com.dsz.service.RolesService;
import com.dsz.util.FenyeUtil;

/**
 * 角色控制器
 */
@RestController	//相当于@Controller + @ResponseBody的作用
@CrossOrigin	//解决跨域数据传输问题
public class RolesController {

	//注入角色业务层
	@Autowired
	private RolesService rolService;
	
	/**
	 * 获取所有角色
	 * @return 角色的集合
	 */
	@RequestMapping(value = "/roles/getAll", method = RequestMethod.POST)
	public List<Roles> getAll() {
		return rolService.getAll();
	}
	
	/**
	 * 根据用户id查询该用户拥有哪些角色
	 * @param uid 员工id
	 * @return	当前员工拥有的角色的集合
	 */
	@RequestMapping(value = "/roles/getUserRoles", method = RequestMethod.POST)
	public List<Roles> getUserRoles(String uid) {
		return rolService.getUserRoles(uid);
	}
	
	/**
	 * @RequestMapping: 设置请求地址和请求方式
	 * value: 请求地址
	 * method = RequestMethod.POST: 只接收post请求
	 * 多条件分页展示
	 * @param fy	存储查询参数的实体类
	 * @param roles	接收页面请求参数的实体类
	 * @return
	 */
	@RequestMapping(value = "/roles/findAll", method = RequestMethod.POST)
	public FenyeUtil<Roles> findAll(FenyeUtil<Roles> fy, Roles roles) {
		fy.setT(roles);	//将查询参数设置到T属性里
		return rolService.findAll(fy);	//调用业务层方法
	}
	
	/**
	 * 添加角色
	 * @param roles 实体类   接收页面的请求参数
	 * @return
	 */
	@RequestMapping(value = "/roles/addRoles", method = RequestMethod.POST)
	public FenyeUtil<Roles> addRoles(Roles roles) {
		return rolService.addRoles(roles);	//调用业务层方法
	}
	
	/**
	 * 修改角色名
	 * @param roles 实体类   接收页面的请求参数
	 * @return
	 */
	@RequestMapping(value = "/roles/editRoles", method = RequestMethod.POST)
	public FenyeUtil<Roles> editRoles(Roles roles) {
		return rolService.editRoles(roles);	//调用业务层方法
	}
	
	/**
	 * 删除   同时实现批量删除和单个删除
	 * @param rids
	 * @return
	 * @RequestParam: 使用集合接收参数  因为接收的参数是一个集合，需要使用此注解才能正确接收
	 */
	@RequestMapping(value = "/roles/deleteRid", method = RequestMethod.POST)
	public Map<String, Object> deleteRid(@RequestParam("rids")List<Integer> rids) {
		return rolService.deleteRid(rids);
	}
	
}
