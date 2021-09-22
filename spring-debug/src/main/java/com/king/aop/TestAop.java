package com.king.aop;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author wmx
 * @version 1.0
 * @ClassName TestAop
 * @Description  Aop测试代码
 * @date 2021/9/6 14:12
 */
public class TestAop {

	public static void main(String[] args) throws Exception {
		saveGeneratedCGlibProxyFiles(System.getProperty("user.dir")+"/spring-debug/src/main/java/com/king/aop/proxy");
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/king/aop/aop.xml");
		MyCalculator bean = ac.getBean(MyCalculator.class);
		bean.add(1,1);
		bean.sub(1,1);
	}

	public static void saveGeneratedCGlibProxyFiles(String dir) throws Exception {
		Field field = System.class.getDeclaredField("props");
		field.setAccessible(true);
		Properties props = (Properties) field.get(null);
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, dir);//dir为保存文件路径
		props.put("net.sf.cglib.core.DebuggingClassWriter.traceEnabled", "true");
	}
}
