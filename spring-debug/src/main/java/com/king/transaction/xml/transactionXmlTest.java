package com.king.transaction.xml;

import com.king.transaction.xml.service.BookService;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author wmx
 * @version 1.0
 * @ClassName transactionXmlTest
 * @Description
 * @date 2021/10/14 17:10
 */
public class transactionXmlTest {
	public static void main(String[] args) throws Exception {
		saveGeneratedCGlibProxyFiles(System.getProperty("user.dir") + "/spring-debug/src/main/java/com/king/transaction/proxy");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/king/transaction/transaction.xml");
		BookService bookService = context.getBean("bookService", BookService.class);
		bookService.checkout("zhangsan", 1);
	}

	public static void saveGeneratedCGlibProxyFiles(String dir) throws Exception {
		Field field = System.class.getDeclaredField("props");
		field.setAccessible(true);
		Properties props = (Properties) field.get(null);
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, dir);//dir为保存文件路径
		props.put("net.sf.cglib.core.DebuggingClassWriter.traceEnabled", "true");
	}
}
