package com.dsz.util;

/**
 * 权限treeTable返回值工具类
 */
public class PermissionTreeTable {
	
	private Integer id;	//功能编号，主键，自增
	private String title;	//功能名称
	private Integer parentid;	//父模块编号
	private String permissionvalue;	//权限值
	private Integer permissionmodule; //模块id
	private Integer permissionname; //权限名称（说明）
	
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
	public String getPermissionvalue() {
		return permissionvalue;
	}
	public void setPermissionvalue(String permissionvalue) {
		this.permissionvalue = permissionvalue;
	}
	public Integer getPermissionmodule() {
		return permissionmodule;
	}
	public void setPermissionmodule(Integer permissionmodule) {
		this.permissionmodule = permissionmodule;
	}
	public Integer getPermissionname() {
		return permissionname;
	}
	public void setPermissionname(Integer permissionname) {
		this.permissionname = permissionname;
	}
	
	

}
