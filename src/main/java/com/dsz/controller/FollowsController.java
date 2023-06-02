package com.dsz.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dsz.entity.Follows;
import com.dsz.service.FollowsService;
import com.dsz.util.FenyeUtil;

/**
 * 跟踪记录控制器
 */
@RestController	//相当于@Controller + @ResponseBody的作用
@CrossOrigin	//解决跨域数据传输问题
public class FollowsController {
	
	//注入跟踪记录业务层
	@Autowired
	private FollowsService follService;
	
	/**
	 * 获取所有日志
	 * @param fy
	 * @param foll
	 * @return Layui分页工具类
	 */
	@RequestMapping(value = "/follows/getAllFollows", method = RequestMethod.POST)
	public FenyeUtil<Follows> getAllFollows(FenyeUtil<Follows> fy, Follows foll) {
		fy.setT(foll);
		return follService.getAllFollows(fy);
	}
	
	/**
	 * 添加跟踪记录
	 * @param foll 接收参数的实体类
	 * @return 新增成功|失败的状态码以及提示消息
	 */
	@RequestMapping(value = "/follows/add", method = RequestMethod.POST)
	public Map<String, Object> add(Follows foll) {
		return follService.add(foll);
	}
	
	/**
	 * 修改跟踪记录
	 * @param foll 接收参数的实体类
	 * @return 修改成功|失败的状态码以及提示消息
	 */
	@RequestMapping(value = "/follows/edit", method = RequestMethod.POST)
	public Map<String, Object> edit(Follows foll) {
		return follService.edit(foll);
	}
	
	/**
	 * 删除跟踪记录
	 * @param fid list类型的参数
	 * @return 数据库受影响行数
	 */
	@RequestMapping(value = "/follows/del", method = RequestMethod.POST)
	public Integer del(@RequestParam("fid")List<String> fid) {
		return follService.del(fid);
	}
	
	/**
	 * 获取所有日志（网络跟踪）
	 * @param fy
	 * @param foll
	 * @return Layui分页工具类
	 */
	@RequestMapping(value = "/follows/getAllFollowsWlgz", method = RequestMethod.POST)
	public FenyeUtil<Follows> getAllFollowsWlgz(FenyeUtil<Follows> fy, Follows foll) {
		fy.setT(foll);
		return follService.getAllFollowsWlgz(fy);
	}

}
