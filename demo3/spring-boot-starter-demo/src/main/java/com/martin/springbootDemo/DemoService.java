package com.martin.springbootDemo;

public class DemoService {
	private String msg;
	
	public String sayDemo() {
		return "This is a martin demo:"+msg;
	}
	
	public void setDemo(String demo) {
		this.msg=demo;
	}
}
