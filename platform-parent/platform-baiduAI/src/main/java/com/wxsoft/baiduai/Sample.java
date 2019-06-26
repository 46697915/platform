package com.wxsoft.baiduai;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.FaceVerifyRequest;
import com.baidu.aip.face.MatchRequest;
import com.wxsoft.baiduai.util.ImgUtil;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "16591290";
    public static final String API_KEY = "BIpohZUVLy7FarKY0hLcQYiI";
    public static final String SECRET_KEY = "WCKLTuspwNwfgx9ZqnmPiHu41n6VxVnE";
    private static String image = ImgUtil.getImageStr("e:/aa.JPG");
    private static AipFace client ;
    static{
        // 初始化一个AipFace
        client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
    }

    //获取连接
    public static AipFace getAipFace(){
        if (null == client) {
            synchronized (AipFace.class) {
                if (null == client) {
                    // 初始化一个AipFace
                    client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

                    // 可选：设置网络连接参数
                    client.setConnectionTimeoutInMillis(2000);
                    client.setSocketTimeoutInMillis(60000);
                }
            }
        }

        return client;
    }
    // 人脸检测
    public static void detect(){

        // 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 调用接口
//        String image = "取决于image_type参数，传入BASE64字符串或URL字符串或FACE_TOKEN字符串";
        String image = ImgUtil.getImageStr("e:/aa.JPG");
        String imageType = "BASE64";

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("face_field", "age,beauty,expression,face_shape,gender,glasses,landmark,landmark72,landmark150,race,quality,eye_status,emotion,face_type");
        options.put("max_face_num", "2");
        options.put("face_type", "CERT");
        options.put("liveness_control", "NORMAL");
        // 人脸检测
        JSONObject res = client.detect(image, imageType, options);
        System.out.println(res.toString(2));

    }

    /**
     * 人脸搜索
     */
    public static void search(){
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("max_face_num", "3");
        options.put("match_threshold", "70");
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        options.put("user_id", "user1");
        options.put("max_user_num", "3");

        String image = Sample.image; //"取决于image_type参数，传入BASE64字符串或URL字符串或FACE_TOKEN字符串";
        image = ImgUtil.getImageStr("e:/cc.jpg");
        String imageType = "BASE64";
        String groupIdList = "group1";

        // 人脸搜索
        JSONObject res = client.search(image, imageType, groupIdList, options);
        System.out.println(res.toString(2));
    }

    /**
     * 人脸注册
     */
    public static void addUser(){
        client = getAipFace();
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("user_info", "{陈亮,12341234}");
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        options.put("action_type", "REPLACE");

        String image = Sample.image; //"取决于image_type参数，传入BASE64字符串或URL字符串或FACE_TOKEN字符串";
        String imageType = "BASE64";
        String groupId = "group1";
        String userId = "user1";

        // 人脸注册
        JSONObject res = client.addUser(image, imageType, groupId, userId, options);
        System.out.println(res.toString(2));
    }

    //两张照片对比
    public static void match(){
        String image1 = "base64_1";
        String image2 = "base64_2";

        // image1/image2也可以为url或facetoken, 相应的imageType参数需要与之对应。
        MatchRequest req1 = new MatchRequest(image1, "BASE64");
        MatchRequest req2 = new MatchRequest(image2, "BASE64");
        ArrayList<MatchRequest> requests = new ArrayList<MatchRequest>();
        requests.add(req1);
        requests.add(req2);

        JSONObject res = client.match(requests);
        System.out.println(res.toString(2));
    }

    /**
     * 在线活体检查
     */
    public static void faceverify(){
        String image = Sample.image; //"image_base64_content";
        FaceVerifyRequest req = new FaceVerifyRequest(image, "BASE64");
        ArrayList<FaceVerifyRequest> list = new ArrayList<FaceVerifyRequest>();
        list.add(req);
        JSONObject res = client.faceverify(list);
        System.out.println(res.toString(2));
    }

    public static void main(String[] args) {
//        detect();
        //addUser();
        search();
//        faceverify();
    }
}