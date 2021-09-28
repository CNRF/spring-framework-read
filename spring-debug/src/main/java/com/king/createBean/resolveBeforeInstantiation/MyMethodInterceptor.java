package com.king.createBean.resolveBeforeInstantiation;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wmx
 * @version 1.0
 * @ClassName MyMethodInterceptor
 * @Description
 * @date 2021/9/28 10:23
 */
public class MyMethodInterceptor implements MethodInterceptor {

	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("目标方法执行之前：" + method);
		Object o1 = methodProxy.invokeSuper(o, objects);
		System.out.println("目标方法执行之后：" + method);
		return o1;
	}
}
