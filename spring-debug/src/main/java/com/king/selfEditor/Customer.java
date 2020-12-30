package com.king.selfEditor;

/**
 * @author wmx
 * @version 1.0
 * @date 2020/12/25 16:28
 * @Description
 */
public class Customer {
	private String name;
	private int age;

	public Customer(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Customer() {
	}

	@Override
	public String toString() {
		return "Custom{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
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
}
