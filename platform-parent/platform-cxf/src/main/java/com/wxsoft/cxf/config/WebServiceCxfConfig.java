package com.wxsoft.cxf.config;

import com.wxsoft.cxf.service.wx.WxService;
import com.wxsoft.cxf.service.wx.WxServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceCxfConfig {

    @Bean
    public ServletRegistrationBean dispatcherServlet(){
        return new ServletRegistrationBean(new CXFServlet(),"/service/*");//发布服务名称
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus()
    {
        return  new SpringBus();
    }

    @Bean
    public WxService wxService()
    {
        return  new WxServiceImpl();
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint=new EndpointImpl(springBus(), wxService());//绑定要发布的服务
        endpoint.publish("/user"); //显示要发布的名称
        return endpoint;
    }
}