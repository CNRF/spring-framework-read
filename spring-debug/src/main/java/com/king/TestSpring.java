package com.king;

import com.king.entity.Person;
import com.king.entity.PersonA;
import com.king.testService.MyConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @author wmx
 * @version 1.0
 * @date 2020/12/10 15:13
 */
public class TestSpring {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/king/bean.xml");
		Person bean1 = applicationContext.getBean(Person.class);
		int age = bean1.getAge();
		System.out.println(age);

//		System.out.println();
	/*	加载xml配置文件的第二种方式
		ClassPathResource classPathResource = new ClassPathResource("processConfigBeanDefinitions.xml");
		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(classPathResource );*/
//		接口载入
//		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MyConfig.class);

	/*	Person bean = applicationContext.getBean(Person.class);
		System.out.println(bean);*/
	/*
		AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(MyConfig.class);
		acac.getBean(MyService.class).TestService();
		*/


	}
}
