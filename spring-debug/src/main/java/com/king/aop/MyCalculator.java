package com.king.aop;

/**
 * @author wmx
 * @version 1.0
 * @ClassName MyCalculator
 * @Description
 * @date 2021/9/13 9:29
 */
public class MyCalculator{

	public Integer add(Integer i, Integer j) throws NoSuchMethodException {
		Integer result = i+j;
		return result;
	}


	public Integer sub(Integer i, Integer j) throws NoSuchMethodException {
		Integer result = i-j;
		return result;
	}


	public Integer mul(Integer i, Integer j) throws NoSuchMethodException {
		Integer result = i*j;
		return result;
	}

	public Integer div(Integer i, Integer j) throws NoSuchMethodException {
		Integer result = i/j;
		return result;
	}
	public Integer show(Integer i){
		System.out.println("show .....");
		return i;
	}
}
