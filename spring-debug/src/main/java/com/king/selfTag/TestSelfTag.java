package com.king.selfTag;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @ClassName TestSelfTag
 * @Description 实现自定义标签  自定义一个<wmx:user   username email age>,完成整个标签的处理工作
 * @date 2021/9/7 16:54
 */
public class TestSelfTag {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("com/king/selfTag/selfTag.xml");
		User wmx = (User) ac.getBean("wmx");
		System.out.println(wmx);
	}
}
