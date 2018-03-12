package com.martin.Sdemo;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
import org.springframework.stereotype.Component;


public class UserRealm implements Realm{

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        
        String username=(String)token.getPrincipal();
        String pass=new String((char[]) token.getCredentials());
        if(!"martin".equals(username) || !"123".equals(pass)) {
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(username,pass,getName());
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "myrealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        // TODO Auto-generated method stub
        return token instanceof UsernamePasswordToken;
    }
   
   
}
