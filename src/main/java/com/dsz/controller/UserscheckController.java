package com.dsz.controller;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dsz.entity.Userscheck;
import com.dsz.service.UserscheckService;
import com.dsz.util.FenyeUtil;

/**
 *用户打卡控制器
 */
@RestController	//相当于@Controller + @ResponseBody的作用
@CrossOrigin	//解决跨域数据传输问题
public class UserscheckController {
	
	//注入打卡业务层
	@Autowired
	private UserscheckService ucService;
	
	/**
	 * 用户打卡
	 * @param uid
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/userscheck/userCheck", method = RequestMethod.POST)
	public Integer userCheck(String uid, String username, String idcard) {
		return ucService.userCheck(uid, username, idcard);
	}
	
	/**
	 * 用户签退
	 * @param uid
	 * @param username
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/userscheck/signOut", method = RequestMethod.POST)
	public Map<String, Object> signOut(String uid, String username) throws ParseException {
		return ucService.signOut(uid, username);
	}
	
	/**
	 * 检查是否签退了
	 * @param uid
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/userscheck/checkSignOut", method = RequestMethod.POST)
	public Integer checkSignOut(String uid, String username) {
		return ucService.checkSignOut(uid, username);
	}
	
	/**
	 * 多条件分页查询
	 * @param fy
	 * @param check
	 * @return
	 */
	@RequestMapping(value = "/userscheck/getAll")
	public FenyeUtil<Userscheck> getAll(FenyeUtil<Userscheck> fy, Userscheck check) {
		fy.setT(check);
		return ucService.getAll(fy);
	}
	
	/**
	 * 导出打卡记录  前端要使用无回调函数提交方式 通过表单或者href属性请求
	 * @param request
	 * @param response
	 * @param ids	接收的id
	 */
	@RequestMapping(value = "/userscheck/exportUserscheck")
	public void exportUserscheck(HttpServletRequest request, HttpServletResponse response, String ids) {
		ucService.exportUserscheck(request, response, ids);
	}

}
