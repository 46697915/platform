package com.platform.httpclient.https.login;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
 
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
/**
 * 模拟登录知乎
 *
 * 知乎修改了，未成功。20190729
 */
public class Main {
    //知乎首页
    final private static String INDEX_URL = "https://www.zhihu.com";
    //邮箱登录地址
    final private static String EMAIL_LOGIN_URL = "https://www.zhihu.com/login/email";
    //手机号码登录地址https://zhihu-web-analytics.zhihu.com/api/v1/logs/batch
//    final private static String PHONENUM_LOGIN_URL = "https://www.zhihu.com/login/phone_num";
    final private static String PHONENUM_LOGIN_URL = "https://passport.csdn.net/v1/register/pc/login/doLogin";
    //登录验证码地址
    final private static String YZM_URL = "https://www.zhihu.com/captcha.gif?type=login";
 
    private CloseableHttpClient httpClient;
    private HttpClientContext httpClientContext;
 
    /**
     * 初始化CloseableHttpClient、HttpClientContext并设置Cookie策略
     */
    public Main(){
        RequestConfig globalConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY)
                .build();
        httpClient = HttpClients.custom()
                .setDefaultRequestConfig(globalConfig)
                .build();
 
        httpClientContext = HttpClientContext.create();
        Registry<CookieSpecProvider> registry = RegistryBuilder
                .<CookieSpecProvider> create()
                .register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())
                .register(CookieSpecs.BROWSER_COMPATIBILITY,
                        new BrowserCompatSpecFactory()).build();
        httpClientContext.setCookieSpecRegistry(registry);
    }
    /**
     *
     * @param emailOrPhoneNum 邮箱或手机号码
     * @param pwd 密码
     * @return
     */
    public boolean login(String emailOrPhoneNum,
                         String pwd){
        String yzm = null;
        String loginState = null;
        HttpGet getRequest = new HttpGet(INDEX_URL);
        HttpClientUtil.getWebPage(httpClient, httpClientContext, getRequest, "utf-8", false);
        HttpPost request = null;
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
//        if(emailOrPhoneNum.contains("@")){
//            //通过邮箱登录
//            request = new HttpPost(EMAIL_LOGIN_URL);
//            formParams.add(new BasicNameValuePair("email", emailOrPhoneNum));
//        }
//        else {
//            //通过手机号码登录
//            request = new HttpPost(PHONENUM_LOGIN_URL);
//            formParams.add(new BasicNameValuePair("userIdentification", emailOrPhoneNum));
//        }
        request = new HttpPost(PHONENUM_LOGIN_URL);
        formParams.add(new BasicNameValuePair("userIdentification", emailOrPhoneNum));
//        yzm = yzm(httpClient, httpClientContext, YZM_URL);
//        formParams.add(new BasicNameValuePair("captcha", yzm));
        formParams.add(new BasicNameValuePair("_xsrf", ""));//这个参数可以不用
        formParams.add(new BasicNameValuePair("pwdOrVerifyCode", pwd));
        formParams.add(new BasicNameValuePair("remember_me", "true"));
        formParams.add(new BasicNameValuePair("uaToken", "118#ZVWZzwL8eKe5UZX2kZ2/cZZTZeHhSZYTZZ4T1z6hzHRzZBFZyoqxvgV72ZzTXHW4Z1bZzsqUzFFZZBSeVfqVzH2z5eZTXfeGZZxHuwTIzECM2/rcl7qYZexrZe/TVHW4ZgZuumiT1KEKBeCZXfquzg2zZZFhHluhzZ2ZZ0NTzeWzzDZuVfq4zH2ZZZChXHWVZggZZzqhzHRZZXquVfq4zH2zkDgUhHW4Hg/rzrrTsFugZxzg2KicplrzwZuHCou2UE92zLgZDZ1kM9LvxyQ7AfO68xyla2U9gVK7lqHlvKyWv7c0otWCugZCmrDtKHZzhmNx2stj9eZTl9IibjNXHf1mEZ2sc0dqrX+fIM4pOqb6Suj5JNoRBdZhxd4D0C2z10/gKNZhILYZlmAk7PQ+HQ6vPsgozPsctwy2aFHnKU7q3J/6RwqXTxNvcEwx/hcuAm1aV0d3kUTxdT28lAKPns1yYTL2qWnovN5SjVtBorD+cjFIFf2+0QlFqbyKEw7NW3D7PtWCglTVl5BReajdb6azCV+ENW/ONefFs4SBKQuh09Af68aBVF1HnoPaDmbS+twONFAS2QRA2tIpTi5RRnjG+Kwi7e+cu/4odOkBhhpRwv/138CymiJbUo6GbBiBiCxUwbwXrytp+e6qKcKEpoba5+zDLEiVlG4ILYeD41byOM40BjT6cPx0Z0C9siUXyFJvS6m2DpFutUON4EQqGlxfArIagNcf33Inj+/Cgs3M8+SXsX/N39fb6IVPBbrQeprCFN7zz27tzkIJHBR/VQAlw1JlkwX8fLLqgQ+kLOguuRk99upscPcPP4BKPoL0C5pFVAC5rpvW2ksHeWiSmZ16/G/Qi+WOZZ0N2kBgJPvyz9EOglx7SoxyIzNyAv9Td45rxe2CL4ZEReUHBDh+4+5zr+71lPaaRdW0dUMiQRtB0EEFmTyxfdVPrL2kCRWmgSAo/s6tKsHKA6Sq84RJfyNkX/2kW2qdMehjS0RR01reRfV+fGumvwk60rGvomRcj06+g2gMA/IevgyAY1CXnYMq+rVxrr5iL/1Gu4EYHjKJrsqHWndHmm9+els6/SWexUrGOHeH2WFvgVDXXO+36bLzJn0Ev3HI87ljxFcbEnU0olf6KiZOtBBaAS4FK70Z/9na/2O3EFd3YvzFA5hgo8Ge5KSvRFwdBUCaPkBmPVCxe+nmA3UuNg5ydz2jaVLEpuPY4GfhWmNYrry5jxfelX0mOvJz9plFx+Ud8y/NTq3RjjLsxldv+JAbOxVDl/t2dScobcyo9CCjEpfDOuPXWg=="));
        formParams.add(new BasicNameValuePair("webUmidToken", "T66BC64C7228ED056505339F7539F7A6CD2580C8908BA821EF409B63B1C"));
        formParams.add(new BasicNameValuePair("loginType", "1"));
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(formParams, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        request.setEntity(entity);
        loginState = HttpClientUtil.getWebPage(httpClient, httpClientContext, request, "utf-8", true);//登录
//        System.out.println("loginState:"+loginState);
        JSONObject jo = new JSONObject(loginState);
        if(jo.get("r").toString().equals("0")){
            System.out.println("登录知乎成功");
            getRequest = new HttpGet("https://www.zhihu.com");
            /**
             * 访问首页
             */
            HttpClientUtil.getWebPage(httpClient, httpClientContext,getRequest, "utf-8", false);
            return true;
        }else{
            throw new RuntimeException(HttpClientUtil.decodeUnicode(loginState));
        }
    }
    /**
     * 下载验证码至本地，并手动输入验证码
     * @return
     */
    public String yzm(CloseableHttpClient httpClient,HttpClientContext context, String url){
        String verificationCodePath = System.getProperty("user.home");
        //下载验证码至本地
        HttpClientUtil.downloadFile(httpClient, context, url, verificationCodePath, "/yzm.jpg",true);
        System.out.println("请输入 " + verificationCodePath + "\\yzm.jpg 下的验证码：");
        Scanner sc = new Scanner(System.in);
        String yzm = sc.nextLine();
        return yzm;
    }
    public static void main(String[] args){
        Main modelLogin = new Main();
        modelLogin.login("46697915@qq.com", "Chen_123456");
    }
}
