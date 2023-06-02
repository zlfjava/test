package com.dsz.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.dsz.entity.Users;
import com.dsz.entity.Userscheck;
import com.dsz.mapper.UsersMapper;
import com.dsz.mapper.UserscheckMapper;
import com.dsz.service.UsersService;
import com.dsz.util.FenyeUtil;
import com.dsz.util.GetDateUtil;
import com.dsz.util.MD5Util;
import com.dsz.util.SendMsg;
import com.dsz.util.TokenUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 用户业务实现层
 */
@Service
@Transactional
public class UsersServiceImpl implements UsersService {

	// 注入用户持久层
	@Autowired
	private UsersMapper userMapper;

	// 注入用户打卡记录持久层
	@Autowired
	private UserscheckMapper ucMapper;

	/**
	 * 多条件分页查询
	 */
	@Override
	public FenyeUtil<Users> getAllUsers(FenyeUtil<Users> fy) {
		List<Users> data = userMapper.getAll(fy);
		Integer count = userMapper.getCount(fy);
		if (data.size() > 0) {
			fy.setCode(0);
			fy.setData(data);
			fy.setCount(count);
		} else {
			fy.setCode(1);
			fy.setMsg("未找到信息");
		}
		return fy;
	}

	/**
	 * 根据登录名和密码查找 （登录）
	 */
	@Override
	public Map<String, Object> userLogin(String idcard, String password) {
		Map<String, Object> map = new HashMap<>(); // 存储返回值
		GetDateUtil date = new GetDateUtil(); // 实例化获取时间的工具类
		Users usByLoginName = userMapper.findByLoginname(idcard, null); // 根据登录名查询是否有该用户
		if (usByLoginName == null) { // 表示没有该用户
			map.put("code", 0); // 状态码
			map.put("msg", "用户名不存在");
			return map; // 返回map集合
		}
		if ("是".equals(usByLoginName.getIslockout())) {
			map.put("code", 0); // 状态码
			map.put("msg", "用户已被锁定，请联系管理员或者明天再试");
			return map; // 返回map集合
		}
		MD5Util md5 = new MD5Util(); // 实例化md5加密类
		password = md5.getMD5Pwd(password); // 将密码参数转化成md5形式
		Users user = userMapper.findByLoginname(idcard, password); // 从数据库中查询
		if (user == null) { // 说明密码错误
			int psdwrongtime = usByLoginName.getPsdwrongtime() + 1; // 获取密码错误次数并将密码错误次数加一
			String uid = usByLoginName.getUid(); // 获取id主键，根据id修改
			Users usPsdwrong = new Users(uid, psdwrongtime); // 利用构造方法设置值
			userMapper.updateUsers(usPsdwrong); // 修改密码错误次数
			if (psdwrongtime == 2) { // 当密码错误两次时的返回参数
				map.put("code", 2); // 状态码
				map.put("msg", "密码错误"); // 提示信息
				return map; // 返回map集合
			}
			if (psdwrongtime < 3) {
				map.put("code", 0); // 状态码
				map.put("msg", "密码错误"); // 提示信息
				return map; // 返回map集合
			} else {
				String islockout = "是";
				String locktime = date.getTime(); // 获取当前时间
				Users usIslockout = new Users(uid, islockout, locktime); // 利用构造方法设置值
				userMapper.updateUsers(usIslockout); // 锁定用户
				map.put("code", 0); // 状态码
				map.put("msg", "密码错误三次，账号已被锁定，请联系管理员或者明天再试"); // 提示信息
				return map; // 返回map集合
			}
		}
		// 如果登录成功 等待生成token
		String token = null;
		try {
			// 工具类生成token
			token = TokenUtil.sign(user, 2 * 60 * 60 * 1000); // 2小时有效期
		} catch (UnsupportedEncodingException | JsonProcessingException e) {
			e.printStackTrace();
		}
		map.put("token", token); // 添加token
		map.put("uid", user.getUid()); // 添加id
		map.put("loginname", user.getLoginname()); // 添加登录名
		map.put("idcard", user.getIdcard()); // 添加登录名
		map.put("protectmtel", user.getProtectmtel()); // 添加手机号
		String uid = user.getUid(); // 获取当前登录账号的id，根据id进行修改
		int psdwrongtime = 0; // 将密码错误次数归0
		Users usPsdwrong = new Users(uid, psdwrongtime); // 利用构造方法设置值
		userMapper.updateUsers(usPsdwrong); // 修改密码错误次数
		// 修改登录时间
		String lastlogintime = date.getTime(); // 获取时间
		Users usLastlogintime = new Users(uid, lastlogintime); // 利用构造方法设置值
		userMapper.updateUsers(usLastlogintime); // 修改最后一次登录时间
		return map;
	}

