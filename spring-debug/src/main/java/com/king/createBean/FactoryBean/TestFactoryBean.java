package com.king.createBean.FactoryBean;

import com.king.createBean.User;
import com.king.selfbdrpp.MyClassPathXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @ClassName TestFactoryBean
 * @Description
 * @date 2021/9/28 10:00
 */
public class TestFactoryBean {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("com/king/createBean/factoryBean/factoryBean.xml");
		MyFactoryBean bean1 = (MyFactoryBean) ac.getBean( "&myFactoryBean");
		System.out.println(bean1);
		User bean = (User) ac.getBean("myFactoryBean");
		System.out.println(bean.getUsername());
		User bean2 = (User) ac.getBean("myFactoryBean");
		System.out.println(bean2.getUsername());

	}
}
