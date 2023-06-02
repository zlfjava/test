package com.dsz.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dsz.entity.Users;
import com.dsz.mapper.UsersMapper;

@Component	//将类交给Spring管理
@EnableScheduling
public class ResetSign {
	
	//注入用户业务层
	@Autowired
	private UsersMapper userMapper;
	
	//任务调度     用于重置打卡状态   每天凌晨执行一次
//	@Scheduled(cron = "*/5 * * * * ? ")	//5秒执行一次     用于测试
	@Scheduled(cron = "0 0 0 * * ?")	//每天24点整执行
	public void reportCurrentByCron() {
		Users user = new Users();
		user.setIssign("否");
		userMapper.updateUsers(user);	//重置打卡状态为否
		System.out.println("每天24点整执行一次重置打卡状态为否");
	}
	
}
