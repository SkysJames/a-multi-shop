package com.sky.business.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Value;

import com.sky.business.common.vo.LoginUser;
import com.sky.business.oplog.entity.Oplog;
import com.sky.business.oplog.service.OplogService;
import com.sky.business.system.service.UserService;
import com.sky.contants.EntityContants;
import com.sky.util.IpProcessUtil;
import com.sky.util.WechatUtil;

/**
 * 微信类型action
 * @author xiefeiye
 *
 */
@InterceptorRefs({@InterceptorRef("defaultStack")})
public class WechatAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "oplogService")
	private OplogService oplogService;
	
	@Value("${wechat.isOpen}")
	private boolean isOpen;
	
	@Value("${wechat.token}")
	private String token;
	
	@Value("${wechat.appid}")
	private String appid;
	
	@Value("${wechat.appsecret}")
	private String appsecret;
	
	@Value("${wechat.redirectUrl}")
    private String redirectUrl;
	
	@Value("${wechat.quickAppid}")
	private String quickAppid;
	
	@Value("${wechat.quickAppsecret}")
	private String quickAppsecret;
	
	@Value("${wechat.quickRedirectUrl}")
    private String quickRedirectUrl;
	
	//授权登录后，最终访问的url
	private String reUrl;
	
	
	/**
	 * 微信公众平台的校验
	 * @return 
	 */
	public String execute() {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		
		PrintWriter out = null;
		
		try {
			out = resp.getWriter();
			
			//微信签名校验
			if(WechatUtil.checkSignature(signature, token, timestamp, nonce)) {
				out.print(echostr);
			}else {
				out.print("wechat check error!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				out.flush();
				out.close();
			}
		}
		
		return null;
	}
	
	/**
	 * 微信浏览器，授权登录入口
	 * @return
	 */
	public void webLogin(){
		try{
			//判断是否已开启微信网页授权登录功能
			if(!isOpen) {
				resp.sendRedirect(reUrl);
				return;
			}
			
			logger.info("微信网页授权登录");
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid
					+ "&redirect_uri=" + URLEncoder.encode(redirectUrl)
					+ "&response_type=code"
					+ "&scope=snsapi_userinfo"
					+ "&state=" + reUrl
					+ "#wechat_redirect";
			resp.sendRedirect(url);
		}catch (Exception e) {
			logger.error("微信网页授权登录失败:"+e.getMessage(), e);
		}
	}
	
	/**
	 * 微信网页授权登录的第二步
	 * 重定向的action
	 * @return
	 */
	public void webCallback(){
		try{
			//获取code和state
			String code = req.getParameter("code");
			String state = req.getParameter("state");
			
			Map<String, Object> userMap = WechatUtil.getUserInfoByWechat(appid, appsecret, code);
			
			//微信用户登录系统
			LoginUser loginUser = userService.checkForLoginWechat(userMap, IpProcessUtil.getIpAddr(req));
			session.setAttribute("loginUser", loginUser);
			
			//保存登录日志
			this.saveLoginLog(loginUser, "微信网页版");
			
			resp.sendRedirect(state);
		}catch (Exception e) {
			logger.error("webCallback失败:"+e.getMessage(), e);
		}
	}
	
	/**
	 * 普通浏览器，微信快捷登录入口
	 * @return
	 */
	public void quickLogin(){
		try{
			logger.info("微信快捷登录");
			String url = "https://open.weixin.qq.com/connect/qrconnect?appid=" + quickAppid
					+ "&redirect_uri=" + URLEncoder.encode(quickRedirectUrl)
					+ "&response_type=code"
					+ "&scope=snsapi_login"
					+ "&state=" + reUrl
					+ "#wechat_redirect";
			logger.info(url);
			resp.sendRedirect(url);
		}catch (Exception e) {
			logger.error("微信快捷登录失败:"+e.getMessage(), e);
		}
	}
	
	/**
	 * 微信扫码快捷登录的第二步
	 * 重定向的action
	 * @return
	 */
	public void quickCallback(){
		try{
			//获取code和state
			String code = req.getParameter("code");
			String state = req.getParameter("state");
			
			Map<String, Object> userMap = WechatUtil.getUserInfoByWechat(quickAppid, quickAppsecret, code);
			
			//微信用户登录系统
			LoginUser loginUser = userService.checkForLoginWechat(userMap, IpProcessUtil.getIpAddr(req));
			session.setAttribute("loginUser", loginUser);
			
			//保存登录日志
			this.saveLoginLog(loginUser, "微信扫码快捷登录");
			
			resp.sendRedirect(state);
		}catch (Exception e) {
			logger.error("quickCallback失败:"+e.getMessage(), e);
		}
	}
	
	/**
	 * 保存登录日志
	 * @param loginUser
	 */
	private void saveLoginLog(LoginUser loginUser, String logType) {
		try{
			String opDetail = "IP地址:" + loginUser.getUserIp() + "。" + "用户" + loginUser.getUsername() + "（" + loginUser.getUserId() + "）登录前台系统（" + logType + "）";
			Oplog log = Oplog.newOpUserInstance(loginUser.getUserId(), EntityContants.OplogContants.actionMaps.get("client-login"), opDetail, loginUser.getUserIp());
			oplogService.save(log);
			logger.info("用户" + loginUser.getUsername() + "（" + loginUser.getUserId() + "）" + "登录前台系统（" + logType + "）");
		}catch (Exception e) {
			logger.error("保存登录日志失败:"+e.getMessage(), e);
		}
	}
	
	
	public String getReUrl() {
		return reUrl;
	}

	public void setReUrl(String reUrl) {
		this.reUrl = reUrl;
	}

}
