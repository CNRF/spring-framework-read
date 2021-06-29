package com.king.selfbdrpp;

import com.king.entity.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;

/**
 * @author wmx
 * @version 1.0
 * @date 2020/12/11 14:39
 * @Description 自定义的BeanFactoryPostProcessor
 */

public class MyBeanPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory){
		BeanDefinition person =beanFactory.getBeanDefinition("person");
		/*
		* 再次可以对bean进行相关增强
		* */
		person.setLazyInit(false);
		person.setDependsOn("personA");
		System.out.println("自定义的BeanFactoryPostProcessor");
	}
}
