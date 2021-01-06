package com.king.Test;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @date 2020/12/16 11:06
 * @Description
 */
public class TestEnvironment {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-${username}.xml");
		AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
		System.out.println(beanFactory);

	}
}
