package com.king.selfbdrpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author wmx
 * @version 1.0
 * @date 2021/1/6 10:51
 * @Description  自定义实现BeanDefinitionPostProcessor方法
 */
public class MyBeanDefinitionPostProcessor  implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("执行postProcessBeanFactory方法------MyBeanDefinitionPostProcessor");
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("执行postProcessBeanDefinitionRegistry方法------MyBeanDefinitionPostProcessor");
		registry.registerBeanDefinition("Teacher",new RootBeanDefinition(Teacher.class));
		registry.registerBeanDefinition("selfBeanDefinition",new RootBeanDefinition(MySelfBeanDefinitionRegistryPostProcessor.class));
	}
}
