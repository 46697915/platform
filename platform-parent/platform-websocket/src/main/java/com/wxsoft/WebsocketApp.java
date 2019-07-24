package com.wxsoft;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.websocket.util.WebSocket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class WebsocketApp {
    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(WebsocketApp.class,args);
        while(true){
            Thread.sleep(10000);
            WebSocket ws = new WebSocket();
            JSONObject jo = new JSONObject();
            jo.put("message", "这是后台返回的消息！");
            jo.put("To","cl");
            ws.onMessage(jo.toString());
            System.out.println("已发生消息！");
        }
    }
}
