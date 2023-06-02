package com.dsz.util;

import java.util.List;

/**
 * treeTable返回值工具类
 */
public class TreeTableModel {
	
	private Integer code=0;	//状态码
	private String msg="ok";	//返回消息
	@SuppressWarnings("rawtypes")	//抑制警告
	private List data;	//数据
	private Integer count;	//数据条数
 
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
 
	public String getMsg() {
		return msg;
	}
 
	public void setMsg(String msg) {
		this.msg = msg;
	}
 
	public Integer getCount() {
		return count;
	}
 
	public void setCount(Integer count) {
		this.count = count;
	}
 
	@SuppressWarnings("rawtypes")
	public List getData() {
		return data;
	}
 
	public void setData(@SuppressWarnings("rawtypes") List data) {
		this.data = data;
	}
 
	public TreeTableModel() {
		super();
	}
	public TreeTableModel(Integer code, String msg, Integer count, @SuppressWarnings("rawtypes") List data) {
		super();
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}
	public TreeTableModel(@SuppressWarnings("rawtypes") List data) {
		super();
		this.count=data.size();
		this.data = data;
	}

}
