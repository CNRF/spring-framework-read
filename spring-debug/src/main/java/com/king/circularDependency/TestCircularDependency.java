package com.king.circularDependency;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @ClassName TestCircularDependency
 * @Description 测试循环依赖
 * @date 2021/9/2 9:52
 */
public class TestCircularDependency {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("com/king/circularDependency/circular.xml");
		A a = ac.getBean(A.class);
		System.out.println(a);
		System.out.println(a.getB());
		B b = ac.getBean(B.class);
		System.out.println(b.getA());
	}
}
