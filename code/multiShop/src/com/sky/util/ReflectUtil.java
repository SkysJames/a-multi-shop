package com.sky.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sky James
 *
 */
public class ReflectUtil {
	
	/**
	 * ��̬��ȡһ�����з��͵����з��͵ľ������͵�Class����
	 * ��Ҫ��̬ȥ��ȡһ����ľ��巺�����ͣ�������ڴ���Ĺ��췽����
	 * ���л�ȡ�����Խ�����һ����Ĺ��췽�����ô˷�����Ȼ�󽫷��ؽ�
	 * ��ֵ��һ����Ա�����Ա���
	 * 
	 * @param <T> �˷��͵���ʵ����
	 * @param clazz	��Ҫ��̬��ȡ����巺�����͵����Class����
	 * @return �˷��͵���ʵ���͵�Class����
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Deprecated
	public static <T> Class<T> getRealClassGenericType(Class clazz) {
		while(clazz != Object.class) {
			Type t = clazz.getGenericSuperclass();
			if(t instanceof ParameterizedType) {
				Type[] args = ((ParameterizedType)t).getActualTypeArguments();
				if(args[0] instanceof Class) {
					return (Class<T>) args[0];
				}
			}
			clazz = clazz.getSuperclass();
		}
		return (Class<T>) Object.class;
	}
	
	/**
	 * ��̬��ȡһ�����з��͵����з��͵ľ������͵�Class����
	 * ��Ҫ��̬ȥ��ȡһ����ľ��巺�����ͣ�������ڴ���Ĺ��췽����
	 * ���л�ȡ�����Խ�����һ����Ĺ��췽�����ô˷�����Ȼ�󽫷��ؽ�
	 * ��ֵ��һ����Ա�����Ա���
	 * 
	 * @param <T> �˷��͵���ʵ����
	 * @param clazz	��Ҫ��̬��ȡ����巺�����͵����Class����
	 * @return �˷��͵���ʵ���͵�Class����
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getClassGenericType(Class<?> clazz) {
		return (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	/**
	 * ��ȡ��������Ϊprivate String�ĳ�Ա�����������б�
	 * @param clazz
	 * @return
	 */
	public static List<String> getStringFieldNames(Class<?> clazz) {
		List<String> fieldNames = new ArrayList<String>();
		Field[] fields = clazz.getDeclaredFields();
		
		for(Field f : fields) {
			if(f.getModifiers() == Modifier.PRIVATE 
					&& f.getType().toString().equals("class java.lang.String")) {

				fieldNames.add(f.getName());
			}
		}
		
		return fieldNames;
	}
}
