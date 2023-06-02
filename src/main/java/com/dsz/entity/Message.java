package com.dsz.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 聊天消息实体类
 */
@NoArgsConstructor	//生成无参构造
@AllArgsConstructor	//生成全参构造
@Getter	//生成getter方法
@Setter	//生成setter方法
@ToString	//生成toString方法
public class Message {
	
	private String meid;	//UUID生成机制，主键
	private String senderid;	//发送人id
	private String sendername;	//发送人姓名
	private String recipientid;	//接收人id
	private String recipientname;	//接收人姓名
	private String rname;	//角色名：当前
	private String mecontent;	//聊天内容
	private String mecreatetime;	//消息发送时间
	private String isread;	//是否读到消息

}
