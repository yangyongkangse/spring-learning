package com.spring.learning.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * author: yangyk
 * date: 2020/9/21 13:08
 * description:
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public SpringContextUtil() {
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}

	//获取applicationContext
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * Author: yangyk
	 * Date: 2020/9/21 13:11
	 * Description: 根据名称获取bean
	 */
	public static Object getBean(String beanName) {
		return applicationContext != null ? applicationContext.getBean(beanName) : null;
	}

	//通过class获取Bean.
	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}

	//通过name,以及Clazz返回指定的Bean
	public static <T> T getBean(String name, Class<T> clazz) {
		return getApplicationContext().getBean(name, clazz);
	}
}
