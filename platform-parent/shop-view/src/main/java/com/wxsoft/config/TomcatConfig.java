package com.wxsoft.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.AbstractServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class TomcatConfig {
    @Value("${factory.doc.root}")
    private String rootDoc;
    @Bean
    public AbstractServletWebServerFactory embeddedServletContainerFactory() {
       
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        tomcatServletWebServerFactory.setDocumentRoot(
                new File(rootDoc));
        return  tomcatServletWebServerFactory;
    }
}