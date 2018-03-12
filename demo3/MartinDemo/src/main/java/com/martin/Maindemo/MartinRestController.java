package com.martin.Maindemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MartinRestController {

    @RequestMapping(path="/hello",method=RequestMethod.GET)
    public String Hello() {
        return "This is a martin demo 11111";
    }
}
