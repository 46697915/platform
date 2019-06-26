package com.platform.httpclient.https.ignore;

public class HttpsTest {
    public static void main(String[] args) throws Exception {
//        String url = "https://192.168.1.101/xxx";
        String url = "https://blog.csdn.net/defonds/article/details/86594441";
        String jsonStr = "{xxx}";
        String httpOrgCreateTestRtn = HttpClientUtil.doPost(url, jsonStr, "utf-8");
        HttpClientUtil.get(url,"utf-8");
    }
}
