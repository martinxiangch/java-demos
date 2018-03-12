package com.martin.SimpleBean;

import org.springframework.stereotype.Repository;

import com.martin.Sdemo.ShiroDemoApplication;

@Repository
public class SPerson {
    
    public void getName() {
        ShiroDemoApplication.Log("This is a simple bean");
    }
}
