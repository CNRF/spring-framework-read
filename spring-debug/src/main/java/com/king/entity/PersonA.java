package com.king.entity;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author wmx
 * @version 1.0
 * @date 2020/12/10 15:13
 */

public class PersonA implements ApplicationContextAware {
	private String name;
	private int age;
	private ApplicationContext applicationContext;

	public PersonA() {
	}

	public PersonA(String name, int age, ApplicationContext applicationContext) {
		this.name = name;
		this.age = age;
		this.applicationContext = applicationContext;
	}

	public PersonA(String name, int age) {
		this.name = name;
		this.age = age;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
