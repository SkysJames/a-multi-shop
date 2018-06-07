package com.sky.business.system.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.common.vo.LoginUser;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.shop.entity.Shop;
import com.sky.business.system.dao.UserDao;
import com.sky.business.system.entity.User;
import com.sky.business.system.service.UserService;
import com.sky.contants.CodeMescContants;
import com.sky.contants.RightContants;
import com.sky.contants.RightGroupContants;
import com.sky.contants.ShopContants;
import com.sky.contants.UserContants;
import com.sky.util.CommonMethodUtil;

/**
 * 用户Service类
 * @author Sky James
 *
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Resource(name = "userDao")
	private UserDao userDao;
	
	@Override
	public LoginUser checkForLogin(LoginUser loginUser) throws Exception {
		//验证数据库中是否存在该用户
		User user = this.findByID(User.class, loginUser.getUserId());
		if(user == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST,CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		//验证用户的密码是否正确
		if(!(user.getPasswd().equals(loginUser.getUserPwd()))){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_COMMON, "用户名密码错误");
		}
//		//验证md5加密后的密码是否正确
//		String pwdMd5 = EncryptArithmeticUtil.md5EncryptAll(loginUser.getUserPwd().getBytes("UTF-8"));
//		loginUser.setUserPwd(pwdMd5);
//		if(!(user.getPasswd().equals(loginUser.getUserPwd()))){
//			throw new ServiceException(CodeMesUtil.Code.LOGIN_USER_ERROR,CodeMesUtil.Message.LOGIN_USER_ERROR);
//		}
		
		//验证用户的状态是否为启用状态
		if(user.getUserStatus() != UserContants.UserStatus.USING){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_COMMON, "该用户已被禁用");
		}
		
		//验证用户是否有登陆后台的权限
		if(StringUtils.isBlank(user.getAllRights()) || user.getAllRights().indexOf(RightContants.BACK_MANAGE)<0) {
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_COMMON, "该用户无登陆后台的权限");
		}
		
		//验证用户的店铺是否存在且启用
		Shop shop = user.getShop();
		if(shop == null || (!ShopContants.SHOP_SYSTEM.equals(shop.getId()) && ShopContants.Status.USING!=shop.getStatus())) {
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_COMMON, "该用户暂无店铺或店铺未启用");
		}
		
		user.setLoginIp(loginUser.getUserIp());
		user.setLoginStatus(UserContants.LoginStatus.ONLINE);
		user.setLoginTime(new Timestamp(new Date().getTime()));
		
		loginUser.setLoginTime(user.getLoginTime());
		loginUser.setUsername(user.getName());
		loginUser.setShopId(user.getShopId());
		loginUser.setShopName(shop.getName());
		loginUser.setShopStatus(shop.getStatus());
		loginUser.setQq(user.getQq());
		loginUser.setWechat(user.getWechat());
		loginUser.setTelephone(user.getTelephone());
		loginUser.setRightgroups(user.getRightgroups());
		loginUser.setAllRights(user.getAllRights());
		
		this.update(user);
		
		return loginUser;
	}
	
	

	@Override
	public void edit(Map<String,Object> editUser) throws Exception {
		//查找数据库中是否存在该用户
		User user = this.findByID(User.class, (String)editUser.get("id"));
		if(user == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		if(editUser.containsKey("name")){
			user.setName((String)editUser.get("name"));
			user.setName(user.getName().replaceAll("\"", "'"));
		}
		if(editUser.containsKey("passwd")){
			user.setPasswd((String)editUser.get("passwd"));
//			//md5加密密码，再存入数据库
//			String pwdMd5 = EncryptArithmeticUtil.md5EncryptAll(editUser.getPasswd().getBytes("UTF-8"));
//			user.setPasswd(pwdMd5);
		}
		if(editUser.containsKey("remark")){
			user.setRemark((String)editUser.get("remark"));
			user.setRemark(user.getRemark().replaceAll("\"", "'"));
		}
		if(editUser.containsKey("userStatus")){
			user.setUserStatus(CommonMethodUtil.getIntegerByObject(editUser.get("userStatus")));
		}
		if(editUser.containsKey("shopId")){
			user.setShopId((String)editUser.get("shopId"));
		}
		if(editUser.containsKey("qq")){
			user.setQq((String)editUser.get("qq"));
			user.setQq(user.getQq().replaceAll("\"", "'"));
		}
		if(editUser.containsKey("wechat")){
			user.setWechat((String)editUser.get("wechat"));
			user.setWechat(user.getWechat().replaceAll("\"", "'"));
		}
		if(editUser.containsKey("telephone")){
			user.setTelephone((String)editUser.get("telephone"));
			user.setTelephone(user.getTelephone().replaceAll("\"", "'"));
		}
		if(editUser.containsKey("rights")){
			user.setRights((String)editUser.get("rights"));
		}
		if(editUser.containsKey("rightgroups")){
			user.setRightgroups((String)editUser.get("rightgroups"));
		}
		
		this.update(user);
		
	}
	
	@Override
	public User add(Map<String,Object> user) throws Exception {
		//查找数据库中是否存在该用户
		User hasUser = this.findByID(User.class, (String)user.get("id"));
		if(hasUser != null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_EXIST,CodeMescContants.MessageContants.ERROR_EXIST);
		}
		
		//md5加密密码
//		String pwdMd5 = EncryptArithmeticUtil.md5EncryptAll(user.getPasswd().getBytes("UTF-8"));
//		user.setPasswd(pwdMd5);
		
		User newUser = new User();
		newUser.setId((String)user.get("id"));
		newUser.setCreateTime(new Timestamp(new Date().getTime()));
		newUser.setUserStatus(UserContants.UserStatus.USING);
		newUser.setLoginStatus(UserContants.LoginStatus.OFFLINE);
		newUser.setName((String)user.get("name"));
		newUser.setPasswd((String)user.get("passwd"));
		
		if(user.containsKey("remark")){
			newUser.setRemark((String)user.get("remark"));
			newUser.setRemark(newUser.getRemark().replaceAll("\"", "'"));
		}
		if(user.containsKey("userStatus")){
			newUser.setUserStatus(CommonMethodUtil.getIntegerByObject(user.get("userStatus")));
		}
		if(user.containsKey("shopId")){
			newUser.setShopId((String)user.get("shopId"));
		}
		if(user.containsKey("qq")){
			newUser.setQq((String)user.get("qq"));
			newUser.setQq(newUser.getQq().replaceAll("\"", "'"));
		}
		if(user.containsKey("wechat")){
			newUser.setWechat((String)user.get("wechat"));
			newUser.setWechat(newUser.getWechat().replaceAll("\"", "'"));
		}
		if(user.containsKey("telephone")){
			newUser.setTelephone((String)user.get("telephone"));
			newUser.setTelephone(newUser.getTelephone().replaceAll("\"", "'"));
		}
		if(user.containsKey("rights")){
			newUser.setRights((String)user.get("rights"));
		}
		if(user.containsKey("rightgroups")){
			newUser.setRightgroups((String)user.get("rightgroups"));
		}
		
		this.save(newUser);
		return newUser;
		
	}
	
	@Override
	public void delete(String userId,LoginUser loginUser) throws Exception {
		//查找数据库中是否存在该用户
		User user = this.findByID(User.class, userId);
		if(user == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		if(userId.equals("admin")) {
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_COMMON, "管理员用户不能被删除");
		}
		
		if(StringUtils.isNotBlank(loginUser.getRightgroups())) {
			//该用户为管理员用户，或者为被删除用户的店长，才可以删除
			if(loginUser.getRightgroups().indexOf(RightGroupContants.RIGHT_GROUP_ADMIN)>-1
					|| (loginUser.getRightgroups().indexOf(RightGroupContants.RIGHT_GROUP_SHOPKEEPER)>-1 && loginUser.getShopId().equals(user.getShopId()))) {
				this.delete(user);
			}
		}
	}

	@Override
	public LoginUser editPerson(Map<String,Object> editUser, LoginUser loginUser) throws Exception {
		//查找数据库中是否存在该用户
		User user = this.findByID(User.class, (String)editUser.get("id"));
		if(user == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		if(editUser.containsKey("name")) {
			user.setName((String)editUser.get("name"));
			user.setName(user.getName().replaceAll("\"", "'"));
		}
		if(editUser.containsKey("remark")) {
			user.setRemark((String)editUser.get("remark"));
			user.setRemark(user.getRemark().replaceAll("\"", "'"));
		}
		if(editUser.containsKey("qq")){
			user.setQq((String)editUser.get("qq"));
			user.setQq(user.getQq().replaceAll("\"", "'"));
		}
		if(editUser.containsKey("wechat")){
			user.setWechat((String)editUser.get("wechat"));
			user.setWechat(user.getWechat().replaceAll("\"", "'"));
		}
		if(editUser.containsKey("telephone")){
			user.setTelephone((String)editUser.get("telephone"));
			user.setTelephone(user.getTelephone().replaceAll("\"", "'"));
		}
		
		this.update(user);
		loginUser.setUsername(user.getName());
		loginUser.setQq(user.getQq());
		loginUser.setWechat(user.getWechat());
		loginUser.setTelephone(user.getTelephone());
		
		return loginUser;
	}

	@Override
	public void editPersonPawd(String userId, String oldPasswd, String newPasswd) throws Exception {
		//验证数据库中是否存在该用户
		User user = this.findByID(User.class, userId);
		if(user == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST,CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		//验证用户的旧密码是否正确
		if(!(user.getPasswd().equals(oldPasswd))){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_COMMON,"密码错误");
		}
//		//验证md5加密后用户的旧密码是否正确
//		String oldPwdMd5 = EncryptArithmeticUtil.md5EncryptAll(oldPasswd.getBytes("UTF-8"));
//		if(!(user.getPasswd().equals(oldPwdMd5))){
//			throw new ServiceException(CodeMesUtil.Code.USER_PASSWD,CodeMesUtil.Message.USER_PASSWD);
//		}
		
		user.setPasswd(newPasswd);
//		//md5加密后的密码存入数据库
//		String newPwdMd5 = EncryptArithmeticUtil.md5EncryptAll(newPasswd.getBytes("UTF-8"));
//		user.setPasswd(newPwdMd5);
		
		this.update(user);
		
	}

}
