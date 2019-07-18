package com.mytest.sys.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CenterController {

    /**
     * 获得险种
     * @return
     */
    @RequestMapping("/getXz")
    public List getXz(){
        List l = new ArrayList<Map>();
        Map m = new HashMap<String,String>();
        m.put("301","基本医疗");
        m.put("302","大病");
        l.add(m);
        return l;
    }

    /**
     * 保存参保关系
     * @return
     */
    @RequestMapping("/saveInsurance")
    public List saveInsurance(){
        List l = new ArrayList<Map>();
        Map m = new HashMap<String,String>();
        m.put("result","参保人1324xxxxxxxxxx1378参加301险种成功");
        l.add(m);
        return l;
    }

}
