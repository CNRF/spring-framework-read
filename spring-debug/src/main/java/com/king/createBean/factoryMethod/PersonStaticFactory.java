package com.king.createBean.factoryMethod;

/**
 * @author wmx
 * @version 1.0
 * @ClassName PersonStaticFactory
 * @Description 静态工厂构建bean
 * @date 2021/9/28 9:25
 */
public class PersonStaticFactory {
	public  static  Person getPerson(String name){
		Person person = new Person();
		person.setId(1);
		person.setName(name);
		return person;
	}
	public static Person getPerson(int age){
		return new Person();
	}

	public static Person getPerson(String name,int id){
		Person person = new Person();
		person.setId(1);
		person.setName(name);
		return person;
	}
}
