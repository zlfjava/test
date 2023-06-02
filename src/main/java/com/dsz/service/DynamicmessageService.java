package com.dsz.service;

import java.util.List;
import java.util.Map;

import com.dsz.entity.Dynamicmessage;
import com.dsz.entity.Students;

/**
 *动态消息业务层
 */
public interface DynamicmessageService {

	/**
	 * 新增动态消息
	 * @param stu 用实体类存储参数
	 * @param dcontent	消息内容
	 * @return	新增成功|失败的状态码以及提示消息
	 */
	Map<String, Object> add(Students stu, String dcontent);

	/**
	 * 根据用户id（拥有咨询师角色的用户）获取他的未读消息数量
	 * @param uid 咨询师用户的id
	 * @return 未读消息数量
	 */
	int getCountByUid(String uid);

	/**
	 * 根据用户id（拥有咨询师角色的用户）获取他的未读消息
	 * @param uid 咨询师用户的id
	 * @return 消息数量
	 */
	List<Dynamicmessage> getMsgByUid(String uid, String isread);

	/**
	 * 根据消息id修改消息（把消息修改为已读）
	 * @param did 当前数据的id
	 * @return 数据库受影响行数
	 */
	Integer edit(String did, String isread);
	
}
