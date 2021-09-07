package com.king.selfTag;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author wmx
 * @version 1.0
 * @ClassName UserNamespaceHandler
 * @Description
 * @date 2021/9/7 17:01
 */
public class UserNamespaceHandler extends NamespaceHandlerSupport {
	@Override
	public void init() {
		registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
	}
}
