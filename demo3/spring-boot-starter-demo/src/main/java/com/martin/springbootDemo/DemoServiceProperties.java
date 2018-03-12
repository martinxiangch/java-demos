package com.martin.springbootDemo;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="demo")
public class DemoServiceProperties {
	private static final String MSG="World";
	private String msg=MSG;
	public String getDemo() {
		return msg;
	}
	
	public void setDemo(String demo) {
		this.msg=demo;
	}
}
