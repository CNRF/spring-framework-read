package com.king.converter;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.convert.ConversionService;

/**
 * @author wmx
 * @version 1.0
 * @ClassName TestConverter
 * @Description 测试实现自定义的Bean的转换规则
 * @date 2021/9/23 16:27
 */

public class TestConverter {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("com/king/converter/converter.xml");
		ConversionService bean = ac.getBean(ConversionService.class);
		Student convert = bean.convert("11,张三", Student.class);
		System.out.println(convert);
	}
}
