package com.mytest.sys.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CenterController {

    @RequestMapping("/cbManager")
    public String CBManager(){
        return "参保成功";
    }

}
