package com.king.converter;

/**
 * @author wmx
 * @version 1.0
 * @ClassName Studetn
 * @Description
 * @date 2021/9/23 16:18
 */
public class Student {
	private Integer id;
	private String name;

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
