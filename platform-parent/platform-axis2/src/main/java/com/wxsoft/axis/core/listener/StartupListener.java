package com.wxsoft.axis.core.listener;

import com.wxsoft.axis.core.util.GlobalValues;
import com.wxsoft.axis.core.util.LogUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.servlet.annotation.WebListener;

@WebListener
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		LogUtil.info("系统初始化开始");
		GlobalValues.setApplicationContext(event.getApplicationContext());
		GlobalValues.enabled = true;
		LogUtil.info("系统初始化完成");
	}
	
}
