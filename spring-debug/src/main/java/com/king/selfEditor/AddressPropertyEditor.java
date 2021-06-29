package com.king.selfEditor;

import org.springframework.util.Assert;

import java.beans.PropertyEditorSupport;

/**
 * @author wmx
 * @version 1.0
 * @date 2020/12/25 16:30
 * @Description 自定义解析器，设置解析规则
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
