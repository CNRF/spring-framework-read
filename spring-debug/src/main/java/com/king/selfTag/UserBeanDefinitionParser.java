package com.king.selfTag;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author wmx
 * @version 1.0
 * @ClassName UserBeanDefinitionPaser
 * @Description
 * @date 2021/9/7 16:57
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
	/**
	 *  返回属性值对应的对象
	 * @param element the {@code Element} that is being parsed
	 * @return
	 */
	@Override
	protected Class<?> getBeanClass(Element element) {
		return User.class;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder builder) {
		// 获取标签具体的属性值
		String userName = element.getAttribute("userName");
		String email = element.getAttribute("email");
		String password = element.getAttribute("password");

		if(StringUtils.hasText(userName)){
			builder.addPropertyValue("username",userName);
		}
		if (StringUtils.hasText(email)){
			builder.addPropertyValue("email",email);
		}
		if (StringUtils.hasText(password)){
			builder.addPropertyValue("password",password);
		}
	}
}
