package com.king.processConfigBeanDefinitions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wmx
 * @version 1.0
 * @date 2021/1/27 14:16
 * @Description
 */
@Configuration
public class PersonService {
	@Bean
	public String Show(){
		System.out.println("测试@Bean");
		return "";
	}
}
