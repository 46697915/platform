package com.wxsoft.cxf.service.wx;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface WxService {
//    @WebMethod
//    String sayHello(@WebParam(name = "name") String name);
    @WebMethod
    String sayHello(String name);

    @WebMethod
    String sayHello2(String name);
}