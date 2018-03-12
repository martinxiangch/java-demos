package com.martin.EurekaServerTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerTest2Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerTest2Application.class, args);
	}
}
