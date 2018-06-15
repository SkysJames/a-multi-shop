package com.sky.business.shop.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.common.vo.LoginUser;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.shop.dao.ShopDao;
import com.sky.business.shop.entity.Shop;
import com.sky.business.shop.service.ShopService;
import com.sky.business.system.entity.User;
import com.sky.contants.CodeMescContants;
import com.sky.contants.FileContants;
import com.sky.contants.RightGroupContants;
import com.sky.contants.ShopContants;
import com.sky.util.CommonMethodUtil;
import com.sky.util.DateUtil;

/**
 * 店铺Service类
 * @author Sky James
 *
 */
@Service("shopService")
public class ShopServiceImpl extends BaseServiceImpl implements ShopService {

	@Resource(name = "shopDao")
	private ShopDao shopDao;
	
	@Override
	public void edit(Map<String,Object> editObj) throws Exception {
		//查找数据库中是否存在
		Shop shop = this.findByID(Shop.class, (String)editObj.get("id"));
		if(shop == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		if(editObj.containsKey("name")){
			shop.setName((String)editObj.get("name"));
		}
		if(editObj.containsKey("popularity")){
			shop.setPopularity(CommonMethodUtil.getIntegerByObject(editObj.get("popularity")));
		}
		if(editObj.containsKey("recommend")){
			shop.setRecommend(CommonMethodUtil.getIntegerByObject(editObj.get("recommend")));
		}
		if(editObj.containsKey("shopType")){
			shop.setShopType((String)editObj.get("shopType"));
		}
		if(editObj.containsKey("service")){
			shop.setService((String)editObj.get("service"));
		}
		if(editObj.containsKey("logoPathList") && (editObj.get("logoPathList") instanceof List)){
			//图片存放的目录
			String picPath = FileContants.SHOP_FILE + File.separator + (String)editObj.get("name");
			//保存图片
			String logo = CommonMethodUtil.saveFiles((List<String>) editObj.get("logoPathList"), picPath);
			
			shop.setLogo(logo);
		}
		if(editObj.containsKey("wechatPathList") && (editObj.get("wechatPathList") instanceof List)){
			//图片存放的目录
			String picPath = FileContants.SHOP_FILE + File.separator + (String)editObj.get("name");
			//保存图片
			String wechatPic = CommonMethodUtil.saveFiles((List<String>) editObj.get("wechatPathList"), picPath);
			
			shop.setWechatPic(wechatPic);
		}
		if(editObj.containsKey("description")){
			shop.setDescription((String)editObj.get("description"));
		}
		if(editObj.containsKey("mark")){
			shop.setMark(CommonMethodUtil.getBigDecimalByObject(editObj.get("mark")));
		}
		if(editObj.containsKey("phone")){
			shop.setPhone((String)editObj.get("phone"));
		}
		if(editObj.containsKey("address")){
			shop.setAddress((String)editObj.get("address"));
		}
		if(editObj.containsKey("longitude")){
			shop.setLongitude(CommonMethodUtil.getDoubleByObject(editObj.get("longitude")));
		}
		if(editObj.containsKey("latitude")){
			shop.setLatitude(CommonMethodUtil.getDoubleByObject(editObj.get("latitude")));
		}
		if(editObj.containsKey("remark")){
			shop.setRemark((String)editObj.get("remark"));
		}
		if(editObj.containsKey("brief")){
			shop.setBrief((String)editObj.get("brief"));
		}
		if(editObj.containsKey("status")){
			Integer beforeStatus = shop.getStatus();
			Integer lastStatus = CommonMethodUtil.getIntegerByObject(editObj.get("status"));
			
			//若店铺状态，由申请待验证变为启用，则是该店铺的入驻时间
			if((null==shop.getAddTime() && ShopContants.Status.USING==lastStatus)
					|| (ShopContants.Status.REGISTER==beforeStatus && ShopContants.Status.USING==lastStatus)) {
				shop.setAddTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			}
			shop.setStatus(lastStatus);
		}
		if(editObj.containsKey("overTimeString")) {
			String overTimeString = (String)editObj.get("overTimeString");
			shop.setOverTime(new Timestamp(DateUtil.convertStr2Date(overTimeString).getTime()));
		}
		if(editObj.containsKey("picPathList") && (editObj.get("picPathList") instanceof List)) {
			//图片存放的目录
			String picPath = FileContants.SHOP_FILE + File.separator + (String)editObj.get("name");
			//保存图片
			String picture = CommonMethodUtil.saveFiles((List<String>) editObj.get("picPathList"), picPath);
			
			shop.setPicture(picture);
		}
		if(editObj.containsKey("picHrefList")) {
			List<String> picHrefList = (List<String>) editObj.get("picHrefList");
			String pictureHref = CommonMethodUtil.packetListToStr(picHrefList);
			
			shop.setPictureHref(pictureHref);
		}
		
		this.update(shop);
	}
	
	@Override
	public Shop add(Map<String,Object> addObj) throws Exception {
		Shop shop = new Shop();
		shop.setId(UUID.randomUUID().toString());
		shop.setPopularity(0);
		
		if(addObj.containsKey("name")){
			String name = (String)addObj.get("name");
			List<Shop> shopList = this.findBy(Shop.class, "name", name);
			
			//查找数据库中是否名字相同的店铺
			if(shopList!=null && shopList.size()>0) {
				throw new ServiceException(CodeMescContants.CodeContants.ERROR_EXIST, CodeMescContants.MessageContants.ERROR_EXIST);
			}
			shop.setName(name);
		}
		
		if(addObj.containsKey("popularity") && addObj.get("popularity")!=null){
			shop.setPopularity(CommonMethodUtil.getIntegerByObject(addObj.get("popularity")));
		} else {
			shop.setPopularity(0);
		}
		if(addObj.containsKey("recommend") && addObj.get("recommend")!=null){
			shop.setRecommend(CommonMethodUtil.getIntegerByObject(addObj.get("recommend")));
		} else {
			shop.setRecommend(0);
		}
		if(addObj.containsKey("shopType")){
			shop.setShopType((String)addObj.get("shopType"));
		}
		if(addObj.containsKey("service")){
			shop.setService((String)addObj.get("service"));
		}
		if(addObj.containsKey("logo")){
			//图片存放的目录
			String picPath = FileContants.SHOP_FILE + File.separator + (String)addObj.get("name");
			//保存图片
			String logo = CommonMethodUtil.saveFiles((List<String>) addObj.get("logo"), picPath);
			
			shop.setLogo(logo);
		}
		if(addObj.containsKey("wechatPathList") && (addObj.get("wechatPathList") instanceof List)){
			//图片存放的目录
			String picPath = FileContants.SHOP_FILE + File.separator + (String)addObj.get("name");
			//保存图片
			String wechatPic = CommonMethodUtil.saveFiles((List<String>) addObj.get("wechatPathList"), picPath);
			
			shop.setWechatPic(wechatPic);
		}
		if(addObj.containsKey("description")){
			shop.setDescription((String)addObj.get("description"));
		}
		if(addObj.containsKey("mark") && addObj.get("mark")!=null){
			shop.setMark(CommonMethodUtil.getBigDecimalByObject(addObj.get("mark")));
		} else {
			shop.setMark(new BigDecimal(0));
		}
		if(addObj.containsKey("phone")){
			shop.setPhone((String)addObj.get("phone"));
		}
		if(addObj.containsKey("address")){
			shop.setAddress((String)addObj.get("address"));
		}
		if(addObj.containsKey("longitude")){
			shop.setLongitude(CommonMethodUtil.getDoubleByObject(addObj.get("longitude")));
		}
		if(addObj.containsKey("latitude")){
			shop.setLatitude(CommonMethodUtil.getDoubleByObject(addObj.get("latitude")));
		}
		if(addObj.containsKey("remark")){
			shop.setRemark((String)addObj.get("remark"));
		}
		if(addObj.containsKey("brief")){
			shop.setBrief((String)addObj.get("brief"));
		}
		if(addObj.containsKey("status")){
			Integer lastStatus = CommonMethodUtil.getIntegerByObject(addObj.get("status"));
			if(ShopContants.Status.USING==lastStatus) {
				shop.setAddTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			}
			shop.setStatus(lastStatus);
		}
		if(addObj.containsKey("overTimeString") && StringUtils.isNotBlank((String)addObj.get("overTimeString"))) {
			String overTimeString = (String)addObj.get("overTimeString");
			shop.setOverTime(new Timestamp(DateUtil.convertStr2Date(overTimeString).getTime()));
		} else {
			shop.setOverTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		}
		if(addObj.containsKey("picPathList")){
			//图片存放的目录
			String picPath = FileContants.SHOP_FILE + File.separator + (String)addObj.get("name");
			//保存图片
			String picture = CommonMethodUtil.saveFiles((List<String>) addObj.get("picPathList"), picPath);
			
			shop.setPicture(picture);
		}
		if(addObj.containsKey("picHrefList")) {
			List<String> picHrefList = (List<String>) addObj.get("picHrefList");
			String pictureHref = CommonMethodUtil.packetListToStr(picHrefList);
			
			shop.setPictureHref(pictureHref);
		}
		
		this.save(shop);
		return shop;
	}
	
	@Override
	public LoginUser register(Map<String, Object> addObj, LoginUser loginUser) throws Exception {
		//判断该用户是否已有店铺
		if(StringUtils.isNotBlank(loginUser.getShopId()) && StringUtils.isNotBlank(loginUser.getShopName())) {
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_COMMON, "您已有店铺，申请失败");
		}
		
		//保存新增店铺
		Shop shop = this.add(addObj);
		
		//更新当前登录用户的数据库信息（更新其店铺信息，以及角色）
		User user = this.findByID(User.class, loginUser.getUserId());
		user.setShopId(shop.getId());
		if(StringUtils.isBlank(user.getRightgroups())) {
			user.setRightgroups(RightGroupContants.RIGHT_GROUP_SHOPKEEPER);
		}else if(user.getRightgroups().indexOf(RightGroupContants.RIGHT_GROUP_SHOPKEEPER) == -1) {
			user.setRightgroups("," + RightGroupContants.RIGHT_GROUP_SHOPKEEPER);
		}
		this.update(user);
		
		//更新当前登录用户的loginUser信息
		loginUser.setShopId(user.getShopId());
		loginUser.setShopName(shop.getName());
		loginUser.setShopStatus(shop.getStatus());
		
		return loginUser;
	}
	
	@Override
	public void delete(String id) throws Exception {
		//店铺不能删除
		if(ShopContants.SHOP_SYSTEM.equals(id) || ShopContants.SHOP_BBS.equals(id)) {
			throw new ServiceException("400", "初始店铺不能删除");
		}
		
		//查找数据库中是否存在该店铺，不存在则删除失败
		Shop shop = this.findByID(Shop.class, id);
		if(shop == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		//查找该店铺下是否存在用户，有则删除失败
		
		this.delete(shop);
	}

	@Override
	public void addPopularity(String id) throws Exception {
		//查找数据库中是否存在
		Shop shop = this.findByID(Shop.class, id);
		if(shop == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		if(null == shop.getPopularity()){
			shop.setPopularity(1);
		}else {
			shop.setPopularity(shop.getPopularity() + 1);
		}
		
		this.update(shop);
	}

}
