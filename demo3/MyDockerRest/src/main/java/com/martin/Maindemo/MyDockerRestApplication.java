package com.martin.Maindemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MyDockerRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyDockerRestApplication.class, args);
	}
	
	@RequestMapping(path="/hello",method=RequestMethod.GET)
	public String hello() {
        return "This is a martin demo!";
    }
}
