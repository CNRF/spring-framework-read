package com.king.xmlLoad;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @ClassName TestFileLoad
 * @Description 测试xml在spring中加载过程
 * @date 2021/9/7 9:45
 */
public class TestFileLoad {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("com/king/xmlLoad/xmlLoad.xml");


	}
}
