package com.king.createBean.resolveBeforeInstantiation;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author wmx
 * @version 1.0
 * @ClassName MyInstantiationAwareBeanPostProcessor
 * @Description
 * @date 2021/9/28 10:22
 */
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		System.out.println("beanName:"+beanName+"----执行postProcessBeforeInstantiation方法");
		if (beanClass == BeforeInstantiation.class){
			//通过Cjlib动态代理实现对Bean进行增强
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(beanClass);
			enhancer.setCallback(new MyMethodInterceptor());
			BeforeInstantiation beforeInstantiation = (BeforeInstantiation) enhancer.create();
			System.out.println("创建代理对象：" + beforeInstantiation);
			return new BeforeInstantiation();
		}
		return null;
	}

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		System.out.println("beanName:"+beanName+"----执行postProcessAfterInstantiation方法");

		return false;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("beanName:"+beanName+"----执行postProcessBeforeInitialization方法");

		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("beanName:"+beanName+"----执行postProcessAfterInitialization方法");

		return bean;
	}

	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
		System.out.println("beanName:"+beanName+"----执行postProcessProperties方法");

		return pvs;
	}
}