	/**
	 * 新增员工（用户）
	 */
	@Override
	public FenyeUtil<Users> addUsers(Users user) {
		FenyeUtil<Users> fy = new FenyeUtil<>(); // 存储返回值
		if (!StringUtils.isEmpty(user.getIdcard())) { // 判断身份证号是否重复
			Users us = new Users();
			us.setIdcard(user.getIdcard());
			Users checkRepeat = userMapper.checkRepeat(us); // 判断是否有重复数据
			if (checkRepeat != null) {
				fy.setCode(5); // 表示身份证被占用
				fy.setMsg("身份证号已被占用！");
				return fy;
			}
		}
		if (!StringUtils.isEmpty(user.getProtectmtel())) { // 判断手机号是否重复
			Users us = new Users();
			us.setProtectmtel(user.getProtectmtel());
			Users checkRepeat = userMapper.checkRepeat(us); // 判断是否有重复数据
			if (checkRepeat != null) {
				fy.setCode(6); // 表示手机号被占用
				fy.setMsg("手机号已被占用！");
				return fy;
			}
		}
		if (!StringUtils.isEmpty(user.getProtectemail())) { // 判断用户名是否重复
			Users us = new Users();
			us.setProtectemail(user.getProtectemail());
			Users checkRepeat = userMapper.checkRepeat(us); // 判断是否有重复数据
			if (checkRepeat != null) {
				fy.setCode(7); // 表示邮箱已存在
				fy.setMsg("邮箱已存在！");
				return fy;
			}
		}
		if (StringUtils.isEmpty(user.getLastlogintime())) {
			System.out.println("不是点击新增按钮");
			return fy;
		}
		String uid = UUID.randomUUID().toString(); // 手动生成uuid
		MD5Util md5 = new MD5Util(); // 实例化MD5加密类
		String idCardPwd = "~" + user.getIdcard().substring(user.getIdcard().length() - 6); // 获取身份证号后六位
		String password = md5.getMD5Pwd(idCardPwd); // MD5加密密码
		GetDateUtil date = new GetDateUtil(); // 实例化日期工具类
		Users us = new Users(uid, user.getLoginname(), password, "否", null, date.getTime(), 0, null,
				user.getProtectemail(), user.getProtectmtel(), user.getIdcard(), "否", null, "否");
		int num = userMapper.addUsers(us); // 执行添加操作
		if (num > 0) {
			fy.setCode(0);
			fy.setMsg("创建成功！");
			return fy;
		} else {
			fy.setCode(1);
			fy.setMsg("创建失败！");
			return fy;
		}
	}

	/**
	 * 根据用户名id查询 用于检查当前登录用户的打卡状态
	 */
	@Override
	public Users findByUid(String uid) {
		return userMapper.findByUid(uid);
	}

