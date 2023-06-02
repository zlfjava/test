package com.dsz.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取日期的工具类
 */
public class GetDateUtil {

	//日期对象
	private Calendar now = Calendar.getInstance();  
	
	//日期
	private Date d = new Date();
	
	//获取当前日期  年月日时分秒
	public String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}
	
	//获取年月日   不带时分秒
	public String getRiQi() {
		String str = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
		return str;
	}
	
    //获取当前年份
    public String getYear() {
    	int year = now.get(Calendar.YEAR);
        return String.valueOf(year);
	}
    
    //获取当前月份
    public String getMonth() {
    	int month = now.get(Calendar.MONTH)  + 1;
        return String.valueOf(month);
	}
    
    //获取当前天
    public String getDay() {
    	int day = now.get(Calendar.DAY_OF_MONTH);
        return String.valueOf(day);
	}
	
}
