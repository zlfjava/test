package com.dsz.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户实体类
 */
@NoArgsConstructor	//生成无参构造
@AllArgsConstructor	//生成全参构造
@Getter	//生成getter方法
@Setter	//生成setter方法
@ToString	//生成toString方法
public class Users {
	
	private String uid;	//UUID生成机制，主键
	private String loginname;	//员工名（用户名），唯一
	private String password;	//密码，必填
	private String islockout;	//是否锁定
	private String lastlogintime;	//最后一次登陆时间
	private String createtime;	//账户创立时间
	private Integer psdwrongtime;	//密码错误次数
	private String locktime;	//被锁定时间
	private String protectemail;	//密保邮箱，唯一
	private String protectmtel;	//密保手机号，唯一
	private String idcard;	//身份证号，唯一
	private String issign;	//是否打卡
	private Integer uweight;	//权重：根据权重分配学生
	private String uifweight;	//是否自动分量
	
	public Users(String uid, Integer psdwrongtime) {
		super();
		this.uid = uid;
		this.psdwrongtime = psdwrongtime;
	}
	public Users(String uid, String islockout, String locktime) {
		super();
		this.uid = uid;
		this.islockout = islockout;
		this.locktime = locktime;
	}
	public Users(String uid, String lastlogintime) {
		super();
		this.uid = uid;
		this.lastlogintime = lastlogintime;
	}
	public Users(String uid, String password, String islockout, Integer psdwrongtime, String locktime) {
		super();
		this.uid = uid;
		this.password = password;
		this.islockout = islockout;
		this.psdwrongtime = psdwrongtime;
		this.locktime = locktime;
	}

}
