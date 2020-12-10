package com.king;

import com.king.entity.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @date 2020/12/10 15:13
 */
public class MyMainTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
		Person bean = applicationContext.getBean(Person.class);
		System.out.println(bean);
	}
}
