package com.dsz.service.impl;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsz.entity.Users;
import com.dsz.entity.Userscheck;
import com.dsz.mapper.UsersMapper;
import com.dsz.mapper.UserscheckMapper;
import com.dsz.service.UserscheckService;
import com.dsz.util.ExportExcelUtil;
import com.dsz.util.FenyeUtil;
import com.dsz.util.GetDateUtil;

/**
 * 打卡业务实现层
 */
@Service
@Transactional
public class UserscheckServiceImpl implements UserscheckService {

	// 注入打卡持久层
	@Autowired
	private UserscheckMapper ucMapper;
	
	//注入用户持久层
	@Autowired
	private UsersMapper usMapper;

	/**
	 * 新增打卡记录
	 */
	@Override
	public Integer userCheck(String uid, String username, String idcard) {
		GetDateUtil date = new GetDateUtil(); // 实例化时间工具类
		String istime = date.getRiQi(); // 获取年月日 不带时分秒
		Userscheck userscheck = ucMapper.getUserscheck(uid, username, istime); // 查询当天是否有打卡记录
		if (userscheck == null) { // 说明当天还没有记录
			Users us = usMapper.findByUid(uid);
			String ucid = UUID.randomUUID().toString(); // 手动生成uuid
			Integer num = ucMapper.addUsersCheck(ucid,uid,username,us.getProtectmtel(),"否","否",istime); // 添加记录
			return num;
		}
		return 0;
	}

	/**
	 * 用户签退操作
	 * @throws ParseException  将字符串解析为时间
	 */
	@Override
	public Map<String, Object> signOut(String uid, String username) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		GetDateUtil date = new GetDateUtil(); // 实例化获取时间的工具类
		Userscheck ucTime = ucMapper.getUserscheck(uid, username, date.getRiQi());
		String strDate = ucTime.getSignintime();	//获取数据库存储的打卡时间
		Date dakaTime = sdf.parse(strDate);	//把字符串的时间解析成功date
		Long num1 = dakaTime.getTime()  + 600000;	//把当前时间转化为毫秒打卡时间加上十分钟
		String strDate2 = date.getTime();	//获取系统时间
		Date dakaTime2 = sdf.parse(strDate2);	//解析
		Long num2 = dakaTime2.getTime();	//把当前时间转化为毫秒
		if (num2 > num1) {	//如果当前时间大于数据库打卡时间 说明已经过了十分钟 可以签退
			// 设置签退 签退时间 和修改条件
			Userscheck uc = new Userscheck(uid, username, "是", date.getTime(), date.getRiQi());
			int count = ucMapper.updateUsersCheck(uc); // 执行修改操作，并返回数据库受影响行数
			if (count > 0) {
				map.put("code", "1");
				map.put("msg", "签退成功");
			}
		} else {
			long millis = num1 - num2;  //相减得到毫秒数
		    long minutes = (millis / 1000)  / 60;	//得到分钟
		    String str = minutes == 0 ? "" : String.valueOf(minutes) + "分钟";
		    int seconds = (int)((millis / 1000) % 60);	//得到秒
			map.put("code", "0");
			map.put("msg", str + seconds + "秒后才可以签退哦");
		}
		return map;
	}

	/**
	 * 检查是否已经签退了
	 */
	@Override
	public Integer checkSignOut(String uid, String username) {
		GetDateUtil date = new GetDateUtil(); // 实例化时间工具类
		String istime = date.getRiQi(); // 获取年月日 不带时分秒
		Userscheck uc = ucMapper.getUserscheck(uid, username, istime); //查询当天是否有打卡记录
		try {
			if ("否".equals(uc.getSignout()) && "是".equals(uc.getSignstatus())) { //说明打卡了，但没有签退
				return 1;
			} else if ("是".equals(uc.getSignout()) && "是".equals(uc.getSignstatus())) { //说明既打卡了，又签退了
				return 2;
			}
		} catch (Exception e) {
			//异常捕捉
		}
		return 0;
	}
	
	/**
	 * 多条件分页查询
	 */
	@Override
	public FenyeUtil<Userscheck> getAll(FenyeUtil<Userscheck> fy) {
		List<Userscheck> data = ucMapper.getAll(fy);
		Integer count = ucMapper.getCount(fy);
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
	 * 导出打卡记录
	 */
	@Override
	public void exportUserscheck(HttpServletRequest request, HttpServletResponse response, String ids) {
		String title = "考勤记录表";	//excel sheeet标题
		String[] headers = {"员工名", "电话号码", "是否打卡", "打卡时间", "是否签退", "签退时间", "当前日期"};	//excel表格的列名
		String fileName = "考勤记录" + System.currentTimeMillis() + ".xlsx";	//excel文件名以及后缀
		String[] arr = ids.split(",");	//String转String数组
		// List of Teacher Type
		List<Map<String, Object>> list = new ArrayList<>();
		// HashSet集合去除重复id
		HashSet<String> hs = new HashSet<String>();
		for (int i = 0; i < arr.length; i++) {
			String id = arr[i];	//String转Integer（如果要Integer类型的要转，这里并没有转换）
			hs.add(id);	//数组元素添加到HashSet去重复
		}
		for (String id : hs) {
			Userscheck uc = ucMapper.getOneUsersCheck(id);	//根据打卡记录id查询打卡记录 每循环一次就查询一条
			Map<String, Object> map = new HashMap<String, Object>();	//存数据
			map.put("username", uc.getUsername());
			map.put("uphone", uc.getUphone());
			map.put("signstatus", uc.getSignstatus());
			map.put("signintime", uc.getSignintime());
			map.put("signout", uc.getSignout());
			map.put("signouttime", uc.getSignouttime());
			map.put("istime", uc.getIstime());
			list.add(map);
		}
		// excel元素 "员工名", "电话号码", "是否打卡", "打卡时间", "是否签退", "签退时间", "当前日期"
		String content[][] = new String[list.size()][3];
		for (int i = 0; i < list.size(); i++) {
			content[i] = new String[headers.length];
			content[i][0] = list.get(i).get("username").toString();
			content[i][1] = list.get(i).get("uphone").toString();
			content[i][2] = list.get(i).get("signstatus").toString();
			//get(i)和get("key") 必须存在，若不存在即为null，会报NullPointerException
			Object signintime = list.get(i).get("signintime") == null ? "" : list.get(i).get("signintime");
			content[i][3] = signintime.toString();
			content[i][4] = list.get(i).get("signout").toString();
			//get(i)和get("key") 必须存在，若不存在即为null，会报NullPointerException
			Object signouttime = list.get(i).get("signouttime") == null ? "" : list.get(i).get("signouttime");
			content[i][5] = signouttime.toString();
			content[i][6] = list.get(i).get("istime").toString();
		}
		// 创建HSSFWorkbook
		HSSFWorkbook wb = ExportExcelUtil.getHSSFWorkbook(title, headers, content);
		// 响应到客户端
		try {
			this.setResponseHeader(response, fileName);
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送响应流方法
	 * @param response
	 * @param fileName
	 */
	public void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			try {
				//将文件名转码 防止中文乱码
				fileName = new String(fileName.getBytes(), "iso-8859-1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			response.setContentType("application/octet-stream;charset=Utf-8");	//设置编码字符集
			//获取浏览器默认下载路径
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
