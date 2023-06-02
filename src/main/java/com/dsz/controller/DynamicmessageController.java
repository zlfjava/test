package com.dsz.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dsz.entity.Dynamicmessage;
import com.dsz.entity.Students;
import com.dsz.service.DynamicmessageService;

/**
 * 动态消息控制器
 */
@RestController	//相当于@Controller + @ResponseBody的作用
@CrossOrigin	//解决跨域数据传输问题
public class DynamicmessageController {
	
	//注入动态消息业务层
	@Autowired
	private DynamicmessageService dymService;
	
	/**
	 * 新增动态消息
	 * @param stu 用实体类存储参数
	 * @param dcontent	消息内容
	 * @return	新增成功|失败的状态码以及提示消息
	 */
	@RequestMapping(value = "/dynamicmessage/add", method = RequestMethod.POST)
	public Map<String, Object> add(Students stu, String dcontent) {
		return dymService.add(stu, dcontent);
	}
	
	/**
	 * 根据用户id（拥有咨询师角色的用户）获取他的未读消息数量
	 * @param uid 咨询师用户的id
	 * @return 未读消息数量
	 */
	@RequestMapping(value = "/dynamicmessage/getCountByUid", method = RequestMethod.POST)
	public int getCountByUid(String uid) {
		return dymService.getCountByUid(uid);
	}
	
	/**
	 * 根据用户id（拥有咨询师角色的用户）获取他的未读消息
	 * @param uid 咨询师用户的id
	 * @return 消息数量
	 */
	@RequestMapping(value = "/dynamicmessage/getMsgByUid", method = RequestMethod.POST)
	public List<Dynamicmessage> getMsgByUid(String uid, String isread) {
		return dymService.getMsgByUid(uid, isread);
	}
	
	/**
	 * 根据消息id修改消息（把消息修改为已读）
	 * @param did 当前数据的id
	 * @return 数据库受影响行数
	 */
	@RequestMapping(value = "/dynamicmessage/edit", method = RequestMethod.POST)
	public Integer edit(String did, String isread) {
		return dymService.edit(did, isread);
	}

}
