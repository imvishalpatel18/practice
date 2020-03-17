package com.operr.todo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

	private static ApplicationContext context;

	public static ApplicationContext getApplicationContext() {
		return context;
	}

	public static void setStaticApplicationContext(ApplicationContext ac) throws BeansException {
		context = ac;
	}

	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		setStaticApplicationContext(ac);
	}
}