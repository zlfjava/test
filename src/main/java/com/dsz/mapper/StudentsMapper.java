package com.dsz.mapper;

import java.util.List;

import com.dsz.entity.Students;
import com.dsz.util.FenyeUtil;

/**
 * 学生持久层
 */
public interface StudentsMapper {

	/**
	 * 获取所有学生
	 * @param fy
	 * @return
	 */
	List<Students> getAll(FenyeUtil<Students> fy);

	/**
	 * 获取所有学生条数
	 * @param fy
	 * @return
	 */
	Integer getCount(FenyeUtil<Students> fy);

	/**
	 * 查询当前网络咨询师录入的学生所属咨询师咨询师
	 * @param uid
	 * @return
	 */
	List<Students> getUsername(String uid);

	/**
	 * 录入学生
	 * @param student
	 * @return
	 */
	int addStu(Students student);

	/**
	 * 根据学生id查询
	 * @param id
	 * @return
	 */
	Students getOneStudents(String id);

	/**
	 * 修改学生信息
	 * @param stu
	 * @return
	 */
	int editStu(Students stu);
	
	/**
	 * 获取所有未分配的学生
	 * @param fy
	 * @return
	 */
	List<Students> getAllWfp(FenyeUtil<Students> fy);

	/**
	 * 获取所有未分配的学生的总条数
	 * @param fy
	 * @return
	 */
	Integer getCountWfp(FenyeUtil<Students> fy);

	/**
	 * 分配学生给某个咨询师 批量修改
	 * @param stu
	 * @param ids
	 * @return
	 */
	int editStuPl(String szxsid, String uphone, List<String> ids);

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
	 * 查询所有未分配的学生
	 * @return
	 */
	List<Students> getAllWfpSy();

	/**
	 * 修改
	 * @return
	 */
	Integer editStuTest(String szxsid, String uphone, String ids);
	
	/**
	 *  查询咨询师下有多少学生
	 * @param uphone
	 * @return
	 */
	Integer getCountzxsNum(String uphone);

	/**
	 * 获取学生信息与学生跟踪记录全部数据
	 * @param fy
	 * @return
	 */
	List<Students> getAllFollowsByStuData(FenyeUtil<Students> fy);

	/**
	 * 获取学生信息与学生跟踪记录全部数据条数
	 * @param fy
	 * @return
	 */
	Integer getAllFollowsByStuCount(FenyeUtil<Students> fy);

	/**
	 * 获取全部学生信息
	 * @return
	 */
	List<Students> getAllStuName(String uphone);

}
