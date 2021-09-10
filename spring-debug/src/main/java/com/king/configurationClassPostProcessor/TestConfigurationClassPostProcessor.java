package com.king.configurationClassPostProcessor;

import com.king.entity.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @ClassName TestConfigurationClassPostProcessor
 * @Description  ConfigurationClassPostProcessor代码阅读
 * @date 2021/9/9 11:05
 */
public class TestConfigurationClassPostProcessor {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("com/king/ConfigurationClassPostProcessor/TestConfigurationClassPostProcessor.xml");
		Person person = (Person) ac.getBean("person");
		System.out.println(person);
	}
}
