package com.dsz.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 模块实体类
 */
@NoArgsConstructor	//生成无参构造
@AllArgsConstructor	//生成全参构造
@Getter	//生成getter方法
@Setter	//生成setter方法
@ToString	//生成toString方法
public class Modules {
	
	private Integer id;	//模块编号，主键，自增
	private String title;	//模块名称
	private Integer parentid;	//父模块编号
	private String mpath;	//模块路径
	private Integer mweight;	//模块权重
	private String styleicon;	//
	private List<Modules> children;	//存储子模块
}
