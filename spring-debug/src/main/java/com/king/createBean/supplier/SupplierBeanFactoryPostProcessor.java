package com.king.createBean.supplier;

import com.king.createBean.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @author wmx
 * @version 1.0
 * @ClassName SupplierBeanFactoryPostProcessor
 * @Description
 * @date 2021/9/28 9:40
 */
public class SupplierBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		GenericBeanDefinition user = (GenericBeanDefinition)beanFactory.getBeanDefinition("user");
		user.setInstanceSupplier(CreateSupplier::createUser);
		user.setBeanClass(User.class);
	}
}
