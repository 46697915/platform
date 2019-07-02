package com.wxsoft.axis2.client;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.log4j.Logger;

import javax.xml.namespace.QName;

public class YBJKclient {

	private static Logger logger = Logger.getLogger(YBJKclient.class);
	private static String webURL = "http://10.40.125.134:7003/yqybjk/services/YBJK";

	public static void main(String args[]) {
		System.out.println("11111111111111111");
		try {
//			getYBJSZXX("sy","1234","","67230158-6","2016-03-11","1","100");
			getYBJSMXXX("sy","1234","","67230158-6","13405422","1","100");
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取医保结算主信息
	 * @param userName
	 * @param password
	 * @param signature
	 * @param yyjgdm
	 * @param jsrq
	 * @param qqym
	 * @param myjls
	 * @return
	 * @throws AxisFault
	 */
	public static String getYBJSZXX(String userName,String  password, String signature, 
			String yyjgdm,String jsrq,String qsym,String myjls) throws AxisFault{

		String ss = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
		"<SstRequest>" +
		"	<userid>"+userName+"</userid>" +
		"	<passwd>"+password+"</passwd>" +
		"	<funid>yq_cx_jszxx_qq</funid>" +
		"	<signature>"+signature+"</signature>" +
		"<RequestSet>" +
		"	<yyjgdm>"+yyjgdm+"</yyjgdm>" +
		"   <jsrq>"+jsrq+"</jsrq>" +
		"   <qsym>"+qsym+"</qsym>" +
		"	<myjls>"+myjls+"</myjls>" +
		"</RequestSet></SstRequest>";
		
		String r = execute("cxjszxx",ss);
		logger.debug("医保返回："+r);
		//System.out.println(r);
		return r ;
	}
	/**
	 * 查询结算明细信息
	 * @param userName
	 * @param password
	 * @param signature
	 * @param yyjgdm
	 * @param ybjsh
	 * @param qsym
	 * @param myjls
	 * @return
	 * @throws AxisFault
	 */
	public static String getYBJSMXXX(String userName,String  password, String signature, 
			String yyjgdm,String ybjsh,String qsym,String myjls) throws AxisFault{

		String ss = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
		"<SstRequest>" +
		"	<userid>"+userName+"</userid>" +
		"	<passwd>"+password+"</passwd>" +
		"	<funid>yq_cx_jsmxxx_qq</funid>" +
		"	<signature>"+signature+"</signature>" +
		"<RequestSet>" +
		"	<yyjgdm>"+yyjgdm+"</yyjgdm>" +
		"   <ybjsh>"+ybjsh+"</ybjsh>" +
		"   <qsym>"+qsym+"</qsym>" +
		"	<myjls>"+myjls+"</myjls>" +
		"</RequestSet></SstRequest>";
		
		String r = execute("cxjsmxxx",ss);

		//System.out.println(r);
		return r ;
	}
	/**
	 * 执行接口方法调用
	 * @param method
	 * @param param
	 * @return
	 * @throws AxisFault
	 */
	public static String execute(String method,String param) throws AxisFault {

		// 使用RPC方式调用WebService
		RPCServiceClient serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();

		options.setTimeOutInMilliSeconds((long) 3000000);
		// 指定调用WebService的URL
//		EndpointReference targetEPR = new EndpointReference(
//				"http://127.0.0.1:8080/yqybjk/services/YBJK");
		EndpointReference targetEPR = new EndpointReference(webURL);
//		EndpointReference targetEPR = new EndpointReference(
//		"http://10.40.125.134:7002/yqybjk/services/YBJK");
		options.setTo(targetEPR);

		//6.1.3.	【查询结算主信息】请求
		QName opAddEntry = new QName("http://www.ylzinfo.com/xsd", method);
		Object[] opAddEntryArgs = new Object[] { param };

//		//6.1.4.	【查询结算明细信息】请求
//		QName opAddEntry = new QName("http://www.ylzinfo.com/xsd", "cxjsmxxx");
//		String ss = YBJKxml.getJSMXXX();
//		Object[] opAddEntryArgs = new Object[] { ss };
		

		// 指定方法返回值的数据类型的Class对象
		Class[] classes = new Class[] { String.class };
		// 指定要调用的sayHello2方法及WSDL文件的命名空间
		// QName opAddEntry = new QName("http://www.ylzinfo.com/xsd",
		// "SI010101");

		// 调用sayHello2方法并输出该方法的返回值
		Object[] r = serviceClient.invokeBlocking(opAddEntry,
				opAddEntryArgs, classes);
		serviceClient.cleanupTransport();
		return r[0].toString();
	}
}
