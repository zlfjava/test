package com.dsz.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 动态消息推送实体类
 */
@NoArgsConstructor	//生成无参构造
@AllArgsConstructor	//生成全参构造
@Getter	//生成getter方法
@Setter	//生成setter方法
@ToString	//生成toString方法
public class Dynamicmessage {
	
	private String did;	//UUID生成机制，主键
	private String sid;	//外键，引用客户id
	private String sname;	//客户名
	private String senderid;	//发送信息的员工的id
	private String uid;	//管理学生的咨询师的id
	private String dcontent;	//消息内容
	private String isread;	//是否已读消息
	private String dcreatetime;	//消息发送时间
	
	private Users user;	//用户实体类，为了获得发送人的姓名
	
	public Dynamicmessage(String did, String sid, String sname, String senderid, String uid, String dcontent,
			String isread, String dcreatetime) {
		super();
		this.did = did;
		this.sid = sid;
		this.sname = sname;
		this.senderid = senderid;
		this.uid = uid;
		this.dcontent = dcontent;
		this.isread = isread;
		this.dcreatetime = dcreatetime;
	}
	
}
