package com.king.selfBean;

import com.king.entity.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @ClassName TestBean
 * @Description 测试bean实例化中各种属性的加载过程
 * @date 2021/9/1 10:51
 */
public class TestBean {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("com/king/selfBean/selfBean.xml");
/*
			//	当xml配置两个相同的bean的时候，不能通过ac.getBean(Person.class)获取对象
				Person bean = ac.getBean(Person.class);*/
		Person bean = (Person) ac.getBean("person");

		System.out.println(bean);
	}
}
