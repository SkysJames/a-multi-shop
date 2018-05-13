package com.sky.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextException;

public final class BeanDefinedLocator implements ApplicationContextAware {

    private static final BeanDefinedLocator beanLocator = new BeanDefinedLocator();

    private ApplicationContext context = null;

    private BeanDefinedLocator() {}

    public static BeanDefinedLocator getInstance() {
    		return beanLocator;
    }
    
    public void setApplicationContext(ApplicationContext applicationcontext) throws BeansException {
    		beanLocator.context = applicationcontext;
	}

    public Object getBean(String beanName) {
	    	return this.context.getBean(beanName);
    }
    
    /**
     * 根据指定的bean名及类型来获取bean
     * @param <T>
     * @param beanName
     * @param clazz
     * @return
     */
	public <T> T getBean(String beanName, Class<T> clazz) {
		contextNotNull();
		return (T) this.context.getBean(beanName, clazz);
	}
    
    /**
     * 根据指定的类型来获取bean
     * @param <T>
     * @param clazz
     * @return
     */
	public <T> T getBean(Class<T> clazz) {
		contextNotNull();
		
		return (T) BeanFactoryUtils.beansOfTypeIncludingAncestors(this.context, clazz, true, true).values().iterator().next();
	}
	
	 /**
     * 根据指定的类型来获取bean集合
     * @param <T>
     * @param clazz
     * @return 
     */
	public <T> List<T> getBeans(Class<T> clazz) {
		contextNotNull();
		return new ArrayList<T>(BeanFactoryUtils.beansOfTypeIncludingAncestors(this.context, clazz, true, true).values());
	}
    
	/**
	 * 判断context上下文是否为空
	 */
	private void contextNotNull() {
		if (this.context == null)
		    throw new ApplicationContextException("application context is null");
	}

}