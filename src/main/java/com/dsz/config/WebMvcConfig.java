package com.dsz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dsz.interceptor.LoginInterceptor;

/**
 * 拦截配置类
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	//注入拦截器类
	@Autowired
    private LoginInterceptor loginInterceptor;
	
	//重写接口里面的方法
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login","/users/findByLoginname","/users/Protectmtel",
				"/users/findByYzmpwd","/users/findByYanzm","/users/setPwd");
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET","POST","PUT","DELETE");
	}
	
}
