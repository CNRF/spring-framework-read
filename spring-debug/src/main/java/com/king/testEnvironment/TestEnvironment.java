package com.king.testEnvironment;

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
		/*
		* Git无法提交spring-${username}.xml文件
		* 需要将文件夹中文件重命名为spring-${username}.xml
		* */
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/king/testEnvironment/spring-${username}.xml");
		AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
		System.out.println(beanFactory);

	}
}
