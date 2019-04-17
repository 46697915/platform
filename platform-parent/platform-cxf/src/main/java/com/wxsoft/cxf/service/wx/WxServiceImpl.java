package com.wxsoft.cxf.service.wx;


import javax.jws.WebService;

/**
 *  注意： 此处命名空间 和 接口 类路径必须相同！！！！！
 *  targetNamespace="http://wx.service.cxf.wxsoft.com/",
 *  endpointInterface = "com.wxsoft.cxf.service.wx.WxService"
 */
@WebService(targetNamespace="http://wx.service.cxf.wxsoft.com/",
        endpointInterface = "com.wxsoft.cxf.service.wx.WxService")
public class WxServiceImpl implements WxService {
    @Override
    public String sayHello(String name) {

        return "Hello " + name ;
    }

    @Override
    public String sayHello2(String name) {
        return "Hello2 " + name;
    }
}
