package com.king.Test;

import com.king.MyClassPathXmlApplicationContext;
import com.king.config.MyConfig;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @date 2020/12/16 11:06
 * @Description
 */
public class Test03 {
	public static void main(String[] args) {

		ClassPathXmlApplicationContext applicationContext = new MyClassPathXmlApplicationContext("bean.xml");
	}
}
