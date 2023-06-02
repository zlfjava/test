package com.dsz.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户打卡实体类
 */
@NoArgsConstructor	//生成无参构造
@AllArgsConstructor	//生成全参构造
@Getter	//生成getter方法
@Setter	//生成setter方法
@ToString	//生成toString方法
public class Userscheck {
	
	private String ucid;	//UUID生成机制，主键
	private String uid;	//外键，引用用户id
	private String username;	//员工名
	private String uphone;	//员工手机号
	private String signstatus;	//是否打卡  是|否
	private String signintime;	//打卡时间
	private String signout;	//是否签退
	private String signouttime;	//签退时间
	private String istime;	//打卡日期
	
	public Userscheck(String uid, String username, String signout, String signouttime, String istime) {
		super();
		this.uid = uid;
		this.username = username;
		this.signout = signout;
		this.signouttime = signouttime;
		this.istime = istime;
	}

	public Userscheck(String ucid, String uid, String username, String signstatus, String signintime, String signout,
			String signouttime, String istime) {
		super();
		this.ucid = ucid;
		this.uid = uid;
		this.username = username;
		this.signstatus = signstatus;
		this.signintime = signintime;
		this.signout = signout;
		this.signouttime = signouttime;
		this.istime = istime;
	}
	
}
