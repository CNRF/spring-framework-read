package com.king.entity;

import lombok.Data;

/**
 * @author wmx
 * @version 1.0
 * @date 2020/12/10 15:13
 */
@Data
public class Person {
	private String name;
	private int age;

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}


}
