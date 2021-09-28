package com.king.createBean.factoryMethod;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @ClassName TestFactoryMethod
 * @Description 通过FactoryMethod创建Bean对象
 * @date 2021/9/28 9:27
 */
public class TestFactoryMethod {
	public static void main(String[] args) {

		ApplicationContext ac = new ClassPathXmlApplicationContext("com/king/createBean/factoryMethod/factoryMethod.xml");
		Person person = ac.getBean("person", Person.class);
		System.out.println(person);
		Person person2 = ac.getBean("person2", Person.class);
		System.out.println(person2);
	}
}
