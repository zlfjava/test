package com.dsz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsz.entity.Roles;
import com.dsz.mapper.RolesMapper;
import com.dsz.service.RolesService;
import com.dsz.util.FenyeUtil;

/**
 * 角色业务实现层
 */
@Service
@Transactional
public class RolesServiceImpl implements RolesService {
	
	//注入角色持久层
	@Autowired
	private RolesMapper rolMapper;
	
	/**
	 * 获取所有角色
	 */
	@Override
	public List<Roles> getAll() {
		return rolMapper.getAll();
	}

	/**
	 * 根据用户id查询该用户拥有哪些角色
	 */
	@Override
	public List<Roles> getUserRoles(String uid) {
		return rolMapper.getUserRoles(uid);
	}

	/**
	 * 多条件分页展示
	 */
	@Override
	public FenyeUtil<Roles> findAll(FenyeUtil<Roles> fy) {
		List<Roles> data = rolMapper.findAll(fy);	//查询所有角色数据
		Integer count = rolMapper.getCount(fy);	//查询所有角色数据的总条数
		if (data.size() > 0) {	//集合的长度大于0说明查询到了数据
			fy.setCode(0);	//设置有数据的状态码
			fy.setData(data);	//设置数据
			fy.setCount(count);	//设置数据条数
		} else {	//没查询到数据
			fy.setCode(1);	//设置无数据的状态码
			fy.setMsg("未找到有效信息");	//给出提示
		}
		return fy;	//返回Layui分页工具类
	}

	/**
	 * 添加角色
	 */
	@Override
	public FenyeUtil<Roles> addRoles(Roles roles) {
		FenyeUtil<Roles> fy = new FenyeUtil<>(); //实例化分页工具类  用来存储返回值
		//根据一个条件（角色名）查询角色（用于添加，查询角色名是否重复）
		List<Roles> rolesList = rolMapper.getOne(roles);	
		if (rolesList.size() > 0) {	//说明有重复数据
			fy.setCode(0);	//设置状态码
			fy.setMsg("角色名已存在");	//给出提示
			return fy;	//返回状态码和提示  并结束方法
		}
		int num = rolMapper.addRoles(roles);	//执行添加操作
		if (num > 0) {	//数据库受影响行数大于0说明成功了
			fy.setCode(1);	//设置状态码
			fy.setMsg("添加成功！");	//给出提示
		} else {
			fy.setCode(0);	//设置失败的状态码
			fy.setMsg("添加失败");	//给出提示
		}
		return fy;	//返回状态码和提示 
	}
	
	/**
	 * 修改角色名
	 */
	@Override
	public FenyeUtil<Roles> editRoles(Roles roles) {
		FenyeUtil<Roles> fy = new FenyeUtil<>();	//实例化分页工具类  用来存储返回值
		//根据一个条件（角色名和角色id）查询角色（用于修改，查询角色名是否重复）
		List<Roles> rolesList = rolMapper.getOne(roles);	
		if (rolesList.size() > 0) {	//说明有重复数据
			fy.setCode(0);	//设置状态码
			fy.setMsg("角色名已存在");	//给出提示
			return fy;	//返回状态码和提示  并结束方法
		}
		int num = rolMapper.editRoles(roles);	//执行修改操作
		if (num > 0) {	//数据库受影响行数大于0说明成功了
			fy.setCode(1);	//设置状态码
			fy.setMsg("修改成功！");	//给出提示
		} else {
			fy.setCode(0);	//设置失败的状态码
			fy.setMsg("修改失败");		//给出提示
		}
		return fy;	//返回状态码和提示 
	}

	/**
	 * 删除   同时实现批量删除和单个删除
	 */
	@Override
	public Map<String, Object> deleteRid(List<Integer> rids) {
		Map<String, Object> map = new HashMap<>();	//存储返回值
		int checkList = rolMapper.checkListByIds(rids);	//删除角色前查查有没有相关的权限
		if (checkList > 0) {	//如果返回值大于0  说明当前删除的角色有权限
			map.put("code", 0);	//设置状态码
			map.put("msg", "所选角色有权限，请取消权限后再删除");	//给出不能删除的提示
			return map;	//返回参数并结束方法
		}
		int num = rolMapper.deleteRid(rids);	//执行删除操作
		if (num > 0) {	//数据库受影响行数大于0说明成功了
			map.put("code", 1);	//设置状态码
			map.put("msg", "删除成功！");	//给出提示
		} else {
			map.put("code", 0);	//设置失败的状态码
			map.put("msg", "删除失败！");	//给出提示
		}
		return map;	//返回状态码和提示 
	}	

}
