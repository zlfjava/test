package com.dsz.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.dsz.entity.Students;
import com.dsz.entity.Users;
import com.dsz.mapper.StudentsMapper;
import com.dsz.mapper.UsersMapper;
import com.dsz.service.StudentsService;
import com.dsz.util.ExportBeanExcel;
import com.dsz.util.ExportExcelUtil;
import com.dsz.util.FenyeUtil;
import com.dsz.util.GetDateUtil;

/**
 * 学生业务实现层
 */
@Service
@Transactional
public class StudentsServiceImpl implements StudentsService {

	// 注入学生持久层
	@Autowired
	private StudentsMapper stuMapper;

	// 注入用户持久层
	@Autowired
	private UsersMapper userMapper;

	/**
	 * 多条件分页展示
	 */
	@Override
	public FenyeUtil<Students> getAllstu(FenyeUtil<Students> fy) {
		List<Students> data = stuMapper.getAll(fy);
		Integer count = stuMapper.getCount(fy);
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
	 * 查询当前网络咨询师录入的学生所属咨询师咨询师 String uid ： 网络咨询师id
	 */
	@Override
	public List<Students> getUsername(String uid) {
		return stuMapper.getUsername(uid);
	}

	/**
	 * 新增学生
	 */
	@Override
	public Map<String, Object> addStu(Students student) {
		Map<String, Object> map = new HashMap<String, Object>();
		String sid = UUID.randomUUID().toString(); // 手动生成uuid
		GetDateUtil date = new GetDateUtil(); // 实例化日期工具类
		String screatetime = date.getTime();
		student.setSid(sid);
		student.setScreatetime(screatetime);
		student.setIfvalid("是");
		int num = stuMapper.addStu(student);
		String uid = student.getUid();
		if (num > 0) {
			map.put("code", 1); // 状态码
			map.put("msg", "录入成功");
			try {
				Users user = userMapper.getAllWlzxsFenpei(uid);// ？
				if ("是".equals(user.getUifweight())) {
					String time = date.getRiQi();
					Users use = userMapper.getZxsSj(time);// 随机查询一个拥有咨询师角色的用户
					System.out.println("这是咨询师" + use);
					stuMapper.editStuTest(use.getLoginname(), use.getProtectmtel(), sid);
					System.out.println("成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			map.put("code", 0); // 状态码
			map.put("msg", "录入失败");
		}
		return map; // 返回map集合
	}

	/**
	 * 修改学生信息
	 */
	@Override
	public Map<String, Object> editStu(Students stu) {
		Map<String, Object> map = new HashMap<>(); // 存储返回值
		String phone = stu.getUphone();
		// 根据手机号查找姓名
		Users sname = userMapper.Protectmtel(phone);
		stu.setSzxsid(sname.getLoginname());
		int num = stuMapper.editStu(stu); // 执行修改操作
		if (num > 0) {
			map.put("code", num);
			map.put("msg", "信息编辑成功");
		} else {
			map.put("code", num);
			map.put("msg", "信息编辑失败");
		}
		return map;
	}

	/**
	 * 新生报到页面导出excel
	 */
	@Override
	public void xsbdExportStudents(HttpServletRequest request, HttpServletResponse response, String ids) {
		String title = "学生信息表"; // excel sheeet标题
		String[] headers = { "学生名", "年龄", "性别", "手机号", "来源渠道", "来源关键词", "学历", "QQ号", "状态", "微信号", "来源网址", "创建时间", "备注",
				"所属咨询师", "咨询师手机号" }; // excel表格的列名
		String fileName = "学生信息" + System.currentTimeMillis() + ".xlsx"; // excel文件名以及后缀
		String[] arr = ids.split(","); // String转String数组
		List<Map<String, Object>> list = new ArrayList<>(); // List of Teacher Type
		HashSet<String> hs = new HashSet<String>(); // HashSet集合去除重复id
		for (int i = 0; i < arr.length; i++) {
			String id = arr[i]; // String转Integer（如果要Integer类型的要转，这里并没有转换）
			hs.add(id); // 数组元素添加到HashSet去重复
		}
		for (String id : hs) {
			Students stu = stuMapper.getOneStudents(id); // 根据学生id查询每循环一次就查询一条
			Map<String, Object> map = new HashMap<String, Object>(); // 存数据
			map.put("sname", stu.getSname());
			map.put("sage", stu.getSage());
			map.put("sgender", stu.getSgender());
			map.put("sphone", stu.getSphone());
			map.put("sourcechannel", stu.getSourcechannel());
			map.put("sourceword", stu.getSourceword());
			map.put("seducation", stu.getSeducation());
			map.put("sqq", stu.getSqq());
			map.put("sstatus", stu.getSstatus());
			map.put("wechatid", stu.getWechatid());
			map.put("sourceurl", stu.getSourceurl());
			map.put("screatetime", stu.getScreatetime());
			map.put("sremark", stu.getSremark());
			map.put("szxsid", stu.getSzxsid());
			map.put("uphone", stu.getUphone());
			list.add(map);
		}
		String content[][] = new String[list.size()][3];
		for (int i = 0; i < list.size(); i++) {
			content[i] = new String[headers.length];
			content[i][0] = list.get(i).get("sname").toString();
			content[i][1] = list.get(i).get("sage").toString();
			content[i][2] = list.get(i).get("sgender").toString();
			Object sphone = list.get(i).get("sphone") == null ? "" : list.get(i).get("sphone");
			content[i][3] = sphone.toString();
			Object sourcechannel = list.get(i).get("sourcechannel") == null ? "" : list.get(i).get("sourcechannel");
			content[i][4] = sourcechannel.toString();
			Object sourceword = list.get(i).get("sourceword") == null ? "" : list.get(i).get("sourceword");
			content[i][5] = sourceword.toString();
			Object seducation = list.get(i).get("seducation") == null ? "" : list.get(i).get("seducation");
			content[i][6] = seducation.toString();
			Object sqq = list.get(i).get("sqq") == null ? "" : list.get(i).get("sqq");
			content[i][7] = sqq.toString();
			Object sstatus = list.get(i).get("sstatus") == null ? "" : list.get(i).get("sstatus");
			content[i][8] = sstatus.toString();
			Object wechatid = list.get(i).get("wechatid") == null ? "" : list.get(i).get("wechatid");
			content[i][9] = wechatid.toString();
			Object sourceurl = list.get(i).get("sourceurl") == null ? "" : list.get(i).get("sourceurl");
			content[i][10] = sourceurl.toString();
			content[i][11] = list.get(i).get("screatetime").toString();
			Object sremark = list.get(i).get("sremark") == null ? "" : list.get(i).get("sremark");
			content[i][12] = sremark.toString();
			content[i][13] = list.get(i).get("szxsid").toString();
			// get(i)和get("key") 必须存在，若不存在即为null，会报NullPointerException
			Object uphone = list.get(i).get("uphone") == null ? "" : list.get(i).get("uphone");
			content[i][14] = uphone.toString();
		}
		HSSFWorkbook wb = ExportExcelUtil.getHSSFWorkbook(title, headers, content); // 创建HSSFWorkbook
		try {
			this.setResponseHeader(response, fileName); // 响应到客户端
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
	 * 
	 * @param response
	 * @param fileName
	 */
	public void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			try {
				fileName = new String(fileName.getBytes(), "iso-8859-1"); // 将文件名转码 防止中文乱码
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			response.setContentType("application/octet-stream;charset=Utf-8"); // 设置编码字符集
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName); // 获取浏览器默认下载路径
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 学生信息页面的文件导出请求
	 */
	@Override
	public void xsxxExportStudents(HttpServletRequest request, HttpServletResponse response, String ids) {
		String title = "学生信息表"; // excel sheeet标题
		String[] headers = { "学生名", "年龄", "性别", "手机号", "来源渠道", "来源关键词", "学历", "QQ号", "状态", "微信号", "来源网址", "创建时间", "备注",
				"课程方向", "打分情况", "是否有效", "无效原因", "是否回访", "首访时间", "是否上门", "上门时间", "定金金额", "定金时间", "是否缴费", "缴费时间", "缴费金额",
				"是否退费", "退费原因", "退费时间", "是否进班", "进班时间", "进班备注", "咨询师备注", "录入人员", "所属咨询师", "咨询师手机号" }; // excel表格的列名
		String fileName = "学生信息" + System.currentTimeMillis() + ".xlsx"; // excel文件名以及后缀
		String[] arr = ids.split(","); // String转String数组
		List<Map<String, Object>> list = new ArrayList<>(); // List of Teacher Type
		HashSet<String> hs = new HashSet<String>(); // HashSet集合去除重复id
		for (int i = 0; i < arr.length; i++) {
			String id = arr[i]; // String转Integer（如果要Integer类型的要转，这里并没有转换）
			hs.add(id); // 数组元素添加到HashSet去重复
		}
		for (String id : hs) {
			Students stu = stuMapper.getOneStudents(id); // 根据学生id查询 每循环一次就查询一条
			Map<String, Object> map = new HashMap<String, Object>(); // 存数据
			map.put("sname", stu.getSname());
			map.put("sage", stu.getSage());
			map.put("sgender", stu.getSgender());
			map.put("sphone", stu.getSphone());
			map.put("sourcechannel", stu.getSourcechannel());
			map.put("sourceword", stu.getSourceword());
			map.put("seducation", stu.getSeducation());
			map.put("sqq", stu.getSqq());
			map.put("sstatus", stu.getSstatus());
			map.put("wechatid", stu.getWechatid());
			map.put("sourceurl", stu.getSourceurl());
			map.put("screatetime", stu.getScreatetime());
			map.put("sremark", stu.getSremark());
			map.put("course", stu.getCourse());
			map.put("gradecase", stu.getGradecase());
			map.put("iseffect", stu.getIseffect());
			map.put("invalidcause", stu.getInvalidcause());
			map.put("isfirstvisit", stu.getIsfirstvisit());
			map.put("firstvisittime", stu.getFirstvisittime());
			map.put("isvisit", stu.getIsvisit());
			map.put("visittime", stu.getVisittime());
			map.put("handselmoney", stu.getHandselmoney());
			map.put("handsetime", stu.getHandsetime());
			map.put("paythefees", stu.getPaythefees());
			map.put("paythefeestime", stu.getPaythefeestime());
			map.put("paymentamount", stu.getPaymentamount());
			map.put("isoutpay", stu.getIsoutpay());
			map.put("isoutpaycause", stu.getIsoutpaycause());
			map.put("isoutpaytime", stu.getIsoutpaytime());
			map.put("intheclass", stu.getIntheclass());
			map.put("intheclasstime", stu.getIntheclasstime());
			map.put("intheclassremark", stu.getIntheclassremark());
			map.put("rremark", stu.getRremark());
			map.put("loginname", stu.getUser().getLoginname());
			map.put("szxsid", stu.getSzxsid());
			map.put("uphone", stu.getUphone());
			list.add(map);
		}
		String content[][] = new String[list.size()][3];
		for (int i = 0; i < list.size(); i++) {
			content[i] = new String[headers.length];
			content[i][0] = list.get(i).get("sname").toString();
			content[i][1] = list.get(i).get("sage").toString();
			content[i][2] = list.get(i).get("sgender").toString();
			Object sphone = list.get(i).get("sphone") == null ? "" : list.get(i).get("sphone");
			content[i][3] = sphone.toString();
			Object sourcechannel = list.get(i).get("sourcechannel") == null ? "" : list.get(i).get("sourcechannel");
			content[i][4] = sourcechannel.toString();
			Object sourceword = list.get(i).get("sourceword") == null ? "" : list.get(i).get("sourceword");
			content[i][5] = sourceword.toString();
			Object seducation = list.get(i).get("seducation") == null ? "" : list.get(i).get("seducation");
			content[i][6] = seducation.toString();
			Object sqq = list.get(i).get("sqq") == null ? "" : list.get(i).get("sqq");
			content[i][7] = sqq.toString();
			Object sstatus = list.get(i).get("sstatus") == null ? "" : list.get(i).get("sstatus");
			content[i][8] = sstatus.toString();
			Object wechatid = list.get(i).get("wechatid") == null ? "" : list.get(i).get("wechatid");
			content[i][9] = wechatid.toString();
			Object sourceurl = list.get(i).get("sourceurl") == null ? "" : list.get(i).get("sourceurl");
			content[i][10] = sourceurl.toString();
			content[i][11] = list.get(i).get("screatetime").toString();
			Object sremark = list.get(i).get("sremark") == null ? "" : list.get(i).get("sremark");
			content[i][12] = sremark.toString();
			Object course = list.get(i).get("course") == null ? "" : list.get(i).get("course");
			content[i][13] = course.toString();
			Object gradecase = list.get(i).get("gradecase") == null ? "" : list.get(i).get("gradecase");
			content[i][14] = gradecase.toString();
			Object iseffect = list.get(i).get("iseffect") == null ? "" : list.get(i).get("iseffect");
			content[i][15] = iseffect.toString();
			Object invalidcause = list.get(i).get("invalidcause") == null ? "" : list.get(i).get("invalidcause");
			content[i][16] = invalidcause.toString();
			Object isfirstvisit = list.get(i).get("isfirstvisit") == null ? "" : list.get(i).get("isfirstvisit");
			content[i][17] = isfirstvisit.toString();
			Object firstvisittime = list.get(i).get("firstvisittime") == null ? "" : list.get(i).get("firstvisittime");
			content[i][18] = firstvisittime.toString();
			Object isvisit = list.get(i).get("isvisit") == null ? "" : list.get(i).get("isvisit");
			content[i][19] = isvisit.toString();
			Object visittime = list.get(i).get("visittime") == null ? "" : list.get(i).get("visittime");
			content[i][20] = visittime.toString();
			Object handselmoney = list.get(i).get("handselmoney") == null ? "" : list.get(i).get("handselmoney");
			content[i][21] = handselmoney.toString();
			Object handsetime = list.get(i).get("handsetime") == null ? "" : list.get(i).get("handsetime");
			content[i][22] = handsetime.toString();
			Object paythefees = list.get(i).get("paythefees") == null ? "" : list.get(i).get("paythefees");
			content[i][23] = paythefees.toString();
			Object paythefeestime = list.get(i).get("paythefeestime") == null ? "" : list.get(i).get("paythefeestime");
			content[i][24] = paythefeestime.toString();
			Object paymentamount = list.get(i).get("paymentamount") == null ? "" : list.get(i).get("paymentamount");
			content[i][25] = paymentamount.toString();
			Object isoutpay = list.get(i).get("isoutpay") == null ? "" : list.get(i).get("isoutpay");
			content[i][26] = isoutpay.toString();
			Object isoutpaycause = list.get(i).get("isoutpaycause") == null ? "" : list.get(i).get("isoutpaycause");
			content[i][27] = isoutpaycause.toString();
			Object isoutpaytime = list.get(i).get("isoutpaytime") == null ? "" : list.get(i).get("isoutpaytime");
			content[i][28] = isoutpaytime.toString();
			Object intheclass = list.get(i).get("intheclass") == null ? "" : list.get(i).get("intheclass");
			content[i][29] = intheclass.toString();
			Object intheclasstime = list.get(i).get("intheclasstime") == null ? "" : list.get(i).get("intheclasstime");
			content[i][30] = intheclasstime.toString();
			Object intheclassremark = list.get(i).get("intheclassremark") == null ? ""
					: list.get(i).get("intheclassremark");
			content[i][31] = intheclassremark.toString();
			Object rremark = list.get(i).get("rremark") == null ? "" : list.get(i).get("rremark");
			content[i][32] = rremark.toString();
			Object loginname = list.get(i).get("loginname") == null ? "" : list.get(i).get("loginname");
			content[i][33] = loginname.toString();
			Object szxsid = list.get(i).get("szxsid") == null ? "" : list.get(i).get("szxsid");
			content[i][34] = szxsid.toString();
			Object uphone = list.get(i).get("uphone") == null ? "" : list.get(i).get("uphone");
			content[i][35] = uphone.toString();
		}
		HSSFWorkbook wb = ExportExcelUtil.getHSSFWorkbook(title, headers, content); // 创建HSSFWorkbook
		try {
			this.setResponseHeader(response, fileName); // 响应到客户端
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询未分配的学生
	 */
	@Override
	public FenyeUtil<Students> getAllWfpstu(FenyeUtil<Students> fy) {
		List<Students> data = stuMapper.getAllWfp(fy);
		Integer count = stuMapper.getCountWfp(fy);
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
	 * 修改未分配的学生到指定的咨询师下 单独修改
	 */
	@Override
	public Map<String, Object> editStuZxs(Students stu) {
		Map<String, Object> map = new HashMap<String, Object>();
		String phone = stu.getUphone();
		// 根据手机号查找姓名
		Users sname = userMapper.Protectmtel(phone);
		stu.setSzxsid(sname.getLoginname());
		int num = stuMapper.editStu(stu); // 执行修改操作
		if (num > 0) {
			map.put("code", num);
			map.put("msg", "分配成功");
		} else {
			map.put("code", num);
			map.put("msg", "分配失败");
		}
		return map;
	}

	/**
	 * 修改未分配的学生到指定的咨询师下 批量修改
	 */
	@Override
	public Map<String, Object> editStuZxsD(String uphone, List<String> ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 根据手机号查找姓名
		Users sname = userMapper.Protectmtel(uphone);
		String szxsid = sname.getLoginname();
		int num = stuMapper.editStuPl(szxsid, uphone, ids); // 执行修改操作
		if (num > 0) {
			map.put("code", num);
			map.put("msg", "分配成功");
		} else {
			map.put("code", num);
			map.put("msg", "分配失败");
		}
		return map;
	}

	/**
	 * 删除学生信息 软删除 删除后进入回收站 也可以还原
	 */
	@Override
	public Integer mildDel(List<String> sid, String ifvalid) {
		return stuMapper.mildDel(sid, ifvalid);
	}

	/**
	 * 删除学生信息 真删除 将会从数据库移除数据 不可还原
	 */
	@Override
	public Integer perpetuaDel(List<String> sid) {
		return stuMapper.perpetuaDel(sid);
	}

	/**
	 * 自动分配
	 */
	@Override
	public Integer testFenpei() {
		List<Students> stu = stuMapper.getAllWfpSy(); // 查询所有未分配的学生
		int stuNum = stu.size(); // 获取未分配学生的人数
		GetDateUtil date = new GetDateUtil();
		String time = date.getRiQi(); // 获取服务器当前时间
		List<Users> us = userMapper.getAllZxsname(time); // 查询所有已签到未签退的咨询师
		if (us.size() < 1) { // 说明没有咨询师在线
			return 3; // 返回状态 3代表没有在线的咨询师
		}
		int userNum = us.size(); // 获取所有已签到未签退的咨询师人数
		List<Integer> uweight = new ArrayList<Integer>(); // 存储咨询师的权重
		List<Integer> xsNum = new ArrayList<Integer>(); // 存储学生咨询师下的学生数量
		for (int i = 0; i < userNum; i++) {
			uweight.add(us.get(i).getUweight());
			xsNum.add(userMapper.getCountByProtectmtel(us.get(i).getProtectmtel()));
		}
		try {
			int s = 0; // 代表学生下标
			int u = 0; // 代表咨询师下标
			if (stuNum == 1) {
				String sid = "";
				for (int i = 0; i < stu.size(); i++) {
					sid = stu.get(i).getSid();
				}
				try {
					Users use = userMapper.getZxsSj(time);// 随机查询一个拥有咨询师角色的用户
					stuMapper.editStuTest(use.getLoginname(), use.getProtectmtel(), sid);
					System.out.println("成功");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				while (stuNum > 0) { // 循环
					// 自动分配的方法（修改）
					stuMapper.editStuTest(us.get(u).getLoginname(), us.get(u).getProtectmtel(), stu.get(s).getSid());
					s++; // 下标++
					u++; // 下标++
					stuNum--; // 未分配学生人数--
					if (u == userNum) { // 如果下标==咨询师人数 说明已经分配了一轮
						u = 0; // 重置下标
						continue; // 跳过本次循环
					}
				}
			}
			return 1; // 分配成功后的返回值
		} catch (Exception e) {
			e.printStackTrace(); // 打印堆栈跟踪
			return 0; // 分配失败后的返回值
		}
	}

	/**
	 * 导入excel文件
	 */
	@Override
	public Object importDept(MultipartFile file, String uid) {
		Map<String, Object> params = new HashedMap<>(); // 返回值
		// 读取excel表格
		try {
			List<List<String>> lists = ExportBeanExcel.readExcel(file.getInputStream());
			System.out.println("读取到的数据：" + lists);
			// 判断集合是否为空
			if (!CollectionUtils.isEmpty(lists)) {
				for (int i = 1; i < lists.size(); i++) {
					List<String> list = lists.get(i);
					String sid = UUID.randomUUID().toString(); // 手动生成uuid
					String szxsid = "未分配"; // 默认没有分配咨询师
					String uphone = null; // 没分配咨询师也就没有咨询师手机号
					GetDateUtil date = new GetDateUtil(); // 实例化日期时间工具类
					String screatetime = date.getTime(); // 获取服务器时间
					String sname = list.get(0);
					System.out.println("姓名：" + sname);
					Double tempSage = Double.parseDouble(list.get(1));
					Integer sage = new Double(tempSage).intValue();
					System.out.println("年龄：" + sage);
					String sgender = list.get(2);
					System.out.println("性别：" + sgender);
					String sphone = list.get(3);
					System.out.println("手机号：" + sphone);
					String sourcechannel = list.get(4);
					System.out.println("来源渠道：" + sourcechannel);
					String sourceword = list.get(5);
					System.out.println("来源关键词：" + sourceword);
					String seducation = list.get(6);
					System.out.println("学历：" + seducation);
					String sqq = list.get(7);
					System.out.println("QQ号：" + sqq);
					String sstatus = list.get(8);
					System.out.println("状态：" + sstatus);
					String wechatid = list.get(9);
					System.out.println("微信号：" + wechatid);
					String sourceurl = list.get(10);
					System.out.println("来源网址：" + sourceurl);
					String sremark = list.get(11);
					System.out.println("备注：" + seducation);
					String ifvalid = "是";
					Students stu = new Students(sid, uid, szxsid, uphone, sname, sphone, sage, sgender, sourcechannel,
							sourceword, seducation, sqq, sstatus, wechatid, sourceurl, null, screatetime, sremark, null,
							null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
							null, null, null, null, ifvalid, null, null);
					try {
						stuMapper.addStu(stu);
						params.put("num", "导入成功");
					} catch (Exception e) {
						e.printStackTrace();
						params.put("num", "导入失败");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return params;
	}

	/**
	 * 查询咨询师下有多少学生
	 */
	@Override
	public Integer getCountzxsNum(String uphone) {
		return stuMapper.getCountzxsNum(uphone);
	}

	/**
	 * 获取所有学生以及学生的跟踪记录
	 */
	@Override
	public FenyeUtil<Students> getAllFollowsByStu(FenyeUtil<Students> fy) {
		List<Students> data = stuMapper.getAllFollowsByStuData(fy);
		Integer count = stuMapper.getAllFollowsByStuCount(fy);
		if (data.size() > 0) {
			fy.setCode(0);
			fy.setData(data);
			fy.setCount(count);
		} else {
			fy.setCode(1);
			fy.setMsg("未找到有效数据");
		}
		return fy;
	}

	/**
	 * 获取全部学生信息（用于网络跟踪页面的下拉单展示数据）
	 */
	@Override
	public List<Students> getAllStuName(String uphone) {
		return stuMapper.getAllStuName(uphone);
	}

}
