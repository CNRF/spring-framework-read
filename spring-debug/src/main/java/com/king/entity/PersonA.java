package com.king.entity;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author wmx
 * @version 1.0
 * @date 2020/12/10 15:13
 */
@Data
public class PersonA implements ApplicationContextAware {
	private String name;
	private int age;
	private ApplicationContext applicationContext;
	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}
}
