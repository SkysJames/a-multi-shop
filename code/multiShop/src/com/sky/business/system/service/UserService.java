package com.sky.business.system.service;

import java.util.Map;

import com.sky.business.common.service.BaseService;
import com.sky.business.common.vo.LoginUser;
import com.sky.business.shop.entity.Shop;
import com.sky.business.system.entity.User;

/**
 * 用户Service接口
 * @author Sky James
 *
 */
public interface UserService extends BaseService {
	
	/**
	 * 微信授权登录
	 * @param userMap
	 * @return
	 * @throws Exception
	 */
	public LoginUser checkForLoginWechat(Map<String, Object> userMap, String ip) throws Exception;
	
	/**
	 * 检验是否登录后台系统成功
	 * @param loginUser
	 * @return
	 * @throws Exception
	 */
	public LoginUser checkForLogin(LoginUser loginUser) throws Exception;
	
	/**
	 * 检验是否登录前端系统成功
	 * @param loginUser
	 * @return
	 * @throws Exception
	 */
	public LoginUser checkForLoginClient(LoginUser loginUser) throws Exception;
	
	/**
	 * 设置并且获取登录用户
	 * @param user
	 * @param shop
	 * @return
	 * @throws Exception
	 */
	public LoginUser setAndGetLoginUser(LoginUser loginUser, User user, Shop shop) throws Exception;
	
	/**
	 * 修改用户信息
	 * @param editUser
	 * @throws Exception
	 */
	public void edit(Map<String,Object> editUser) throws Exception;
	
	/**
	 * 添加用户
	 * @param user
	 * @throws Exception
	 */
	public User add(Map<String,Object> user) throws Exception;
	
	/**
	 * 删除用户
	 * @param userId
	 * @param loginUser
	 * @throws Exception
	 */
	public void delete(String userId,LoginUser loginUser) throws Exception;
	
	/**
	 * 修改用户个人信息
	 * @param editUser
	 * @param loginUser
	 * @throws Exception
	 */
	public LoginUser editPerson(Map<String,Object> editUser, LoginUser loginUser) throws Exception;
	
	/**
	 * 修改用户个人的密码
	 * @param userId
	 * @param oldPasswd
	 * @param newPasswd
	 * @throws Exception
	 */
	public void editPersonPawd(String userId, String oldPasswd, String newPasswd) throws Exception;
	
}
