package com.dsz.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dsz.entity.Users;
import com.dsz.service.UsersService;
import com.dsz.util.BASE64;
import com.dsz.util.BaiduOcr;
import com.dsz.util.FenyeUtil;
import com.dsz.util.MultipartToFile;

/**
 * 用户控制器
 */
@RestController	//相当于@Controller + @ResponseBody的作用
@CrossOrigin	//解决跨域数据传输问题
public class UsersController {
	
	//注入用户业务层
	@Autowired
	private UsersService userService;
	
	/**
	 * 多条件分页查询
	 * @param fy
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/users/getAllUsers")
	public FenyeUtil<Users> getAllUsers(FenyeUtil<Users> fy, Users user) {
		fy.setT(user);
		return userService.getAllUsers(fy);
	}
	
	/**
	 * 根据用户名id查询  用于检查当前登录用户的打卡状态
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/users/findByUid", method = RequestMethod.POST)
	public Users findByUid(String uid) {
		return userService.findByUid(uid);
	}
	
	/**
	 * 新增员工（用户）
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/users/addUsers", method = RequestMethod.POST)
	public FenyeUtil<Users> addUsers(Users user) {
		return userService.addUsers(user);
	}
	
	/**
	 * 修改员工信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/users/edit", method = RequestMethod.POST)
	public FenyeUtil<Users> edit(Users user) {
		return userService.edit(user);
	}	
	
	/**
	 * 用户打卡
	 * @param uid
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/users/userCheck", method = RequestMethod.POST)
	public Boolean userCheck(String uid, String username) {
		return userService.userCheck(uid, username);
	}
	
	/**
	 * 锁定和解锁
	 * @param uid
	 * @param islockout
	 * @return
	 */
	@RequestMapping(value = "/users/editIslockout", method = RequestMethod.POST)
	public Integer editIslockout(String uid, String islockout,String uuid) {
		return userService.editIslockout(uid, islockout,uuid);
	}
	
	/**
	 * 重置密码
	 * @param uid
	 * @param islockout
	 * @return
	 */
	@RequestMapping(value = "/users/resetPwd", method = RequestMethod.POST)
	public Integer resetPwd(String uid, String password) {
		return userService.resetPwd(uid, password);
	}
	
	/**
	 * 批量删除用户
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/users/delUser", method = RequestMethod.POST)
	public Map<String, Object> delUser(@RequestParam("uid")List<String> uid,String uuid) {
		return userService.delUser(uid,uuid);
	}
	
	/**
	 * 给用户添加角色
	 * @param uid
	 * @param rid
	 * @return
	 */
	@RequestMapping(value = "/users/addRoles", method = RequestMethod.POST)
	public Integer addRoles(String uid, Integer rid) {
		return userService.addRoles(uid, rid);
	}
	
	/**
	 * 移除用户的角色
	 * @param uid
	 * @param rid
	 * @return
	 */
	@RequestMapping(value = "/users/delRoles", method = RequestMethod.POST)
	public Integer delRoles(String uid, Integer rid) {
		return userService.delRoles(uid, rid);
	}
	
	/**
	 * @RequestMapping: 设置请求地址和请求方式
	 * value: 请求地址
	 * method = RequestMethod.POST: 只接收post请求
	 * 查询原密码是否正确
	 * @param uid	用户id
	 * @param password	密码
	 * @return
	 */
	@RequestMapping(value = "/users/getAllPwd",method = RequestMethod.POST)
	public Map<String, Object> getAllPwd(String uid, String password) {
		return userService.getAllPwd(uid, password);
	}
	
	/**
	 * 修改密码
	 * @param user
	 * @param newPwd
	 * @return
	 */
	@RequestMapping(value = "/users/editPwd",method = RequestMethod.POST)
	public Map<String, Object> editPwd(Users user,String newPwd) {
		return userService.editPwd(user,newPwd);
	}
	
