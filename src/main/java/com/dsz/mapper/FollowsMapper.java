package com.dsz.mapper;

import java.util.List;

import com.dsz.entity.Follows;
import com.dsz.util.FenyeUtil;

/**
 * 跟踪记录持久层
 */
public interface FollowsMapper {
	
	/**
	 * 添加跟踪记录
	 * @param foll
	 * @return
	 */
	int add(Follows foll);

	/**
	 * 获取所有数据
	 * @param fy
	 * @return
	 */
	List<Follows> getAll(FenyeUtil<Follows> fy);

	/**
	 * 获取数据总条数
	 * @param fy
	 * @return
	 */
	Integer getCount(FenyeUtil<Follows> fy);
	
	/**
	 * 修改跟踪记录
	 * @param foll 接收参数的实体类
	 * @return 修改成功|失败的状态码以及提示消息
	 */
	int edit(Follows foll);

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
	List<Follows> getWlgz(FenyeUtil<Follows> fy);

	/**
	 * 获取所有日志（网络跟踪）总条数
	 * @param fy
	 * @return
	 */
	Integer getWlgzNum(FenyeUtil<Follows> fy);

}
