package com.martin.EurekaConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class MyController {
    private static final Logger log=LoggerFactory.getLogger(MyController.class);
   
    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("/hello")
    @HystrixCommand(fallbackMethod="helloFallback",commandProperties= {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000"),
            @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds",value="10000")
    }, threadPoolProperties= {
            @HystrixProperty(name="coreSize",value="1"),
            @HystrixProperty(name="maxQueueSize",value="10")
    })
    public String findById() {
        log.info("begin to get remote object!");
        return this.restTemplate.getForObject("http://localhost:8080/hello", String.class);
        
    }
    
    public String helloFallback() {
        log.info("begin to fallback");
        return "this is fall back message!";
    }
}
