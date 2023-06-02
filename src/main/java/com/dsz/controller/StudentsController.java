package com.dsz.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dsz.entity.Students;
import com.dsz.service.StudentsService;
import com.dsz.util.FenyeUtil;

/**
 * 学生控制器
 */
@RestController // 相当于@Controller + @ResponseBody的作用
@CrossOrigin // 解决跨域数据传输问题
public class StudentsController {

	// 注入用户业务层
	@Autowired
	private StudentsService stuService;
	
	/**
	 * 多条件分页查询
	 * @param fy
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/students/getAllstu")
	public FenyeUtil<Students> getAllstu(FenyeUtil<Students> fy, Students student) {
		fy.setT(student);
		return stuService.getAllstu(fy);
	}
	
	/**
	 * 查询咨询师
	 * @return
	 */
	@RequestMapping("/students/getUsername")
	public List<Students> getUsername(String uid) {
		return stuService.getUsername(uid);
	}
	
	/**
	 * 新增学生
	 * @param student
	 * @return
	 */
	@RequestMapping(value = "/students/addStu", method = RequestMethod.POST)
	public Map<String, Object> addStu(Students student) {
		return stuService.addStu(student);
	}
	
	/**
	 * 修改学生信息
	 * @param stu 学生实体
	 * @return code：1成功 0失败  msg：提示消息
	 */
	@RequestMapping(value = "/students/editStu", method = RequestMethod.POST)
	public Map<String, Object> editStu(Students stu) {
		return stuService.editStu(stu);
	}
	
	/**
	 * 新生报到页面导出excel
	 * @param request
	 * @param response
	 * @param ids
	 */
	@RequestMapping(value = "/students/xsbdExportStudents")
	public void exportUserscheck(HttpServletRequest request, HttpServletResponse response, String ids) {
		stuService.xsbdExportStudents(request, response, ids);
	}
	
	/**
	 * 学生信息页面的文件导出请求
	 * @param request
	 * @param response
	 * @param ids
	 */
	@RequestMapping(value = "/students/xsxxExportStudents")
	public void xsxxExportStudents(HttpServletRequest request, HttpServletResponse response, String ids) {
		stuService.xsxxExportStudents(request, response, ids);
	}
	
	/**
	 * 查询未分配的学生
	 * @param fy
	 * @param student
	 * @return
	 */
	@RequestMapping(value = "/students/getAllWfpstu", method = RequestMethod.POST)
	public FenyeUtil<Students> getAllWfpstu(FenyeUtil<Students> fy, Students student) {
		fy.setT(student);
		return stuService.getAllWfpstu(fy);
	}
	
	/**
	 * 修改未分配的学生到指定的咨询师下 单独修改
	 * @param stu
	 * @return
	 */
	@RequestMapping(value = "/students/editStuZxs", method = RequestMethod.POST)
	public Map<String, Object> editStuZxs(Students stu) {
		return stuService.editStuZxs(stu);
	}
	
	/**
	 * 修改未分配的学生到指定的咨询师下 批量修改
	 * @param stu
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/students/editStuZxsD", method = RequestMethod.POST)
	public Map<String, Object> editStuZxsD(String uphone,@RequestParam("ids")List<String> ids) {
		return stuService.editStuZxsD(uphone,ids);
	}
	
	/**
	 * 删除学生信息   软删除  删除后进入回收站 也可以还原
	 * @param sid 学生id的集合
	 * @return	数据库受影响行数
	 */
	@RequestMapping(value = "/students/mildDel", method = RequestMethod.POST)
	public Integer mildDel(@RequestParam("sid")List<String> sid, String ifvalid) {
		return stuService.mildDel(sid, ifvalid);
	}
	
	/***
	 * 删除学生信息  真删除  将会从数据库移除数据 不可还原
	 * @param sid sid 学生id的集合
	 * @RequestParam 用集合接收参数
	 * @return 数据库受影响行数
	 */
	@RequestMapping(value = "/students/perpetuaDel", method = RequestMethod.POST)
	public Integer perpetuaDel(@RequestParam("sid")List<String> sid) {
		return stuService.perpetuaDel(sid);
	}
	
	/**
	 * 自动分配
	 * @return
	 */
	@RequestMapping(value = "/students/testFenpei", method = RequestMethod.POST)
	public Integer testFenpei() {
		return stuService.testFenpei();
	}
	
	/**
	 * 导入文件
	 * @param file
	 * @return
	 */
	@RequestMapping("/students/importStudents")
	public Object importDept(@RequestParam(value = "file", required = false)MultipartFile file, String uid) {
		System.out.println("接收到了Excel上传请求");
		return stuService.importDept(file, uid);
	}
	
	/**
	 * 查询咨询师下有多少学生
	 * @param uphone
	 * @return
	 */
	@RequestMapping(value = "/students/getCountzxsNum", method = RequestMethod.POST)
	public Integer getCountzxsNum(String uphone) {
		return stuService.getCountzxsNum(uphone);
	}
	
	/**
	 * 获取所有学生以及学生的跟踪记录
	 * @param fy
	 * @param student
	 * @return
	 */
	@RequestMapping(value = "/students/getAllFollowsByStu", method = RequestMethod.POST)
	public FenyeUtil<Students> getAllFollowsByStu(FenyeUtil<Students> fy, Students student) {
		fy.setT(student);
		return stuService.getAllFollowsByStu(fy);
	}
	
	/**
	 * 获取全部学生信息
	 * @return
	 */
	@RequestMapping(value = "/students/getAllStuName", method = RequestMethod.POST)
	public List<Students> getAllStuName(String uphone) {
		return stuService.getAllStuName(uphone);
	}
	
}
