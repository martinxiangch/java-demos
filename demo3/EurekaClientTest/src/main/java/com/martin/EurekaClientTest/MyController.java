package com.martin.EurekaClientTest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/hello")
    public String Hello() {
        return "hello world!";
    }
}
