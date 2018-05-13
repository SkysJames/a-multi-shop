package com.sky.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


/**
 * JSON工具类,支持通过JSONObject.fromObject和JSONObject.toBean可以将Bean、ArrayList、
 * HashMap中与JSONObject的互相转换
 * @author xiefeiye
 *
 */
public class JsonUtil {

	private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	private static final Type typeList =  new TypeToken<ArrayList>(){}.getType();
	
	private static final Type typeMap =  new TypeToken<Map>(){}.getType();
	
	
	/**
	 * 将JavaBean字符串转换为 JSON 
	 * @param JavaBean
	 * @return
	 */
	public static  String getJavaBeanToJson(Object javaBean){
		try {
			return org.apache.struts2.json.JSONUtil.serialize(javaBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将JavaBean的字段转换为 JSON 
	 * @param JavaBean
	 * @return
	 */
	public static String getBeanFieldToJson(Object javaBean){
		return gson.toJson(javaBean);
	}
	
	/**
	 * 将JSON字符串转换为 JavaBean 
	 * @param <T>
	 * @param json
	 * @param T
	 * @return
	 */
	public static <T> T getJsonToJavaBean(String json,Class<T> clazz){
		return gson.fromJson(json, clazz);
	}
	
	/**
	 * 将JSON字符串转换为 ArrayList 
	 * @param json
	 * @return
	 */
	public static ArrayList getJsonToList(String json){
		return gson.fromJson(json, typeList);
	}
	
	/**
	 * 将JSON字符串转换为 ArrayListBean
	 * @param json
	 * @param type
	 * @return
	 */
	public static  <T> T getJsonToListBean(String json,Type type){
		return gson.fromJson(json, type);
	}
	
	/**
	 * 将JSON字符串转换为 HashMap
	 * @param json
	 * @return
	 */
	public static Map getJsonToMap(String json){
		return gson.fromJson(json, typeMap);
	}
	
	/**
	 * 对于特殊需求直接调用gson
	 * @return
	 */
	public static Gson getGson(){
		return gson;
	}
	public static String toJson(Object obj){
		return gson.toJson(obj);
	}
}
