package com.king.methodOverrides.replace;

/**
 * @author wmx
 * @version 1.0
 * @ClassName OriginalDog
 * @Description
 * @date 2021/9/28 10:49
 */
public class OriginalDog {
	public void sayHello() {
		System.out.println("Hello,I am a black dog...");
	}

	public void sayHello(String name) {
		System.out.println("Hello,I am a black dog, my name is " + name);
	}
}
