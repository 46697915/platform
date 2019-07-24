package com.wxsoft.websocket.controller;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.websocket.util.WebSocket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class WebsocketController {

    @RequestMapping("/")
    public String hello(String username) throws IOException {
        WebSocket ws = new WebSocket();
        JSONObject jo = new JSONObject();
        jo.put("message", "这是后台返回的消息！");
        jo.put("To",username);
        ws.onMessage(jo.toString());
        return "websocket";
    }
}
