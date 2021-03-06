package com.king.transaction.annotation;

import com.king.transaction.annotation.config.TransactionConfig;
import com.king.transaction.annotation.dao.BookDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TransactionTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(TransactionConfig.class);
		applicationContext.refresh();
//        BookService bean = applicationContext.getBean(BookService.class);
//        bean.checkout("zhangsan",1);
		BookDao bean = applicationContext.getBean(BookDao.class);
		bean.test();
	}
}
