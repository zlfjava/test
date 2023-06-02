package com.dsz.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色实体类
 */
@NoArgsConstructor	//生成无参构造
@AllArgsConstructor	//生成全参构造
@Getter	//生成getter方法
@Setter	//生成setter方法
@ToString	//生成toString方法
public class Roles {
	
	private Integer rid;	//角色编号，主键，自增
	private String rname;	//角色名

}
