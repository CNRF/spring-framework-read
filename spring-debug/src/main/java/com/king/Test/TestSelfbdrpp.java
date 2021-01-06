package com.king.Test;

import com.king.selfEditor.Customer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @date 2021/1/6 10:53
 * @Description 测试自定义的BeanPostRegistryProcessor
 */
public class TestSelfbdrpp {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("selfBeanPostRegistryProcessor.xml");
//		Customer bean = applicationContext.getBean(Customer.class);
//		System.out.println(bean);
	}
}
