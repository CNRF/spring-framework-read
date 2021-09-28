package com.king.createBean.FactoryBean;

import com.king.createBean.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author wmx
 * @version 1.0
 * @ClassName MyFactoryBean
 * @Description 通过FactoryBean在spring中创建Bean对象
 * @date 2021/9/28 9:55
 */
public class MyFactoryBean implements FactoryBean<User> {
	@Override
	public User getObject() throws Exception {
		return new User("FactoryBean创建对象");
	}

	@Override
	public Class<?> getObjectType() {
		return User.class;
	}

	@Override
	public boolean isSingleton() {
		//控制是否是单例
		return false;
	}
}
