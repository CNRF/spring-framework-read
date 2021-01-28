package com.king.processConfigBeanDefinitions;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @date 2021/1/27 14:16
 * @Description
 */
public class TestProcessorConfigBeanDefinitions {
	public static void main(String[] args) {
		//指定为注解的方式进行加载
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("com.king.processConfigBeanDefinitions");
		Person bean = ac.getBean(Person.class);
		System.out.println(bean);
	}
}
