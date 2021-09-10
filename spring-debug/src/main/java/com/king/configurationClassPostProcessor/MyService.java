package com.king.configurationClassPostProcessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wmx
 * @version 1.0
 * @ClassName MyService
 * @Description
 * @date 2021/9/9 14:28
 */
@Configuration
public class MyService {
	@Bean
	public String Show(){
		return "show";
	}
}
