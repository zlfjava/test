package com.dsz.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dsz.entity.Users;
import com.dsz.mapper.UsersMapper;

@Component	//将类交给Spring管理
@EnableScheduling
public class ResetLock {
	
	@Autowired
	private UsersMapper userMapper;
	
	//任务调度     用于重置是否锁定   每天凌晨执行一次
//	@Scheduled(cron = "*/5 * * * * ? ")	//5秒执行一次     用于测试
	@Scheduled(cron = "0 0 0 * * ?")	//每天24点整执行
	public void reportCurrentByCron() {
		Users us = new Users();
		us.setPsdwrongtime(0); // 重新设置密码错误次数为0
		us.setLocktime(""); // 取消锁定时间
		us.setIslockout("否");
		userMapper.updateUsers(us);	//解锁
		System.out.println("每天24点整执行一次账号是否锁定为否");
	}

}
