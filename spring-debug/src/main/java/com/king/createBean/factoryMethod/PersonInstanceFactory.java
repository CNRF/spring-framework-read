package com.king.createBean.factoryMethod;

/**
 * @author wmx
 * @version 1.0
 * @ClassName PersonInstanceFactory
 * @Description 构造方法生成
 * @date 2021/9/28 9:23
 */
public class PersonInstanceFactory {
	public Person getPerson(String name){
		Person person = new Person();
		person.setId(1);
		person.setName(name);
		return person;
	}
}
