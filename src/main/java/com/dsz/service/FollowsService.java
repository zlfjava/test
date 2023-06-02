package com.dsz.service;

import java.util.List;
import java.util.Map;

import com.dsz.entity.Follows;
import com.dsz.util.FenyeUtil;

/**
 *跟踪记录业务层
 */
public interface FollowsService {

	/**
	 * 添加跟踪记录
	 * @param foll 接收参数的实体类
	 * @return 新增成功|失败的状态码以及提示消息
	 */
	Map<String, Object> add(Follows foll);

	/**
	 * 获取所有日志
	 * @param fy
	 * @return Layui分页工具类
	 */
	FenyeUtil<Follows> getAllFollows(FenyeUtil<Follows> fy);

	/**
	 * 修改跟踪记录
	 * @param foll 接收参数的实体类
	 * @return 修改成功|失败的状态码以及提示消息
	 */
	Map<String, Object> edit(Follows foll);

	/**
	 * 删除跟踪记录
	 * @param fid list类型的参数
	 * @return 数据库受影响行数
	 */
	Integer del(List<String> fid);

	/**
	 * 获取所有日志（网络跟踪）
	 * @param fy
	 * @return
	 */
	FenyeUtil<Follows> getAllFollowsWlgz(FenyeUtil<Follows> fy);

}
