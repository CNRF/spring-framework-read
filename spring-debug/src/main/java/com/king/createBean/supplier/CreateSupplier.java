package com.king.createBean.supplier;

import com.king.createBean.User;

/**
 * @author wmx
 * @version 1.0
 * @ClassName CreateSupplier
 * @Description
 * @date 2021/9/28 9:38
 */
public class CreateSupplier {
	public static User createUser(){
		return new User("Supplier");
	}
}
