package com.ftsafe.iccd.bean;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	private static AbstractApplicationContext context;

	public static void main(String[] args) {

		context = new ClassPathXmlApplicationContext(new String[] {"Beans.xml"});
		Foo foo = (Foo) context.getBean("foo");
		Client client;
		for (int i = 0; i < 5; i++) {
			client = (Client) context.getBean(Client.class);
			String string = new String(i+"");
			client.setName(string);
			foo.setClient(client);
			System.out.println(foo.getClient().getName());
		}
		System.out.println(context.getBeanDefinitionCount());
//		context.registerShutdownHook();
		context.close();
	}

}
