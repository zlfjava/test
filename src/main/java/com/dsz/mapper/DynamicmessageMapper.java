package com.dsz.mapper;

import java.util.List;

import com.dsz.entity.Dynamicmessage;

/**
 * 动态消息持久层
 */
public interface DynamicmessageMapper {

	/**
	 * 新增动态消息
	 * @param dymMsg
	 * @return
	 */
	Integer add(Dynamicmessage dymMsg);

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
