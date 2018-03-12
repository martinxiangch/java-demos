package com.martin.Repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

//@Repository
@Service
//@ConfigurationProperties(prefix="com.martin")
public class Person {
 
 //   @Value("${name:demo}")
    private String Name="aaa";
    
    public void setName(String name) {
        this.Name=name;
    }
    
    public String getName() {
        return Name;
    }
    
}
