package com.wxsoft.util.wx;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.wxsoft.business.controller.BaseController;
import com.wxsoft.business.pojo.DrugStore;
import com.wxsoft.business.pojo.Orderdetail;
import com.wxsoft.business.pojo.User;
import com.wxsoft.business.service.IDrugStoreService;
import com.wxsoft.business.service.IOrderdetailService;
import com.wxsoft.business.service.IUserService;
import com.wxsoft.business.wechat.model.resp.TextMessage;
import com.wxsoft.util.StoreUtil;

/**
 * 核心服务类
 * 
 */
public class CoreService  extends BaseController{
	private final Logger log = Logger.getLogger(this.getClass());
	@Resource
	private IUserService userService;
	@Resource
	private IDrugStoreService service;

	@Resource
	private IOrderdetailService orderDetailservice;
	// public static final ActionService actionservice =
	// ActionService.getInstance();

	/**
	 * 处理微信发来的请求
	 * http://mp.weixin.qq.com/wiki/17/f298879f8fb29ab98b2f2971d42552fd.html
	 * @param request
	 * @return
	 */
	public String processRequest(HttpServletRequest request) {
		log.info("———————/weixincore/queryData—POST请求-请求获取数据。-processRequest-开始-");	
		String respMessage = "";
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			log.info("———————/weixincore/queryData—POST请求-请求获取数据。-processRequest-开始-发送方帐号（open_id）fromUserName-"+fromUserName+"--消息类型msgType--"+msgType+"--公众帐号toUserName-"+toUserName+"---------------");	
			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				//消息内容
				String keyword = requestMap.get("Content");
				// 用户openid
				String openid = requestMap.get("FromUserName");
				// TODO 根据用户openid查询是否已经绑定如果没有绑定则提示用户绑定，已经绑定返回用户需要查询的信息
				User user = userService.getUserByOpenId(openid);
				if(user!=null){
					DrugStore drugStore = new DrugStore();
					if(user.getPharmacy()!=null&&!"".equals(user.getPharmacy())){
						drugStore = service.getDrugStoreByShotN(user.getPharmacy());
					}
					Orderdetail orderdetail = new Orderdetail();
					if(drugStore!=null&&drugStore.getShortname()!=null&&!"".equals(drugStore.getShortname())){
						orderdetail.setDrugStoreShortName(drugStore.getShortname()+"_");
					}
					long moneyCount = orderDetailservice.findMoneyCount(orderdetail);
					textMessage.setContent("您今天的总销售额是" +moneyCount+
							"元。");
				}else{
					textMessage.setContent("您还没有绑定信息不能查询，点击登录绑定信息" +
							"<a href=\"\">http://112.74.214.177/shop/weixincore/login.html?openid="
							+ openid + "</a>");
				}

				// 格式化成xml
				respMessage = MessageUtil.textMessageToXml(textMessage);
				return respMessage;

			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					// 用户openid
					String openid = requestMap.get("FromUserName");
					// TODO 返回带用户信息的连接
					User user = userService.getUserByOpenId(openid);
					if(user!=null){
						DrugStore drugStore = new DrugStore();
						if(user.getPharmacy()!=null&&!"".equals(user.getPharmacy())){
							drugStore = service.getDrugStoreByShotN(user.getPharmacy());
						}
						Orderdetail orderdetail = new Orderdetail();
						if(drugStore!=null&&drugStore.getShortname()!=null&&!"".equals(drugStore.getShortname())){
							orderdetail.setDrugStoreShortName(drugStore.getShortname()+"_");
						}
						long moneyCount = orderDetailservice.findMoneyCount(orderdetail);
						textMessage.setContent("您今天的总销售额是" +moneyCount+
								"元。");
					}else{
						textMessage.setContent("您还没有绑定信息不能查询，点击登录绑定信息" +
								"<a href=\"\">http://112.74.214.177/shop/weixincore/login.html?openid="
								+ openid + "</a>");
					}
					log.info("———————/weixincore/queryData—POST请求-请求获取数据。-processRequest-结束11-xml-");
					// 格式化成xml
					respMessage = MessageUtil.textMessageToXml(textMessage);

					return respMessage;

				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}

			}
			textMessage.setContent(respContent);
			log.info("———————/weixincore/queryData—POST请求-请求获取数据。-processRequest-结束22-xml-");
			// 格式化成xml
			respMessage = MessageUtil.textMessageToXml(textMessage);
			log.info("———————/weixincore/queryData—POST请求-请求获取数据。-processRequest-结束22-respMessage-"+respMessage);	

		} catch (Exception e) {
			log.info("———————/weixincore/queryData—POST请求-请求获取数据。-processRequest-开始-Exception-"+e);	
			e.printStackTrace();
			
		}

		return respMessage;
	}

	public static void main(String a[]) {

	}

}
