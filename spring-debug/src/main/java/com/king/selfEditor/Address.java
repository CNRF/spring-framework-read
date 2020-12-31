package com.king.selfEditor;

/**
 * @author wmx
 * @version 1.0
 * @date 2020/12/31 11:12
 * @Description
 */
public class Address {
	private String provinces;
	private String city;
	private String town;

	public Address() {
	}

	public String getProvinces() {
		return provinces;
	}

	public void setProvinces(String provinces) {
		this.provinces = provinces;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public Address(String provinces, String city, String town) {
		this.provinces = provinces;
		this.city = city;
		this.town = town;
	}

	@Override
	public String toString() {
		return "Address{" +
				"provinces='" + provinces + '\'' +
				", city='" + city + '\'' +
				", town='" + town + '\'' +
				'}';
	}
}
