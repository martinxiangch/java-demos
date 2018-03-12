package com.martin.Bean;

import com.martin.Sdemo.ShiroDemoApplication;

public class Student implements IPersonBean {

    @Override
    public void ShowName() {
        // TODO Auto-generated method stub
        ShiroDemoApplication.Log("This is a student!");
    }

}
