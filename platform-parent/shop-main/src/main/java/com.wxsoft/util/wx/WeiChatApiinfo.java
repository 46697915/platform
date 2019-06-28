package com.wxsoft.util.wx;

import org.apache.commons.lang.StringUtils;

import com.wxsoft.business.wechat.model.req.AccessToken;

public class WeiChatApiinfo {
	/**
	 * 获取全局返回码
	 * 
	 * @param appid		微信appid
	 * @param secret	微信secret
	 * @return
	 * @throws Exception 
	 */
	public String getAccessToken(String appid,String secret){
		if(StringUtils.isEmpty(appid)||StringUtils.isEmpty(secret)) return "请输入appid或appsecret";
		String accessToken = "";
		String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token";
		AccessToken accessTokenModel = JsonMapper.buildNormalMapper().fromJson(HttpClientUtil.sendGetSSLRequest(accessTokenUrl+"?grant_type=client_credential&appid="+appid+"&secret="+secret, null), AccessToken.class);
		if(StringUtils.isEmpty(accessTokenModel.getToken())){
			return null;
		}else{
			accessToken=accessTokenModel.getToken();
		}
		return accessToken;
	}
	
	public String getUserInfo(String openId){
		return "";
	}
}
