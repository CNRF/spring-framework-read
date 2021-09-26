package com.king.converter;

import org.springframework.core.convert.converter.Converter;

/**
 * @author wmx
 * @version 1.0
 * @ClassName StudentConverter
 * @Description
 * @date 2021/9/23 16:19
 */
public class StudentConverter implements Converter<String,Student> {
	@Override
	public Student convert(String source) {
		Student student = new Student();
		String[] split = source.split(",");
		student.setId(Integer.valueOf(split[0]));
		student.setName(split[1]);
		return student;
	}
}
