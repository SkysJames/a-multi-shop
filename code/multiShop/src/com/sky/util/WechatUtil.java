package com.sky.util;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 公共方法工具
 * @author xiefeiye
 *
 */
public class WechatUtil {
	
	protected static Logger logger = Logger.getLogger(WechatUtil.class);

	/**
	 * 微信签名校验
	 * @param signature
	 * @param token
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String signature, String token, String timestamp, String nonce) {
		if(signature==null) {
			return false;
		}
		
		String[] arr = new String[] {token, timestamp, nonce};
		
		//排序
		Arrays.sort(arr);
		
		//生成字符串
		StringBuffer content = new StringBuffer();
		for(int i=0;i<arr.length;i++) {
			content.append(arr[i]);
		}
		
		//sha1加密
		String temp = getSha1(content.toString());
		
		return signature.equals(temp);
	}
	
	/**
	 * sha1加密字符串
	 * @param str
	 * @return
	 */
	public static String getSha1(String str) {
		if(str==null || str.length()==0) {
			return null;
		}
		
		char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
		
		try {  
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes("UTF-8"));
            
            byte[] bytes = messageDigest.digest();
            int len = bytes.length;
            char buf[] = new char[len * 2];
            int k = 0;
            for(int i=0;i<len;i++) {
            		buf[k++] = hexDigits[bytes[i] >>> 4 & 0xf];
            		buf[k++] = hexDigits[bytes[i] & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {  
        		e.printStackTrace();
        		return null;
        }
	}
	
	/**
	 * 微信网页授权登录，或微信快捷登录的第二步
	 * 即回调函数所调用的内容
	 * @param appid
	 * @param appsecret
	 * @param code
	 * @return
	 */
	public static Map<String, Object> getUserInfoByWechat(String appid, String appsecret, String code) {
		//通过code换取access_token
		String tokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid
				+ "&secret=" + appsecret
				+ "&code=" + code
				+ "&grant_type=authorization_code";
		Map<String, Object> tokenMap = CommonMethodUtil.getJsonMapByUrl(tokenUrl);
		String openid = (String)tokenMap.get("openid");
		String access_token = (String)tokenMap.get("access_token");
		
		//拉取用户信息
		String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token
				+ "&openid="+openid
				+ "&lang=zh_CN";
		Map<String, Object> userMap = CommonMethodUtil.getJsonMapByUrl(infoUrl);
		
		return userMap;
	}
	
}
