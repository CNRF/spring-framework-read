package com.king.testService;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wmx
 * @version 1.0
 * @ClassName TestService
 * @Description
 * @date 2021/6/29 16:43
 */
public class TestService {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(MyConfig.class);
		acac.getBean(MyService.class).TestService();
	}
}
