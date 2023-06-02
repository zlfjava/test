package com.dsz.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsz.service.UsersService;

/**
 * 登录请求控制器
 */
@RestController	//相当于@Controller + @ResponseBody的作用
@CrossOrigin	//解决跨域数据传输问题
public class LoginController {
	
	//注入业务层
	@Autowired
	private UsersService userService;
	
	/**
	 * 登录请求
	 * @param loginname
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
	public Map<String, Object> userLogin(String idcard, String password) {
		return userService.userLogin(idcard, password);	//将数据返回给前端页面
	}
	
	/**
	 * 测试token是否有效
	 * @return
	 */
	@RequestMapping("/testToken")
	public Boolean testToken() {
		return true;
	}

}
