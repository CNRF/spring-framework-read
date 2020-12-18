package com.king.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author wmx
 * @version 1.0
 * @date 2020/12/10 17:05
 */
@Configuration
@ComponentScan("com.king")
public class MyConfig {
	@Bean
	public void Person(){
	}
}
