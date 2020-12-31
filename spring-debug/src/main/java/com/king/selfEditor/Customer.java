package com.king.selfEditor;

/**
 * @author wmx
 * @version 1.0
 * @date 2020/12/25 16:28
 * @Description
 */
public class Customer {
	private String name;
	private Address address;

	public Customer() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Customer(String name, Address address) {
		this.name = name;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"name='" + name + '\'' +
				", address=" + address +
				'}';
	}
}
