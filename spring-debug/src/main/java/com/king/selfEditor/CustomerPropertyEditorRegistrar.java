package com.king.selfEditor;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * @author wmx
 * @version 1.0
 * @date 2020/12/31 10:59
 * @Description
 */
public class CustomerPropertyEditorRegistrar implements PropertyEditorRegistrar {
	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		registry.registerCustomEditor(Address.class,new AddressPropertyEditor());
	}
}
