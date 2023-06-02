package com.dsz.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 学生实体类
 */
@NoArgsConstructor	//生成无参构造
@AllArgsConstructor	//生成全参构造
@Getter	//生成getter方法
@Setter	//生成setter方法
@ToString	//生成toString方法
public class Students {
	
	private String sid;	//UUID生成机制，主键
	private String uid;	//外键，引用员工id（网络咨询师员工id）
	private String szxsid;	//外键，引用员工id（咨询师员工id）
	private String uphone;	//咨询师手机号
	private String sname;	//客户名称
	private String sphone;	//客户手机号
	private Integer sage;	//客户年龄
	private String sgender;	//客户性别
	private String sourcechannel;	//来源渠道：通过百度|360搜索...推荐来的
	private String sourceword;	//来源关键词
	private String seducation;	//客户学历
	private String sqq;	//客户QQ号
	private String sstatus;	//客户状态：在读|待业|在职...
	private String wechatid;	//微信号
	private String sourceurl;	//来源网址：职英A站、B站...
	private String ifweight;	//是否自动分量
	private String screatetime;	//创建时间
	private String sremark;	//备注：网络咨询人员给的备注
	private String gradecase;	//打分情况：什么时候可以报名
	private String course;	//课程方向：想报考学习的课程
	private String iseffect;	//是否有效：是|否
	private String invalidcause;	//无效原因
	private String isfirstvisit;	//是否已经首次回访：是|否
	private String firstvisittime;	//首次回访时间
	private String isvisit;	//是否上门：是|否
	private String visittime;	//上门时间
	private BigDecimal handselmoney;	//定金金额
	private String handsetime;	//定金时间
	private String paythefees;	//是否完成缴费
	private String paythefeestime;	//完成缴费时间
	private String paymentamount;	//缴费金额
	private String isoutpay;	//是否退费
	private String isoutpaycause;	//退费原因
	private String isoutpaytime;	//退费时间
	private String intheclass;	//是否进班
	private String intheclasstime;	//进班时间
	private String intheclassremark;	//进班备注
	private String rremark;	//咨询师给的备注
	private String ifvalid;	//是否是有效数据  如果有效就显示 无效就在回收站
	
	private Users user;	//用户实体类  在查学生的同时通过MyBatis的结果集映射带出所属员工信息
	
	private Follows follows; //跟踪日志实体类  在查学生的同时映射出该学生的跟踪日志

}
