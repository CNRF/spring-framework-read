package com.king.methodOverrides.replace;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @ClassName TestReplace
 * @Description  replace-method标签测试
 * @date 2021/9/28 10:56
 */
public class TestReplace {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/king/methodOverrides/replace.xml");
		OriginalDog originalDog = (OriginalDog)ac.getBean("OriginalDog");
		originalDog.sayHello();
		//replace标签会将xml配置的sayHello()方法替换为 ReplaceDog中 reimplement方法
		OriginalDog originalDog1 = (OriginalDog)ac.getBean("OriginalDog1");
		originalDog1.sayHello();
	}
}
