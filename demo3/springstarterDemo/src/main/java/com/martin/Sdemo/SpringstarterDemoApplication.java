package com.martin.Sdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

import com.martin.springbootDemo.DemoService;

@SpringBootApplication
public class SpringstarterDemoApplication {

	@Autowired
	DemoService demoservice;
	
	@RequestMapping("/")
	public String index() {
		return demoservice.sayDemo();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringstarterDemoApplication.class, args);
	}
}
