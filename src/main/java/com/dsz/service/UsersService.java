package com.dsz.service;

import java.util.List;
import java.util.Map;

import com.dsz.entity.Users;
import com.dsz.util.FenyeUtil;

/**
 * 用户业务层
 */
public interface UsersService {

	/**
	 * 新增员工（用户）
	 * @param user
	 * @return
	 */
	FenyeUtil<Users> addUsers(Users user);

	/**
	 * 多条件分页查询
	 * @param fy
	 * @return
	 */
	FenyeUtil<Users> getAllUsers(FenyeUtil<Users> fy);

	/**
	 * 登录
	 * @param loginname
	 * @param password
	 * @return
	 */
	Map<String, Object> userLogin(String idcard, String password);

	/**
	 * 根据用户名id查询
	 * @param uid
	 * @return
	 */
	Users findByUid(String uid);

	/**
	 * 用户打卡
	 * @param uid
	 * @param username
	 * @return
	 */
	Boolean userCheck(String uid, String username);

	/**
	 * 锁定和解锁
	 * @param uid
	 * @param islockout
	 * @return
	 */
	Integer editIslockout(String uid, String islockout,String uuid);

	/**
	 * 重置密码
	 * @param uid
	 * @param password
	 * @return
	 */
	Integer resetPwd(String uid, String password);

	/**
	 * 修改员工信息
	 * @param user
	 * @return
	 */
	FenyeUtil<Users> edit(Users user);

	/**
	 * 批量删除用户
	 * @param uid
	 * @return
	 */
	Map<String, Object> delUser(List<String> uid,String uuid);

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
	 * 修改密码
	 * @param username
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	Map<String, Object> editPwd(Users user, String newPwd);

	/**
	 * 查询原密码是否正确
	 * @param loginname
	 * @param password
	 * @return
	 */
	Map<String, Object> getAllPwd(String uid, String password);

	/**
	 * 根据用户名查询用户是否存在
	 * @param loginname
	 * @return
	 */
	Map<String, Object> findByLoginname(String idcard);

	/**
	 * 查询手机号跟用户名是否匹配
	 * @param protectmtel
	 * @return
	 */
	Map<String, Object> Protectmtel(String protectmtel);

	/**
	 * 验证码
	 * @param protectmtel
	 * @return
	 */
	Map<String, Object> findByYzmpwd(String protectmtel);

	/**
	 * 判断验证码是否正确
	 * @param yanzm
	 * @return
	 */
	Map<String, Object> findByYanzm(String yanzm);

	/**
	 * 忘记密码重新设置
	 * @param password
	 * @return
	 */
	Map<String, Object> setPwd(Users user,String password);
	
	/**
	 * 查询所有拥有咨询师角色的用户 已经签到未签退的咨询师
	 * @return
	 */
	List<Users> getAllZxsname();

	/**
	 * 查询所有拥有咨询师角色的用户
	 * @return
	 */
	List<Users> getUserByRoles();
	
	/**
	 *  查询所有拥有网络咨询师角色的用户
	 * @return
	 */
	FenyeUtil<Users> getAllWlzxs(FenyeUtil<Users> fy);
	
	/**
	 * 修改是否自动分量 是否
	 * @param uid
	 * @param ifweight
	 * @return
	 */
	Integer edituIfweight(String uid, String uifweight);

	/**
	 * 修改是否自动分量 是否 批量
	 * @param uid
	 * @param uifweight
	 * @return
	 */
	Integer editPluIfweight(String uifweight);

	/**
	 *  查询开启自动分配为否的人数 
	 * @return
	 */
	Integer getCountFou();
	
	/**
	 * 修改用户权重
	 * @param user
	 * @return
	 */
	Map<String, Object> editqz(Users user);

}
