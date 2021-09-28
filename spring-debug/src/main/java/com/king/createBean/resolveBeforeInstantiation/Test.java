package com.king.createBean.resolveBeforeInstantiation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @ClassName Test
 * @Description  自定义BeanPostProcessor生成代理对象 InstantiationAwareBeanPostProcessor
 * @date 2021/9/28 10:25
 */
public class Test {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/king/createBean/resolveBeforeInstantiation/resolveBeforeInstantiation.xml");
		BeforeInstantiation bean = ac.getBean(BeforeInstantiation.class);
		bean.doSomething();
	}
}
