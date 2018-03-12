package com.martin.springbootDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DemoService.class)
@ConditionalOnClass(DemoService.class)
@ConditionalOnProperty(prefix="demo",value="enable",matchIfMissing=true)
public class DemoServiceAutoConfiguration {
	@Autowired
	private DemoServiceProperties demoProperties;
	
	public DemoService demoService() {
		DemoService demo=new DemoService();
		demo.setDemo(demoProperties.getDemo());
		return demo;
	}
}
