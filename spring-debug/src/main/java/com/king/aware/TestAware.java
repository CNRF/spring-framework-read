package com.king.aware;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @ClassName TestAware
 * @Description
 * @date 2021/8/25 9:23
 */
public class TestAware {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("com/king/aware/aware.xml");
		EnvironmentAwareImpl environmentAwareImpl = ac.getBean(EnvironmentAwareImpl.class);
		System.out.println(environmentAwareImpl);
	}
}
