package com.dsz.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 回访实体类
 */
@NoArgsConstructor	//生成无参构造
@AllArgsConstructor	//生成全参构造
@Getter	//生成getter方法
@Setter	//生成setter方法
@ToString	//生成toString方法
public class Follows {
	
	private String fid;	//UUID生成机制，主键
	private String sid;	//外键，引用客户id
	private String uid;	//员工id(访问者id)
	private String visittime;	//回访时间
	private String visitcase;	//回访情况（沟通内容）
	private String trackingmode;	//跟踪方式(访问方式)
	private String nexttrackingtime;	//下次跟踪时间
	private String fremark;	//备注
	private String stustatus;	//状态：学生状态
	
	private Students student;	//学生实体类
	
	private Users user;	//用户数据
	
}
