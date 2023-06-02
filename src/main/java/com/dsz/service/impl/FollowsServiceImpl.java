package com.dsz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsz.entity.Follows;
import com.dsz.mapper.FollowsMapper;
import com.dsz.service.FollowsService;
import com.dsz.util.FenyeUtil;

/**
 * 跟踪记录业务实现层
 */
@Transactional	//开启事务
@Service	//业务层标识 将此类交给Spring管理
public class FollowsServiceImpl implements FollowsService {
	
	//注入跟踪记录持久层
	@Autowired
	private FollowsMapper follMapper;

	/**
	 * 添加跟踪记录
	 */
	@Override
	public Map<String, Object> add(Follows foll) {
		Map<String, Object> map = new HashMap<>();	//存储返回值
		String fid = UUID.randomUUID().toString(); // 手动生成uuid
		foll.setFid(fid);	//设置uuid
		int num = follMapper.add(foll);	//执行添加操作
		if (num > 0) {
			map.put("code", num);	//返回状态码
			map.put("msg", "跟踪成功");		//提示消息
		} else {
			map.put("code", num);
			map.put("msg", "跟踪失败");
		}
		return map;
	}

	/**
	 * 获取所有日志
	 */
	@Override
	public FenyeUtil<Follows> getAllFollows(FenyeUtil<Follows> fy) {
		List<Follows> data = follMapper.getAll(fy);	//获取所有数据
		Integer count = follMapper.getCount(fy);	//获取数据总条数
		if (data.size() > 0) {
			fy.setCode(0);
			fy.setData(data);
			fy.setCount(count);
		} else {
			fy.setCode(1);
			fy.setMsg("未找到有效信息");
		}
		return fy;
	}

	/**
	 * 修改跟踪记录
	 */
	@Override
	public Map<String, Object> edit(Follows foll) {
		Map<String, Object> map = new HashMap<>();
		int num = follMapper.edit(foll);	//修改操作
		if (num > 0) {
			map.put("code", 1);
			map.put("msg", "修改成功");
		} else {
			map.put("code", 0);
			map.put("msg", "修改失败");
		}
		return map;
	}

	/**
	 * 删除跟踪记录
	 */
	@Override
	public Integer del(List<String> fid) {
		return follMapper.del(fid);
	}

	/**
	 * 获取所有日志（网络跟踪）
	 */
	@Override
	public FenyeUtil<Follows> getAllFollowsWlgz(FenyeUtil<Follows> fy) {
		List<Follows> data = follMapper.getWlgz(fy);
		Integer count = follMapper.getWlgzNum(fy);
		if (data.size() > 0) {
			fy.setCode(0);
			fy.setData(data);
			fy.setCount(count);
		} else {
			fy.setCode(1);
			fy.setMsg("未找到有效信息");
		}
		return fy;
	}


}
