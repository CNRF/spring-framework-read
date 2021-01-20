package com.king.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
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

	@Override
	protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
		System.out.println("扩展实现beanProcessorFactory方法");
	}

	@Override
	public void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor) {
		super.addBeanFactoryPostProcessor(postProcessor);
//		实现自定义的BeanPostProcessor方式一
		//方式二:在XML中将BeanPostProcessor注册为bean，详见bean.xml文件
		super.addBeanFactoryPostProcessor(new MyBeanPostProcessor());
	}
}
