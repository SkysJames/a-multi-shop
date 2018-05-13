package com.sky.business.system.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.common.vo.LoginUser;
import com.sky.business.common.vo.Pager;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.system.dao.UserDao;
import com.sky.business.system.entity.User;
import com.sky.business.system.service.UserService;
import com.sky.contants.CodeMescContants;
import com.sky.contants.EntityContants;
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
	public Pager pagedList(Map<String, Object> condition) throws Exception {
		Integer pageNo = Pager.DEFAULT_CURRENT_PAGE;
		Integer pageSize = Pager.DEFAULT_PAGE_SIZE;
		
		if(condition.containsKey("pageNo") && condition.containsKey("pageSize")) {
			pageNo = CommonMethodUtil.getIntegerByObject(condition.get("pageNo"));
			pageSize = CommonMethodUtil.getIntegerByObject(condition.get("pageSize"));
		}
		
		return userDao.pagedList(condition, pageNo, pageSize);
	}

	@Override
	public LoginUser checkForLogin(LoginUser loginUser) throws Exception {
		
		//验证数据库中是否存在该用户
		User user = this.findByID(User.class, loginUser.getUserId());
		if(user == null){
			throw new ServiceException(CodeMescContants.CodeContants.USER_INEXIST,CodeMescContants.MessageContants.USER_INEXIST);
		}
		
		//验证用户的密码是否正确
		if(!(user.getPasswd().equals(loginUser.getUserPwd()))){
			throw new ServiceException(CodeMescContants.CodeContants.LOGIN_USER_ERROR,CodeMescContants.MessageContants.LOGIN_USER_ERROR);
		}
//		//验证md5加密后的密码是否正确
//		String pwdMd5 = EncryptArithmeticUtil.md5EncryptAll(loginUser.getUserPwd().getBytes("UTF-8"));
//		loginUser.setUserPwd(pwdMd5);
//		if(!(user.getPasswd().equals(loginUser.getUserPwd()))){
//			throw new ServiceException(CodeMesUtil.Code.LOGIN_USER_ERROR,CodeMesUtil.Message.LOGIN_USER_ERROR);
//		}
		
		//验证用户的状态是否为启用状态
		if(user.getUserStatus() != EntityContants.UserContants.UserStatus.USING){
			throw new ServiceException(CodeMescContants.CodeContants.LOGIN_USER_STATIC, CodeMescContants.MessageContants.LOGIN_USER_STATIC);
		}
		
		user.setLoginIp(loginUser.getUserIp());
		user.setLoginStatus(EntityContants.UserContants.LoginStatus.ONLINE);
		user.setLoginTime(new Timestamp(new Date().getTime()));
		
		loginUser.setLoginTime(user.getLoginTime());
		loginUser.setUsername(user.getName());
		
		this.update(user);
		
		return loginUser;
	}
	
	

	@Override
	public void edit(Map<String,Object> editUser, LoginUser loginUser) throws Exception {
		//查找数据库中是否存在该用户
		User user = this.findByID(User.class, (String)editUser.get("id"));
		if(user == null){
			throw new ServiceException(CodeMescContants.CodeContants.USER_INEXIST, CodeMescContants.MessageContants.USER_INEXIST);
		}
		
		if(editUser.containsKey("name")){
			user.setName((String)editUser.get("name"));
		}
		if(editUser.containsKey("passwd")){
			user.setPasswd((String)editUser.get("passwd"));
//			//md5加密密码，再存入数据库
//			String pwdMd5 = EncryptArithmeticUtil.md5EncryptAll(editUser.getPasswd().getBytes("UTF-8"));
//			user.setPasswd(pwdMd5);
		}
		if(editUser.containsKey("remark")){
			user.setRemark((String)editUser.get("remark"));
		}
		if(editUser.containsKey("userStatus")){
			user.setUserStatus(CommonMethodUtil.getIntegerByObject(editUser.get("userStatus")));
		}
		if(editUser.containsKey("departmentId")){
			user.setDepartmentId((String)editUser.get("departmentId"));
		}
		if(editUser.containsKey("rights")){
			user.setRights((String)editUser.get("rights"));
		}
		if(editUser.containsKey("rights")){
			user.setRightgroups((String)editUser.get("rightgroups"));
		}
		
		this.update(user);
		
	}
	
	@Override
	public User add(Map<String,Object> user) throws Exception {
		//查找数据库中是否存在该用户
		User hasUser = this.findByID(User.class, (String)user.get("id"));
		if(hasUser != null){
			throw new ServiceException(CodeMescContants.CodeContants.USER_EXIST,CodeMescContants.MessageContants.USER_EXIST);
		}
		
		//md5加密密码
//		String pwdMd5 = EncryptArithmeticUtil.md5EncryptAll(user.getPasswd().getBytes("UTF-8"));
//		user.setPasswd(pwdMd5);
		
		User newUser = new User();
		newUser.setId((String)user.get("id"));
		newUser.setCreateTime(new Timestamp(new Date().getTime()));
		newUser.setUserStatus(EntityContants.UserContants.UserStatus.USING);
		newUser.setLoginStatus(EntityContants.UserContants.LoginStatus.OFFLINE);
		newUser.setName((String)user.get("name"));
		newUser.setPasswd((String)user.get("passwd"));
		newUser.setRemark((String)user.get("remark"));
		newUser.setUserStatus(CommonMethodUtil.getIntegerByObject(user.get("userStatus")));
		newUser.setDepartmentId((String)user.get("departmentId"));
		newUser.setRights((String)user.get("rights"));
		newUser.setRightgroups((String)user.get("rightgroups"));
		
		this.save(newUser);
		return newUser;
		
	}
	
	@Override
	public void delete(String userId,LoginUser loginUser) throws Exception {
		//查找数据库中是否存在该用户
		User user = this.findByID(User.class, userId);
		if(user == null){
			throw new ServiceException(CodeMescContants.CodeContants.USER_INEXIST, CodeMescContants.MessageContants.USER_INEXIST);
		}
		
		this.delete(user);
	}

	@Override
	public LoginUser editPerson(Map<String,Object> editUser, LoginUser loginUser) throws Exception {
		//查找数据库中是否存在该用户
		User user = this.findByID(User.class, (String)editUser.get("id"));
		if(user == null){
			throw new ServiceException(CodeMescContants.CodeContants.USER_INEXIST, CodeMescContants.MessageContants.USER_INEXIST);
		}
		
		if(editUser.containsKey("name")) {
			user.setName((String)editUser.get("name"));
		}
		
		if(editUser.containsKey("remark")) {
			user.setRemark((String)editUser.get("remark"));
		}
		
		this.update(user);
		loginUser.setUsername(user.getName());
		
		return loginUser;
	}

	@Override
	public void editPersonPawd(String userId, String oldPasswd, String newPasswd) throws Exception {
		//验证数据库中是否存在该用户
		User user = this.findByID(User.class, userId);
		if(user == null){
			throw new ServiceException(CodeMescContants.CodeContants.USER_INEXIST,CodeMescContants.MessageContants.USER_INEXIST);
		}
		
		//验证用户的旧密码是否正确
		if(!(user.getPasswd().equals(oldPasswd))){
			throw new ServiceException(CodeMescContants.CodeContants.USER_PASSWD,CodeMescContants.MessageContants.USER_PASSWD);
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
