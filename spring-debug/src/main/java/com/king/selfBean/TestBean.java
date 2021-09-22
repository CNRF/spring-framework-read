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
		Person bean = (Person) ac.getBean("person");

		System.out.println(bean);
	}
}
