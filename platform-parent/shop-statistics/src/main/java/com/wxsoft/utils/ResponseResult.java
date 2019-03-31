package com.wxsoft.utils;

public class ResponseResult {
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
    public static ResponseResult getResponseResult(){
        return new ResponseResult ();
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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
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