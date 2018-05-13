package com.sky.contants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 各个实体类的属性标识
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
	 * 用户
	 * @author Sky James
	 *
	 */
	public static final class UserContants {
		
		/**
		 * 用户状态
		 * @author Sky James
		 *
		 */
		public static final class UserStatus {
			/**
			 * 禁用状态
			 */
			public final static int UNUSING = 0;
			/**
			 * 启用状态
			 */
			public final static int USING = 1;
		}
		
		/**
		 * 登录状态
		 * @author Sky James
		 *
		 */
		public static final class LoginStatus {
			/**
			 * 离线状态
			 */
			public final static int OFFLINE = 0;
			/**
			 * 在线状态
			 */
			public final static int ONLINE = 1;
		}
	}
	
	/**
	 * 部门
	 * @author Sky James
	 *
	 */
	public static final class DepartmentContants {
		/**
		 * 总部部门ID
		 */
		public static final String DEPARTMENT_SYSTEM = "system";
		/**
		 * 父部门ID默认值
		 */
		public static final String PARENT_DEFAULT = "-1";
		
	}
	
	/**
	 * 权限
	 * @author Sky James
	 *
	 */
	public static final class RightContants {
		public static Map<String,String> typeMaps = new HashMap<String,String>();
		public static List<String> typeList = new ArrayList<String>();
		
		//权限类型
		public static String RIGHT_TYPE_NEWS = "news";
		public static String RIGHT_TYPE_PRODUCT = "product";
		public static String RIGHT_TYPE_USER = "user";
		public static String RIGHT_TYPE_VISITOR = "visitor";
		public static String RIGHT_TYPE_OPLOG = "oplog";
		public static String RIGHT_TYPE_OTHER = "other";
		
		static {
			typeMaps.put(RIGHT_TYPE_NEWS, "新闻管理");
			typeMaps.put(RIGHT_TYPE_PRODUCT, "产品管理");
			typeMaps.put(RIGHT_TYPE_USER, "用户管理");
			typeMaps.put(RIGHT_TYPE_VISITOR, "访客管理");
			typeMaps.put(RIGHT_TYPE_OPLOG, "日志管理");
			typeMaps.put(RIGHT_TYPE_OTHER, "其他");
			
			typeList.add(RIGHT_TYPE_NEWS);
			typeList.add(RIGHT_TYPE_PRODUCT);
			typeList.add(RIGHT_TYPE_USER);
			typeList.add(RIGHT_TYPE_VISITOR);
			typeList.add(RIGHT_TYPE_OPLOG);
			typeList.add(RIGHT_TYPE_OTHER);
		}
	}
	
	/**
	 * 访客
	 * @author Sky James
	 *
	 */
	public static final class VisitorContants {
		/**
		 * 访客状态
		 * @author Sky James
		 *
		 */
		public static final class Status {
			/**
			 * 禁用状态
			 */
			public final static int UNUSING = 0;
			/**
			 * 启用状态
			 */
			public final static int USING = 1;
		}
	}
	
	/**
	 * 新闻
	 * @author Sky James
	 *
	 */
	public static final class NewsContants {
		/**
		 * 新闻类型
		 * @author Sky James
		 *
		 */
		public static final class NewsType {
			/**
			 * 默认类型
			 */
			public final static int DEFAULT = 0;
		}
	}
	
	/**
	 * 产品
	 * @author Sky James
	 *
	 */
	public static final class ProductContants {
		/**
		 * 产品类型
		 * @author Sky James
		 *
		 */
		public static final class ProType {
			/**
			 * 默认类型
			 */
			public final static int DEFAULT = 0;
		}
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
			actionMaps.put("show-index", "展示首页");
			
			/**
			 * 后台action
			 */
			actionMaps.put("home-index", "后台首页");
			actionMaps.put("home-logout", "后台退出");
			actionMaps.put("home-login", "登录");
			actionMaps.put("user", "用户管理");
			actionMaps.put("department", "部门管理");
			actionMaps.put("right-group", "角色管理");
			actionMaps.put("right", "权限管理");
			actionMaps.put("visitor", "访客管理");
			actionMaps.put("oplog", "日志管理");
			actionMaps.put("news", "新闻管理");
			actionMaps.put("product", "产品管理");
			
			methodMaps.put("index", "进入主页面");
			methodMaps.put("logout", "退出了登录");
			methodMaps.put("save", "执行添加保存操作");
			methodMaps.put("edit", "执行修改操作");
			methodMaps.put("delete", "执行删除操作");
			methodMaps.put("list", "执行查询操作");
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
