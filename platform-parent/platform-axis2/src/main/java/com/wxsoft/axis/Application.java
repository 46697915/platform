package com.wxsoft.axis;

import com.wxsoft.axis.core.listener.StartupListener;
import com.wxsoft.axis.core.util.FileCopyUtils;
import com.wxsoft.axis.core.util.LogUtil;
import org.apache.axis2.transport.http.AxisServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
//@EnableConfigurationProperties({Config.class})
public class Application {
	public static void main(String[] args) throws Exception {
    	SpringApplication springApplication =new SpringApplication(Application.class);
    	springApplication.addListeners(new StartupListener());
        springApplication.run(args);
    }
	
	@Bean
	public ServletRegistrationBean helloWorldServlet() {
	    ServletRegistrationBean helloWorldServlet = new ServletRegistrationBean();
	    helloWorldServlet.setServlet(new AxisServlet());//这里的AxisServlet就是web.xml中的org.apache.axis2.transport.http.AxisServlet
	    helloWorldServlet.addUrlMappings("/services/*");
	    //通过默认路径无法找到services.xml，这里需要指定一下路径，且必须是绝对路径
	    String path = this.getClass().getResource("/ServicesPath").getPath().toString();
	    LogUtil.info("The original path：" + path);
	    if(path.toLowerCase().startsWith("file:")){
	    	LogUtil.info("去掉前面的“file:”！");
	    	path = path.substring(5);
	    }
	    //如果获得到的地址里有感叹号，说明文件在压缩包（jar包）中，Axis2无法正常使用，需要拷贝到jar包外
	    if(path.indexOf("!") != -1){
	    	try {
	    		LogUtil.info("将ServicesPath/services/MyWebService/META-INF/services.xml文件拷贝到jar包同级目录下！");
				FileCopyUtils.copy("ServicesPath/services/MyWebService/META-INF/services.xml");
			} catch (IOException e) {
				LogUtil.error(e);
			}
	    	LogUtil.info("jar包运行！查找jar包同级目录下的“/ServicesPath”目录");
	    	path = path.substring(0, path.lastIndexOf("/", path.indexOf("!"))) + "/ServicesPath";
	    }
	    LogUtil.info("The final path：" + path);
	    helloWorldServlet.addInitParameter("axis2.repository.path", path);
	    helloWorldServlet.setLoadOnStartup(1);
	    return helloWorldServlet;
	}
}
