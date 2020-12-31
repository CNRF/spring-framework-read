package com.king.Test;

import com.king.MyClassPathXmlApplicationContext;
import com.king.selfEditor.Customer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @date 2020/12/16 11:06
 * @Description 实现自定义接卸器
 */
public class Test04 {
	public static void main(String[] args) {

		ClassPathXmlApplicationContext applicationContext = new MyClassPathXmlApplicationContext("selfEditor.xml");
		Customer bean = applicationContext.getBean(Customer.class);
		System.out.println(bean);
	}
}
