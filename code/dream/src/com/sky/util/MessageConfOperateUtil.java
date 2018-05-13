package com.sky.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MessageConfOperateUtil {

	protected static Log log = LogFactory.getLog(MessageConfOperateUtil.class);

	private static Properties props;

	static {
		// 初始化配置文件属性
		InputStream in = null;
		String path = null;
		try {

			try {
				path = URLDecoder.decode(MessageConfOperateUtil.class
						.getResource("/resource/config/message.properties").toString(),
						"utf-8");
			} catch (UnsupportedEncodingException e) {
				log.info("---------------------------- 配置文件路径获取失败------------------------",e);
				e.printStackTrace();
			}

			path = path.substring(5);
			log.info("---------------------------- 配置文件路径：" + path
					+ "------------------------");
			in = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			log.info("-----------------------未找到配置文件--------------------------",e);
			e.printStackTrace();
		}
		props = new Properties();
		try {
			props.load(in);
		} catch (IOException e) {
			log.info("----------------------------配置文件加载失败------------------------",e);
			e.printStackTrace();
		}
	}

	public static Object get(Object key) {
		return props.get(key);
	}

	public static String getProperty(String key) {
		return props.getProperty(key);
	}

	public static void put(Object key, Object value) {
		props.put(key, value);
	}

	public static void putAll(Map<? extends Object, ? extends Object> map) {
		props.putAll(map);
	}

	public static void setProperty(String key, String value) {
		props.setProperty(key, value);
	}

	public static boolean containsKey(Object key) {
		return props.containsKey(key);
	}

	public static boolean containsValue(Object value) {
		return props.containsValue(value);
	}

	public static void remove(Object key) {
		props.remove(key);
	}

	public static int size() {
		return props.size();
	}

	public static boolean isEmpty() {
		return props.isEmpty();
	}

	public static Set<Object> getAllPropsKeys() {
		return props.keySet();
	}

}
