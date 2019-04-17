package com.wxsoft.axis.client.testservice;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

public class Client {
	public String send(String request,String url) throws RemoteException,
			UnsupportedEncodingException,Exception {
		TestService sc = new TestServiceStub(url);//"http://172.20.201.235:8080/services/FSUService"
		Invoke in = new Invoke();
		org.apache.axis2.databinding.types.soapencoding.String s = new org.apache.axis2.databinding.types.soapencoding.String();
		s.setString(request);
		in.setJsonData(s);
		InvokeResponse ir = sc.invoke(in);
		String ss = ir.getInvokeReturn().getString();
		return ss;
//		byte[] b = ss.getBytes("ISO-8859-1");
//		String response = new String(b, "utf-8");
//		return response;
	}
}
