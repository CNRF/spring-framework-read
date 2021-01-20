package com.king.selfbdrpp;

import com.king.config.MyClassPathXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @date 2020/12/16 11:06
 * @Description 实现自定义 ClassPathXmlApplicationContext
 */
public class TestSelfApplicationContext {
	public static void main(String[] args) {

		ClassPathXmlApplicationContext applicationContext = new MyClassPathXmlApplicationContext("bean.xml");
	}
}
