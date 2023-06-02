package com.dsz.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 权限实体类
 */
@NoArgsConstructor	//生成无参构造
@AllArgsConstructor	//生成全参构造
@Getter	//生成getter方法
@Setter	//生成setter方法
@ToString	//生成toString方法
public class Permission {
	
	private Integer id;	//功能权限编号，主键，自增
	private String title;	//权限名称
	private Integer parentid;	//父模块编号
	private String permissionvalue;	//权限值
	private Integer permissionmodule;	//模块id
	private String permissionname;	//权限名称（说明）
	private List<Permission> children;	//存储子模块
	
}
