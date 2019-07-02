package com.wxsoft.business.wechat.model.req;

/**
 * 接收事件推送
 * @author xin
 *
 */
public class BaseEvent {
	//开发者微信号
	private String ToUserName;	
	//发送方帐号（一个OpenID）
	private String FromUserName;	 
	//消息创建时间 （整型）
	private String CreateTime;	
	//消息类型，event
	private String MsgType;	 
	//事件类型，subscribe(订阅)、unsubscribe(取消订阅)、scan 、LOCATION、CLICK
	private String Event;
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
}
