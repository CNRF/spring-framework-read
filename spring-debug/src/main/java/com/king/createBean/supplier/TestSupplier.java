package com.king.createBean.supplier;

import com.king.createBean.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @ClassName TestSupplier
 * @Description 通过supplier创建Bean对象
 * @date 2021/9/28 9:43
 */
public class TestSupplier {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/king/createBean/supplier/supplier.xml");
		User bean = ac.getBean(User.class);
		System.out.println(bean.getUsername());
	}
}