	/**
	 * 用户打卡
	 */
	@Override
	public Boolean userCheck(String uid, String username) {
		Users user = new Users();
		user.setUid(uid);
		user.setLoginname(username);
		user.setIssign("是");
		int num1 = userMapper.updateUsers(user); // 将是否打卡设置为是
		GetDateUtil date = new GetDateUtil();
		// 修改打卡记录表的是否打卡为是 并给上打卡时间
		Userscheck uc = new Userscheck(null, uid, username, "是", date.getTime(), null, null, date.getRiQi());
		int num2 = ucMapper.updateUsersCheck(uc);
		if (num1 > 0 && num2 > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 锁定和解锁
	 */
	@Override
	public Integer editIslockout(String uid, String islockout, String uuid) {
		// 判断当前用户登录不能删除
		if (uuid.equals(uid)) {
			return 0;
		} else {
			GetDateUtil date = new GetDateUtil(); // 实例化时间日期类
			Users us = new Users(); // 实例化用户实体类
			us.setUid(uid); // 设置id
			us.setIslockout(islockout); // 设置是否锁定
			if ("是".equals(islockout)) { // 如果执行的是锁定操作
				us.setLocktime(date.getTime()); // 设置锁定时间
				return userMapper.updateUsers(us); // 执行锁定操作 并返回数据库受影响行数
			}
			us.setPsdwrongtime(0); // 重新设置密码错误次数为0
			us.setLocktime(""); // 取消锁定时间
			return userMapper.updateUsers(us); // 执行解锁操作 并返回数据库受影响行数
		}
	}

	/**
	 * 重置密码
	 */
	@Override
	public Integer resetPwd(String uid, String password) {
		Users us = new Users(); // 实例化用户实体类
		us.setUid(uid); // 设置id
		MD5Util md5 = new MD5Util(); // 实例化密码加密类
		password = md5.getMD5Pwd(password); // 加密~+身份证好
		us.setPassword(password); // 设置重置后的密码
		return userMapper.updateUsers(us);
	}

	/**
	 * 修改员工信息
	 */
	@Override
	public FenyeUtil<Users> edit(Users user) {
		FenyeUtil<Users> fy = new FenyeUtil<>(); // 存储返回值
		if (!StringUtils.isEmpty(user.getIdcard())) { // 判断身份证号是否重复
			Users us = new Users();
			us.setUid(user.getUid()); // 查询修改时的去重复需要用到
			us.setIdcard(user.getIdcard());
			Users checkRepeat = userMapper.checkRepeat(us); // 判断是否有重复数据
			if (checkRepeat != null) {
				fy.setCode(5); // 表示身份证被占用
				fy.setMsg("身份证号已被占用！");
				return fy;
			}
		}
		if (!StringUtils.isEmpty(user.getProtectmtel())) { // 判断手机号是否重复
			Users us = new Users();
			us.setUid(user.getUid());
			us.setProtectmtel(user.getProtectmtel());
			Users checkRepeat = userMapper.checkRepeat(us); // 判断是否有重复数据
			if (checkRepeat != null) {
				fy.setCode(6); // 表示手机号被占用
				fy.setMsg("手机号已被占用！");
				return fy;
			}
		}
		if (!StringUtils.isEmpty(user.getProtectemail())) { // 判断用户名是否重复
			Users us = new Users();
			us.setUid(user.getUid());
			us.setProtectemail(user.getProtectemail());
			Users checkRepeat = userMapper.checkRepeat(us); // 判断是否有重复数据
			if (checkRepeat != null) {
				fy.setCode(7); // 表示邮箱已存在
				fy.setMsg("邮箱已存在！");
				return fy;
			}
		}
		if (StringUtils.isEmpty(user.getCreatetime())) {
			System.out.println("不是点击修改按钮");
			return fy;
		}
		int num = userMapper.updateUsers(user); // 执行修改操作
		if (num > 0) {
			fy.setCode(0);
			fy.setMsg("修改成功！");
		} else {
			fy.setCode(1);
			fy.setMsg("修改失败");
		}
		return fy;
	}

	/**
	 * 批量删除用户
	 */
	@Override
	public Map<String, Object> delUser(List<String> uid, String uuid) {
		Map<String, Object> map = new HashMap<>();
		Users user = userMapper.findByUid(uuid);
		// 判断当前用户登录不能删除
		for (int i = 0; i < uid.size(); i++) {
			if (uuid.equals(uid.get(i))) {
				map.put("code", 0);
				map.put("msg", "当前用户" + user.getLoginname() + "正在登录，不能删除");
				return map;
			}
		}
		int rolesNum = userMapper.getUserReloseCount(uid); // 查询当前用户拥有几个角色
		if (rolesNum > 0) {
			map.put("code", 0);
			map.put("msg", "当前用户有" + rolesNum + "个角色，不能删除");
			return map;
		}
		int num = userMapper.delUser(uid);
		if (num > 0) {
			map.put("code", num);
			map.put("msg", "删除成功！");
		} else {
			map.put("code", 0);
			map.put("msg", "删除失败！");
			return map;
		}
		return map;
	}

	/***
	 * 给用户添加角色
	 */
	@Override
	public Integer addRoles(String uid, Integer rid) {
		return userMapper.addRoles(uid, rid);
	}

	/**
	 * 移除用户的角色
	 */
	@Override
	public Integer delRoles(String uid, Integer rid) {
		return userMapper.delRoles(uid, rid);
	}

	/**
	 * 修改密码
	 */
	@Override
	public Map<String, Object> editPwd(Users user, String newPwd) {
		Map<String, Object> map = new HashMap<>(); // 实例化map集合
		MD5Util md5 = new MD5Util(); // 实例化密码加密工具类
		newPwd = md5.getMD5Pwd(newPwd); // 加密密码
		user.setPassword(newPwd); // 设置加密后的面
		int num = userMapper.updateUsers(user); // 执行修改操作
		if (num > 0) { // 判断返回值 如果大于0说明修改成功
			map.put("code", "1"); // 设置成功的状态码
			map.put("msg", "密码修改成功，请使用新密码重新登录"); // 提示消息
		}
		return map; // 返回状态码和提示消息
	}

	/**
	 * 判断原密码是否正确
	 */
	@Override
	public Map<String, Object> getAllPwd(String uid, String password) {
		Map<String, Object> map = new HashMap<>(); // 实例化map集合
		MD5Util md5 = new MD5Util(); // 实例化密码加密工具类
		password = md5.getMD5Pwd(password); // 加密密码
		// 根据用户id和密码查询
		Users user = userMapper.findByPassword(uid, password);
		if (user == null) { // 如果查询结果为空 说明密码错误
			map.put("code", "0"); // 设置错误的状态码
			map.put("msg", "原密码错误"); // 设置提示语句
			return map; // 返回值
		}
		return map;
	}

	private String Loginname = ""; // 用户名
	private String uid = ""; // 用户id

	/**
	 * 根据用户名查询用户是否存在
	 */
	@Override
	public Map<String, Object> findByLoginname(String idcard) {
		Map<String, Object> map = new HashMap<>(); // 实例化map集合 存储返回值
		Users user = userMapper.findByLoginname(idcard, null); // 根据用户名查找
		if (user == null) { // 如果返回值为null 说明没有查到数据 该用户名不存在
			map.put("code", 0); // 状态码
			map.put("msg", "身份证号不存在"); // 提示消息
			return map; // 返回map集合
		} else {
			map.put("code", 1); // 状态码
			map.put("msg", "身份证号正确"); // 提示消息
			Loginname = idcard; // 将用户名设置为全局的
			return map; // 返回map集合
		}
	}

	/**
	 * 查询手机号跟用户名是否匹配
	 */
	@Override
	public Map<String, Object> Protectmtel(String protectmtel) {
		Map<String, Object> map = new HashMap<>();
		Users user = userMapper.Protectmtel(protectmtel);
		if (user == null) {
			map.put("code", 0); // 状态码
			map.put("msg", "该手机号未绑定");
			return map; // 返回map集合
		}
		String name = user.getIdcard();
		uid = user.getUid();
		if (Loginname.equals(name)) {
			map.put("code", 1); // 状态码
			map.put("msg", "手机号正确");
			return map; // 返回map集合
		} else {
			map.put("code", 0); // 状态码
			map.put("msg", "不是您所绑定的手机号");
			return map; // 返回map集合
		}
	}

	private String code = ""; // 验证码

	/**
	 * 验证码
	 */
	@Override
	public Map<String, Object> findByYzmpwd(String protectmtel) {
		Map<String, Object> map = new HashMap<>(); // 实例化map集合 用来存储返回值
		code = SendMsg.getCode(); // 调用工具类里的方法生成验证码
		System.out.println("验证码：" + code); // 打印验证码到控制台
		SendMsg.sendMessage(protectmtel, code); // 手机号发送短信
		return map;
	}

	/**
	 * 判断验证码是否正确
	 */
	@Override
	public Map<String, Object> findByYanzm(String yanzm) {
		Map<String, Object> map = new HashMap<>(); // 实例化map集合 用来存储返回值
		if (yanzm.equals(code)) { // 判断验证码是否正确
			map.put("code", 1); // 状态码
			map.put("msg", "验证码正确"); // 给出正确的提示
			return map; // 返回map集合
		} else {
			map.put("code", 0); // 状态码
			map.put("msg", "验证码错误"); // 错误的提示
			return map; // 返回map集合
		}
	}

	/**
	 * 忘记密码重新设置
	 */
	@Override
	public Map<String, Object> setPwd(Users user, String password) {
		Map<String, Object> map = new HashMap<>(); // 实例化map集合 用来存储返回值
		MD5Util md5 = new MD5Util(); // 实例化密码加密类
		password = md5.getMD5Pwd(password); // 加密密码
		// 取消锁定时间 重新设置密码错误次数为0
		Users us = new Users(uid, password, "否", 0, ""); // 使用实体类设置参数
		int num = userMapper.updateUsers(us); // 执行修改操作
		if (num > 0) { // 如果返回值大于0 说明修改成功
			map.put("code", 1); // 设置修改成功的状态码
		} else {
			map.put("code", 0); // 设置修改失败的状态码
			map.put("msg", "密码重置失败"); // 失败的提示
		}
		return map; // 返回状态码和提示消息
	}

	/**
	 * 查询所有拥有咨询师角色的用户 已经签到未签退的咨询师
	 */
	@Override
	public List<Users> getAllZxsname() {
		GetDateUtil date = new GetDateUtil();
		String time = date.getRiQi();
		return userMapper.getAllZxsname(time);
	}

	/**
	 * 查询所有拥有咨询师角色的用户
	 */
	@Override
	public List<Users> getUserByRoles() {
		return userMapper.getUserByRoles();
	}

	/**
	 * 查询所有拥有网络咨询师角色的用户
	 */
	@Override
	public FenyeUtil<Users> getAllWlzxs(FenyeUtil<Users> fy) {
		List<Users> data = userMapper.getAllWlzxs(fy);
		Integer count = userMapper.getCountWlzxs(fy);
		if (data.size() > 0) {
			fy.setCode(0);
			fy.setData(data);
			fy.setCount(count);
		} else {
			fy.setCode(1);
			fy.setMsg("未找到信息");
		}
		return fy;
	}

	/**
	 * 修改是否自动分量 是否
	 */
	@Override
	public Integer edituIfweight(String uid, String uifweight) {
		Users us = new Users(); // 实例化用户实体类
		us.setUid(uid); // 设置id
		us.setUifweight(uifweight); // 设置是否锁定
		if ("是".equals(uifweight)) { // 如果执行的是锁定操作
			return userMapper.updateUsers(us); // 执行锁定操作 并返回数据库受影响行数
		}
		return userMapper.updateUsers(us); // 执行解锁操作 并返回数据库受影响行数
	}

	/**
	 * 修改是否自动分量 是否 批量
	 */
	@Override
	public Integer editPluIfweight(String uifweight) {
		return userMapper.editPluIfweight(uifweight);
	}

	/**
	 * 查询开启自动分配为否的人数
	 */
	@Override
	public Integer getCountFou() {
		return userMapper.getCountFou();
	}

	/**
	 * 修改用户权重
	 */
	@Override
	public Map<String, Object> editqz(Users user) {
		Map<String, Object> map = new HashMap<>();
		int num = userMapper.updateUsers(user); // 执行修改操作
		System.out.println("这是修改返回" + num);
		if (num > 0) {
			map.put("code", 1);
			map.put("msg", "修改成功");
		} else {
			map.put("code", 0);
			map.put("msg", "修改失败");
		}
		return map;
	}

}
