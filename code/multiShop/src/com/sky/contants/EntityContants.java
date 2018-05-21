package com.sky.contants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公共实体类的属性标识
 * @author Sky James
 *
 */
public class EntityContants {
	
	/**
	 * 返回的结果集的参数名
	 * @author Sky James
	 *
	 */
	public static final class ResultMapContants {
		
		/**
		 * 状态码
		 */
		public static final String STATUS_CODE = "statusCode";
		/**
		 * 信息
		 */
		public static final String MESSAGE = "message";
		
	}
	
	/**
	 * 日志
	 * @author Sky James
	 *
	 */
	public static class OplogContants {
		public static Map<String,String> actionMaps = new HashMap<String,String>();
		public static Map<String,String> methodMaps = new HashMap<String,String>();
		
		static {
			/**
			 * 前台展示action
			 */
			actionMaps.put("client-index", "前台首页");
			
			/**
			 * 后台action
			 */
			actionMaps.put("server-index", "后台首页");
			actionMaps.put("server-logout", "系统退出");
			actionMaps.put("server-login", "登录");
			actionMaps.put("user", "用户管理");
			actionMaps.put("type", "类型管理");
			actionMaps.put("prohistory", "收藏/历史记录管理");
			actionMaps.put("announce", "公告管理");
			actionMaps.put("message", "消息管理");
			actionMaps.put("report", "举报管理");
			actionMaps.put("shop", "店铺管理");
			actionMaps.put("evaluate", "评价管理");
			actionMaps.put("cart", "购物车管理");
			actionMaps.put("product", "商品管理");
			actionMaps.put("right-group", "角色管理");
			actionMaps.put("right", "权限管理");
			actionMaps.put("visitor", "访客管理");
			
			methodMaps.put("index", "进入主页面");
			methodMaps.put("logout", "退出了登录");
			methodMaps.put("save", "执行添加操作");
			methodMaps.put("edit", "执行修改操作");
			methodMaps.put("delete", "执行删除操作");
			methodMaps.put("list", "执行列表查询操作");
			methodMaps.put("paged", "执行分页查询操作");
		}
	}
	
	
//	public static void main(String[] args){
//		System.out.println(Oplog.actionMaps);
//		Set<String> keys = Oplog.actionMaps.keySet(); 
//		for(String key : keys){
//			System.out.println(key + " : " + Oplog.actionMaps.get(key));
//		}
//	}
	
}
