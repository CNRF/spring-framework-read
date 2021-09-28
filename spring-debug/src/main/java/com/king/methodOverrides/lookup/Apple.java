package com.king.methodOverrides.lookup;

/**
 * @author wmx
 * @version 1.0
 * @ClassName Apple
 * @Description
 * @date 2021/9/28 10:29
 */
public class Apple  extends Fruit{

	private Banana banana;

	public Apple() {
		System.out.println("I got a fresh apple");
	}

	public Banana getBanana() {
		return banana;
	}

	public void setBanana(Banana banana) {
		this.banana = banana;
	}
}
