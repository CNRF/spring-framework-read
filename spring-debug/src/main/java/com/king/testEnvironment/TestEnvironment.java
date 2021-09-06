package com.king.testEnvironment;

import com.king.entity.Person;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @date 2020/12/16 11:06
 * @Description  spring会自动将相关系统环境变量替换为相关值，故此段代码会报错，提示无spring-Administrator.xml文件
 * 				@see org.springframework.context.support.AbstractApplicationContext#getEnvironment
 *
 */
public class TestEnvironment {
	public static void main(String[] args) {
		/*
		* Git无法提交spring-${username}.xml文件
		* 需要将文件夹中文件重命名为spring-${username}.xml
		* */
//		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/king/testEnvironment/spring-${username}.xml");
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/king/testEnvironment/environment.xml");
		AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
		System.out.println(beanFactory);
	}
}
