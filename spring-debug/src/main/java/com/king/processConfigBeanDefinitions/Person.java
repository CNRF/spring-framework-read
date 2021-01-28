package com.king.processConfigBeanDefinitions;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author wmx
 * @version 1.0
 * @date 2021/1/27 14:13
 * @Description
 */
@Component
public class Person {
	private String name;
	private String address;
	public Person() {
	}

	public Person(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
