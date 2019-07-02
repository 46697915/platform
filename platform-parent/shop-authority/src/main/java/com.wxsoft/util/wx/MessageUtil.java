package com.wxsoft.util.wx;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.wxsoft.business.controller.BaseController;
import com.wxsoft.business.wechat.model.resp.TextMessage;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 
 



public class MessageUtil  extends BaseController{
	private static final Logger log = Logger.getLogger(MessageUtil.class);
	/**
	 * 返回消息类型：文本
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 返回消息类型：音乐
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

	/**
	 * 返回消息类型：图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * 请求消息类型：链接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	/**
	 * 请求消息类型：地理位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

	/**
	 * 请求消息类型：音频
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

	/**
	 * 请求消息类型：推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	/**
	 * 事件类型：subscribe(订阅)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";
	
	/**
	 * 事件类型：CLICK(微答人自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_WEIDAREN = "V1001_TODAY_WDREN";
	
	/**
	 * 事件类型：CLICK(分类资讯自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_CLASSINFO = "V1001_TODAY_CLASSINFO";
	
	/**
	 * 事件类型：CLICK(我要分享自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_MYSHARE = "V1001_TODAY_MYSHARE";		
	
	/**
	 * 事件类型：CLICK(大家都在转自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_PEOPLEPLAY = "V1001_TODAY_PEOPLEPLAY";	
	
	/**
	 * 事件类型：CLICK(我的信息自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_MYINFO = "V1001_MYINFO";	
	
	/**
	 * 事件类型：CLICK(我的收藏自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_MYMARK = "V1001_MYMARK";	
	
	/**
	 * 第三方用户唯一凭证
	 */
	public static final String APPID ="wx074279480b711554";
	
	
	/**
	 * 第三方用户唯一凭证密钥，既appsecret
	 */
	public static final String APPSECRET="c643efdb4df8fc482cff1ec99f544a16";

	/**
	 * 解析微信发来的请求（XML）
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		log.info("———————/weixincore/queryData—POST请求-请求获取数据。-parseXml-开始-");	
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();

		// 从request中取得输入流
		InputStream inputStream = request.getInputStream();
		log.info("———————/weixincore/queryData—POST请求-请求获取数据。-parseXml-开始-inputStream-"+inputStream);	
		
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		log.info("———————/weixincore/queryData—POST请求-请求获取数据。-parseXml-开始-document-"+document);	
		
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();

		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getText());

		// 释放资源
		inputStream.close();
		inputStream = null;

		return map;
	}

	/**
	 * 文本消息对象转换成xml
	 * 
	 * @param textMessage 文本消息对象
	 * @return xml
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

 
 

	/**
	 * 扩展xstream，使其支持CDATA块
	 * 
	 * @date 2013-05-19
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("unchecked")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});
}
