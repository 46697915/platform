package com.wxsoft.shrio.controller;

import com.wxsoft.shrio.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    //登录校验
    @RequestMapping("/login")
    public String login(User user){
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        return "success";
    }
}
