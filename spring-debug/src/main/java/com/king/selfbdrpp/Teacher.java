package com.king.selfbdrpp;

/**
 * @author wmx
 * @version 1.0
 * @date 2021/1/6 15:00
 * @Description
 */
public class Teacher {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Teacher(String name) {
		this.name = name;
	}

	public Teacher() {
		System.out.println("创建Teacher对象");
	}
}
