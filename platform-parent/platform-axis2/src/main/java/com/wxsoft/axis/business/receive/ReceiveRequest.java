package com.wxsoft.axis.business.receive;

import com.wxsoft.axis.core.util.LogUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReceiveRequest {
	
	public String receive(String request) {
		Long time = new Date().getTime();
		LogUtil.info("收到消息(" + time + ")：" + request);
		String result = "我收到了您的消息，内容是：" + request;
		LogUtil.info("响应消息(" + time + ")：" + result);
		return result;
	}
}
