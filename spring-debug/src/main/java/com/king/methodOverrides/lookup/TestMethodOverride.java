package com.king.methodOverrides.lookup;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @ClassName TestMethodOverride
 * @Description lookup-method 标签验证
 * @date 2021/9/28 10:38
 * spring中默认的对象都是单例的，spring会在一级缓存中持有该对象，方便下次直接获取，
 * 那么如果是原型作用域的话，会创建一个新的对象
 * 如果想在一个单例模式的bean下引用一个原型模式的bean,怎么办？
 * 在此时就需要引用lookup-method标签来解决此问题
 * <p>
 * 通过拦截器的方式每次需要的时候都去创建最新的对象，而不会把原型对象缓存起来，
 */
public class TestMethodOverride {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/king/methodOverrides/methodOverride.xml");
		//验证单例模式引用原型模式下中原型模式的Bean是否是同一个
		Apple bean = ac.getBean(Apple.class);
		System.out.println(bean.getBanana());
		Apple bean2 = ac.getBean(Apple.class);
		System.out.println(bean2.getBanana());


		FruitPlate fruitplate1 = (FruitPlate) ac.getBean("fruitplate1");
		fruitplate1.getFruit();
		FruitPlate fruitplate2 = (FruitPlate) ac.getBean("fruitplate1");
		fruitplate2.getFruit();
		FruitPlate fruitplate3 = (FruitPlate) ac.getBean("fruitplate2");
		fruitplate3.getFruit();
		FruitPlate fruitplate4 = (FruitPlate) ac.getBean("fruitplate2");
		fruitplate4.getFruit();
	}
}
