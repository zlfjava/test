package com.dsz.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.dsz.entity.Students;
import com.dsz.util.FenyeUtil;

/**
 * 学生业务层
 */
public interface StudentsService {

	/**
	 * 多条件分页查询
	 * @param fy
	 * @return
	 */
	FenyeUtil<Students> getAllstu(FenyeUtil<Students> fy);

	/**
	 * 查询咨询师
	 * @param uid
	 * @return
	 */
	List<Students> getUsername(String uid);

	/**
	 * 新增学生
	 * @param student
	 * @return
	 */
	Map<String, Object> addStu(Students student);
	
	/**
	 * 修改学生信息
	 * @param stu
	 * @return code：1成功 0失败  msg：提示消息
	 */
	Map<String, Object> editStu(Students stu);

	/**
	 * 新生报到页面导出excel
	 * @param request
	 * @param response
	 * @param ids
	 */
	void xsbdExportStudents(HttpServletRequest request, HttpServletResponse response, String ids);

	/**
	 * 学生信息页面的文件导出请求
	 * @param request
	 * @param response
	 * @param ids
	 */
	void xsxxExportStudents(HttpServletRequest request, HttpServletResponse response, String ids);
	
	/**
	 * 查询未分配的学生
	 * @param fy
	 * @return
	 */
	FenyeUtil<Students> getAllWfpstu(FenyeUtil<Students> fy);

	/**
	 * 修改未分配的学生到指定的咨询师下 单独修改
	 * @param stu
	 * @param sid 
	 * @return
	 */
	Map<String, Object> editStuZxs(Students stu);

	/**
	 * 修改未分配的学生到指定的咨询师下 批量修改
	 * @param uphone
	 * @param ids
	 * @return
	 */
	Map<String, Object> editStuZxsD(String uphone, List<String> ids);

	/**
	 * 删除学生信息   软删除  删除后进入回收站 也可以还原
	 * @param sid 学生id的集合
	 * @return	数据库受影响行数
	 */
	Integer mildDel(List<String> sid, String ifvalid);

	/***
	 * 删除学生信息  真删除  将会从数据库移除数据 不可还原
	 * @param sid sid 学生id的集合
	 * @RequestParam 用集合接收参数
	 * @return 数据库受影响行数
	 */
	Integer perpetuaDel(List<String> sid);
	
	/**
	 * 自动分配
	 * @return
	 */
	Integer testFenpei();

	/**
	 * 导入excel文件
	 * @param file
	 * @return
	 */
	Object importDept(MultipartFile file, String uid);
	
	/**
	 * 查询咨询师下有多少学生
	 * @param uphone
	 * @return
	 */
	Integer getCountzxsNum(String uphone);

	/**
	 * 获取所有学生以及学生的跟踪记录
	 * @param student
	 * @return
	 */
	FenyeUtil<Students> getAllFollowsByStu(FenyeUtil<Students> fy);

	/**
	 * 获取全部学生信息
	 * @return
	 */
	List<Students> getAllStuName(String uphone);

}
