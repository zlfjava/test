package com.dsz.mapper;

import java.util.List;

import com.dsz.entity.Users;
import com.dsz.util.FenyeUtil;

/**
 * 用户持久层
 */
public interface UsersMapper {
	
	/**
	 * 获取所有用户
	 * @param fy
	 * @return
	 */
	List<Users> getAll(FenyeUtil<Users> fy);

	/**
	 * 获取所有用户条数
	 * @param fy
	 * @return
	 */
	Integer getCount(FenyeUtil<Users> fy);

	/**
	 * 新增员工（用户）
	 * @param user
	 * @return
	 */
	Integer addUsers(Users user);

	/**
	 * 根据用户名和密码查询是否有该用户
	 * @param loginname
	 * @param password
	 * @return
	 */
	Users findByLoginname(String idcard, String password);

	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	Integer updateUsers(Users user);

	/**
	 * 根据用户名id查询
	 * @param uid
	 * @return	Users
	 */
	Users findByUid(String uid);

	/**
	 * 批量删除用户
	 * @param uid
	 * @return
	 */
	Integer delUser(List<String> uid);

	/**
	 * 给用户添加角色
	 * @param uid
	 * @param rid
	 * @return
	 */
	Integer addRoles(String uid, Integer rid);

	/**
	 * 移除用户的角色
	 * @param uid
	 * @param rid
	 * @return
	 */
	Integer delRoles(String uid, Integer rid);

	/**
	 * 查询原密码是否正确
	 * @param uid
	 * @param password
	 * @return
	 */
	Users findByPassword(String uid, String password);

	
    /**
	 * 判断是否有重复数据
	 * @param user
	 * @return
	 */
	Users checkRepeat(Users user);

	/**
	 * 根据手机号查询
	 * @param protectmtel
	 * @return
	 */
	Users Protectmtel(String protectmtel);
	
	/**
	 * 查询所有拥有咨询师角色的用户
	 * @param time 
	 * @return
	 */
	List<Users> getAllZxsname(String time);
	
	/**
	 * 根据手机号查找姓名
	 * @param phone
	 * @return
	 */
	Users getUphoneName(String phone);

	/**
	 * 查询所有拥有咨询师角色的用户
	 * @return
	 */
	List<Users> getUserByRoles();
	
	/**
	 * 查询所有拥有网络咨询师角色的用户
	 * @return
	 */
	List<Users> getAllWlzxs(FenyeUtil<Users> fy);
	
	/**
	 * 查询所有拥有网络咨询师角色的用户 总条数
	 * @param fy
	 * @return
	 */
	Integer getCountWlzxs(FenyeUtil<Users> fy);

	/**
	 * 修改是否自动分量 是否 批量
	 * @param uid
	 * @param uifweight
	 * @return
	 */
	Integer editPluIfweight(String uifweight);

	/**
	 * 查询开启自动分配为否的人数 
	 * @return
	 */
	Integer getCountFou();
	
	/**
	 * 根据手机号查询咨询师下学生的数量
	 * @param protectmtel
	 * @return
	 */
	int getCountByProtectmtel(String protectmtel);
	
	/**
	 * 查询所有拥有网络咨询师的用户  自动分配用
	 * @return 
	 */
	Users getAllWlzxsFenpei(String uid);
	
	/**
	 * 随机查询一个拥有咨询师角色的用户
	 * @param time 
	 * @return
	 */
	Users getZxsSj(String time);

	/**
	 * 查询当前用户拥有多少角色
	 * @param uid
	 * @return
	 */
	int getUserReloseCount(List<String> uid);

}
