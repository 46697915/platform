package com.wxsoft.utils;

public class ResponseEasyUI {
    //程序返回结果
    private Object rows;
    //总记录数
    private long total;

    //返回信息编码  0失败 1成功
    private int code;
    //错误信息
    private String error;
    //程序返回结果
    private Object result;
    //成功信息
    private String success;
    //登陆成功的标识
    private String token;
    //创建实例
    public static ResponseEasyUI getResponseResult(){
        return new ResponseEasyUI();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}