	/**
	 * 身份证识别
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/users/testScanIdCard")
	public Map<String, Object> test(MultipartFile file) throws Exception {
		File multipartFileToFile = MultipartToFile.multipartFileToFile(file);
		//获取本地的绝对路径图片
        //File file = new File("E901AB03115B0A5D27FED8199314BDBD.jpg");
        //进行BASE64位编码
        String imageBase = BASE64.encodeImgageToBase64(multipartFileToFile);
        imageBase = imageBase.replaceAll("\r\n", "");
        imageBase = imageBase.replaceAll("\\+", "%2B");
        //百度云的文字识别接口,后面参数为获取到的token
        String httpUrl = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard?access_token="+BaiduOcr.getAuth();
        //id_card_side=front 识别正面    id_card_side=back  识别背面
        String httpArg = "detect_direction=true&id_card_side=front&image=" + imageBase;
        String jsonResult = BaiduOcr.request(httpUrl, httpArg);
        //System.out.println("返回的结果--------->" + jsonResult);
        Map<String, String> map = BaiduOcr.getHashMapByJson(jsonResult);
        //返回的身份证
        String msg = "";
        for (String key : map.keySet()) {
        	if(key == "IDCard") {
        		msg = map.get(key);
        	}
            System.out.println(key +": "+ map.get(key));
        }
        System.out.println("str:" + msg);
        Map<String, Object> msgMap = new HashMap<>();
        //判断是否为空  不为空是获取到身份证 为空没有获取到 身份证不符合规范或身份证反面无法识别
        if(!msg.equals("")) {
        	msgMap.put("code", 0);
        	msgMap.put("msg", msg);
        } else {
        	msgMap.put("code", 1);
        	msgMap.put("msg", "上传照片不符合规范");
        }
		return msgMap;
	}
	
	/**
	 * @RequestMapping: 设置请求地址和请求方式
	 * value: 请求地址
	 * method = RequestMethod.POST: 只接收post请求
	 * 根据用户名查询用户是否存在
	 * @param loginname
	 * @return
	 */
	@RequestMapping("/users/findByLoginname")
	public Map<String, Object> findByLoginname(String idcard) {
		return userService.findByLoginname(idcard);
	}
	
	/**
	 * 查询手机号跟用户名是否匹配
	 * @param protectmtel
	 * @return
	 */
	@RequestMapping("/users/Protectmtel")
	public Map<String, Object> Protectmtel(String protectmtel) {
		return userService.Protectmtel(protectmtel);
	}
	
	/**
	 * @RequestMapping: 设置请求地址和请求方式
	 * 验证码
	 * @param protectmtel
	 * @return
	 */
	@RequestMapping("/users/findByYzmpwd")
	public Map<String, Object> findByYzmpwd(String protectmtel) {
		return userService.findByYzmpwd(protectmtel);
	}
	
	/**
	 * 判断验证码是否正确
	 * @param yanzm
	 * @return
	 */
	@RequestMapping("/users/findByYanzm")
	public Map<String, Object> findByYanzm(String yanzm) {
		return userService.findByYanzm(yanzm);
	}
	
	/**
	 * @RequestMapping: 设置请求地址和请求方式
	 * 忘记密码重新设置
	 * @param password
	 * @return
	 */
	@RequestMapping("/users/setPwd")
	public Map<String, Object> setPwd(Users user,String password) {
		return userService.setPwd(user,password);
	}
	
	/**
	 * 查询所有拥有咨询师角色的用户 已经签到未签退的咨询师
	 * @return
	 */
	@RequestMapping("/users/getAllZxsname")
	public List<Users> getAllZxsname() {
		return userService.getAllZxsname();
	}
	
	/**
	 * 查询所有拥有咨询师角色的用户
	 * @return
	 */
	@RequestMapping("/users/getUserByRoles")
	public List<Users> getUserByRoles() {
		return userService.getUserByRoles();
	}
	
	/**
	 * 查询所有拥有网络咨询师角色的用户
	 * @return
	 */
	@RequestMapping("/users/getAllWlzxs")
	public FenyeUtil<Users> getAllWlzxs(FenyeUtil<Users> fy, Users user) {
		fy.setT(user);
		return userService.getAllWlzxs(fy);
	}
	
	/**
	 * 修改是否自动分量 是否
	 * @param uid
	 * @param ifweight
	 * @return
	 */
	@RequestMapping("/users/edituIfweight")
	public Integer edituIfweight(String uid, String uifweight) {
		return userService.edituIfweight(uid, uifweight);
	}
	
	/**
	 * 修改是否自动分量 是否 批量
	 * @param uid
	 * @param uifweight
	 * @return
	 */
	@RequestMapping("/users/editPluIfweight")
	public Integer editPluIfweight(String uifweight) {
		System.out.println("这是批量");
		return userService.editPluIfweight(uifweight);
	}
	
	/**
	 * 查询开启自动分配为否的人数 
	 * @return
	 */
	@RequestMapping("/users/getCountFou")
	public Integer getCountFou() {
		Integer num = userService.getCountFou();
		System.out.println("这是总条数" + num);
		return num;
	}
	
	/**
	 * 修改用户权重
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/users/editqz", method = RequestMethod.POST)
	public Map<String, Object> editqz(Users user) {
		return userService.editqz(user);
	}
	
}
