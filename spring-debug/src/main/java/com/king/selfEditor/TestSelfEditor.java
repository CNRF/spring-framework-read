package com.king.selfEditor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @date 2020/12/16 11:06
 * @Description 实现自定义解析器
 */
public class TestSelfEditor {
	public static void main(String[] args) {

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("selfEditor.xml");
		Customer bean = applicationContext.getBean(Customer.class);
		System.out.println(bean);
	}
}
