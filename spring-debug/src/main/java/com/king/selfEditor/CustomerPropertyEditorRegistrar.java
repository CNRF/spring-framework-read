package com.king.selfEditor;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * @author wmx
 * @version 1.0
 * @date 2020/12/31 10:59
 * @Description 注册自定义的解析器，需要继承PropertyEditorRegistrar解析器
 */
public class CustomerPropertyEditorRegistrar implements PropertyEditorRegistrar {
	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		//注册自定义的解析器,Address指定解析器
		registry.registerCustomEditor(Address.class,new AddressPropertyEditor());
	}
}
