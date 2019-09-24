package org.wxsoftframwork.ui.core.vue.element;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.Map;

public class ResponseElementUI {
    //成功信息
    private String success;
    //错误信息
    private String msg;
    //程序返回结果
    private Object result;
    //登陆成功的标识
    private String token;
    //创建实例
    public static ResponseElementUI getResponseResult(){
        return new ResponseElementUI ();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {

        this.result = result;
    }

    /**
     * 使用com.github.pagehelper.PageHelper分页
     * @param pi
     */
    public void setResult(PageInfo pi) {

        Map<String, Object> rMap = new HashMap<String,Object>();
        rMap.put("total",pi.getTotal());
        rMap.put("list",pi.getList());

        this.result = rMap;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}