package com.ftsafe.iccd.bean;

public class Foo {

	private String message;
	private Client client;
	
	private Foo() {}
	public void init() {
		System.out.println("hi");
	}
	public static Foo createInstance() {
		return new Foo();
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void destroy() {
		System.out.println("bye from Foo");
	}
}
