package com.martin.Bean;

import com.martin.Sdemo.ShiroDemoApplication;

public class Teacher implements IPersonBean {

    @Override
    public void ShowName() {
        // TODO Auto-generated method stub
        ShiroDemoApplication.Log("This is a teacher!");
    }

}



