package com.dsz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsz.entity.Dynamicmessage;
import com.dsz.entity.Students;
import com.dsz.entity.Users;
import com.dsz.mapper.DynamicmessageMapper;
import com.dsz.mapper.UsersMapper;
import com.dsz.service.DynamicmessageService;
import com.dsz.util.GetDateUtil;

/**
 * 动态消息业务实现层
 */
@Service
@Transactional
public class DynamicmessageServiceImpl implements DynamicmessageService {
	
	//注入动态消息持久层
	@Autowired
	private DynamicmessageMapper dymMapper;
	
	//注入用户持久层
	@Autowired
	private UsersMapper userMapper;

	/**
	 * 新增动态消息
	 */
	@Override
	public Map<String, Object> add(Students stu, String dcontent) {
		Map<String, Object> map = new HashMap<>();	//存储返回值
		GetDateUtil date = new GetDateUtil(); // 实例化日期工具类
		String did = UUID.randomUUID().toString(); // 手动生成uuid
		String sid = stu.getSid();	//获取学生id
		String sname = stu.getSname();	//获取学生姓名
		System.out.println("学生姓名=" + sname);
		String senderid = stu.getUid();	//获取消息发送人id
		String uphone = stu.getUphone();	//获取咨询师手机号
		Users user = userMapper.Protectmtel(uphone);	//根据手机号查询咨询师
		String uid = user.getUid();	//获取咨询师id
		//添加的参数
		Dynamicmessage dymMsg = new Dynamicmessage(did, sid, sname, senderid, uid, dcontent, "未读", date.getTime());
		int num = dymMapper.add(dymMsg);	//添加操作
		if (num > 0) {
			map.put("code", num);
			map.put("msg", "推送成功");
		} else {
			map.put("code", num);
			map.put("msg", "推送失败");
		}
		return map;
	}

	/**
	 * 根据用户id（拥有咨询师角色的用户）获取他的未读消息数量
	 */
	@Override
	public int getCountByUid(String uid) {
		return dymMapper.getCountByUid(uid);
	}

	/**
	 * 根据用户id（拥有咨询师角色的用户）获取他的未读消息
	 */
	@Override
	public List<Dynamicmessage> getMsgByUid(String uid, String isread) {
		return dymMapper.getMsgByUid(uid, isread);
	}

	/**
	 * 根据消息id修改消息（把消息修改为已读）
	 */
	@Override
	public Integer edit(String did, String isread) {
		return dymMapper.edit(did, isread);
	}

}
