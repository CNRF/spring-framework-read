package com.king;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author wmx
 * @version 1.0
 * @date 2020/12/11 14:39
 * @Description 自定义的BeanFactoryPostProcessor
 */
public class MyBeanPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		BeanDefinition person = beanFactory.getBeanDefinition("person");
		/*
		* 再次可以对bean进行相关增强
		* */
		person.setLazyInit(true);
		System.out.println("自定义的BeanFactoryPostProcessor");

	}
}
