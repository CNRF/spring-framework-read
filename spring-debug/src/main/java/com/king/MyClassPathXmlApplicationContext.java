package com.king;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @date 2020/12/16 15:03
 * @Description
 */
public class MyClassPathXmlApplicationContext  extends ClassPathXmlApplicationContext {
	//拓展initPropertySource测试
	@Override
	protected void initPropertySources() {
		System.out.println("拓展initPropertySources");
		/**
		 * 会在validateRequiredProperties方法中检测是否包含username，没有会抛异常
		 * @see org.springframework.core.env.AbstractPropertyResolver#validateRequiredProperties
		 * */
		getEnvironment().setRequiredProperties("username");
//		getEnvironment().setRequiredProperties("abc");

	}
	public MyClassPathXmlApplicationContext(String... configLocations){
		super(configLocations);
	}
}
