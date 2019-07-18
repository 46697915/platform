package com.mytest.sys.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CenterController {

    @RequestMapping("/cbManager")
    public List CBManager(){
        List l = new ArrayList<Map>();
        Map m = new HashMap<String,String>();
        m.put("result","参保成功");
        l.add(m);
        return l;
    }

}
