package com.king.circularDependency;

/**
 * @author wmx
 * @version 1.0
 * @ClassName a
 * @Description
 * @date 2021/9/3 9:20
 */
public class A {
	private B b;
	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}

	@Override
	public String toString() {
		return "A{" +
				"b=" + b +
				'}';
	}
}
