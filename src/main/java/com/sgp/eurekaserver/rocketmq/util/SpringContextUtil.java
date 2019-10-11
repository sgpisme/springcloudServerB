package com.sgp.eurekaserver.rocketmq.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SpringContextUtil
 * @Description:获取spring容器，以访问容器中定义的其他bean
 * @author guosheng.zhu
 * @date May 6, 2011 2:35:22 PM
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	private SpringContextUtil() {
		System.out.println("6666666666666666666666666666666!");
	}

	/**
	 * @Title: getBean
	 * @Description: 获取对象
	 * @param @param beanId
	 * @param @return
	 * @param @throws BeansException
	 * @return Object
	 */
	public static Object getBeans(String beanId) throws BeansException {
		return applicationContext.getBean(beanId);
	}
	
	public static <T> T getBeans(Class<T> c){
		return applicationContext.getBean(c);
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	// 改方法仅提供做单元测试用
	public static void setActForTest(ApplicationContext act) {
		applicationContext = act;
	}
}
