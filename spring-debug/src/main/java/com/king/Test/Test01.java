package com.king.Test;

import com.king.config.MyConfig;
import com.king.entity.PersonA;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @date 2020/12/10 15:13
 */
public class Test01 {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
	/*	加载xml配置文件的第二种方式
		ClassPathResource classPathResource = new ClassPathResource("bean.xml");
		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(classPathResource );*/
	/*	接口载入
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MyConfig.class);*/

	/*	Person bean = applicationContext.getBean(Person.class);
		System.out.println(bean);*/
	/*
		AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(MyConfig.class);
		acac.getBean(MyService.class).TestService();
		*/
		PersonA bean1 = applicationContext.getBean(PersonA.class);
		ApplicationContext applicationContext1 = bean1.getApplicationContext();
		System.out.println(applicationContext1.toString());

	}
}
