package com.king.circularDependency;

/**
 * @author wmx
 * @version 1.0
 * @ClassName B
 * @Description
 * @date 2021/9/3 9:20
 */
public class B {
	private A  a;

	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}

}
