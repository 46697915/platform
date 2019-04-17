package com.wxsoft.axis.client;

import com.wxsoft.axis.client.testservice.Client;

public class Test {
    public static void main(String[] args) {
        Client c = new Client();
        try {
            String ss = "你好，我是子君小哥！";
            String s = c.send(ss,
                    "http://localhost:8080/services/TestService");
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
