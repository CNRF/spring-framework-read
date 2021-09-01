package com.king.aware;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * @author wmx
 * @version 1.0
 * @ClassName EnvironmentAwareImpl
 * @Description
 * @date 2021/8/24 17:53
 */
public class EnvironmentAwareImpl implements EnvironmentAware {
	/**
	 * Set the {@code Environment} that this component runs in.
	 * {@link org.springframework.context.support.AbstractApplicationContext#prepareBeanFactory}
	 * @param environment
	 */
	@Override
	public void setEnvironment(Environment environment) {
		String[] defaultProfiles = environment.getDefaultProfiles();
		System.out.println(defaultProfiles);
	}
}
