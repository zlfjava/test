package com.dsz.util;

/**
 *模块treeTable返回值工具类
 */
public class Modulescs {
	private Integer id;	//模块编号，主键，自增
	private String title;	//模块名称
	private Integer parentid;	//父模块编号
	private String mpath;	//模块路径
	private Integer mweight;	//模块权重
	private String styleicon;	//图标
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	public String getMpath() {
		return mpath;
	}
	public void setMpath(String mpath) {
		this.mpath = mpath;
	}
	public Integer getMweight() {
		return mweight;
	}
	public void setMweight(Integer mweight) {
		this.mweight = mweight;
	}
	public String getStyleicon() {
		return styleicon;
	}
	public void setStyleicon(String styleicon) {
		this.styleicon = styleicon;
	}
	
	

}
