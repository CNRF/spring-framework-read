package com.king.selfEditor;

import java.beans.PropertyEditorSupport;

/**
 * @author wmx
 * @version 1.0
 * @date 2020/12/25 16:30
 * @Description
 */
public class AddressPropertyEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String[] s = text.split("_");
		Address address = new Address();
		address.setProvinces(s[0]);
		address.setCity(s[1]);
		address.setTown(s[2]);
		setValue(address);
	}
}
