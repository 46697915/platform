package com.wxsoft.controller;

import com.wxsoft.utils.DESUtil;
import com.wxsoft.utils.HardWareUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SNController {

    private static String infoHW = "";
    private static String infoHWEncrypt = "";
    /**
     * 返回硬件信息
     * @return
     */
    @RequestMapping("/getSN")
    public String getSN(){
        if("".equals(infoHW)){
            infoHW = HardWareUtils.getHWInfo();
        }
        return infoHW;
    }

    /**
     * 返回加密后硬件信息
     * @return
     */
    @RequestMapping("/getSNEncrypt")
    public String getSNEncrypt(){
        if("".equals(infoHWEncrypt)){
            infoHWEncrypt = HardWareUtils.getHWInfoEncrypt();
        }
        return infoHWEncrypt ;
    }

    /**
     * 获取动态的 sn 并加密
     * @return
     */
    @RequestMapping("/getDynamicSNEncrypt")
    public String getDynamicSNEncrypt(){
        if("".equals(infoHW)){
            infoHW = HardWareUtils.getHWInfo();
        }
        long time = new Date().getTime();
        String dSN = time +"_"+ infoHW ;
        System.out.println(dSN);
        String dSNEncrypt = DESUtil.getEncryptString(dSN);
        System.out.println(dSNEncrypt);
        System.out.println(DESUtil.getDecryptString(dSNEncrypt));
        return dSNEncrypt ;
    }

    /**
     * 获取DynamicSN 的 解密后数据
     * @param dSNEncrypt
     * @return
     */
    @RequestMapping("/getDynamicSNDecrypt")
    public String getDynamicSNDecrypt(String dSNEncrypt){
        if(dSNEncrypt==null || "".equals(dSNEncrypt)){
            return  "";
        }
        System.out.println(dSNEncrypt);
        String dSNDecrypt = DESUtil.getDecryptString(dSNEncrypt);
        return dSNDecrypt ;
    }

    /**
     * 清空infoHW硬件信息缓存
     * @return
     */
    @RequestMapping("/clearInfoHW")
    public String clearInfoHW(){
        infoHW =  "";
        return "true";
    }

    /**
     * 清空infoHWEncrypt硬件加密信息缓存
     * @return
     */
    @RequestMapping("/clearInfoHWEncrypt")
    public String clearInfoHWEncrypt(){
        infoHWEncrypt =  "";
        return "true";
    }
}
