package com.martin.cms;

import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//implements CommandLineRunner
@SpringBootApplication
public class ShiroTest2Application  {

	public static void main(String[] args) {
		SpringApplication.run(ShiroTest2Application.class, args);
	}

   /* @Override
    public void run(String... arg0) throws Exception {
        @SuppressWarnings("deprecation")
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        
        String result="succed!";
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("Martin1", "123456");
        try {
            subject.login(token);
            
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String sStackTrace = sw.toString();
            
            result="Error:"+ e.getMessage()+";"+sStackTrace;
        }
        System.out.println(result);
    }*/
	

/*	@Bean
    public SecurityManager getSecurityManager() {
       @SuppressWarnings("deprecation")
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        
        
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        SecurityManager securityManager = new DefaultSecurityManager(iniRealm);
        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }*/
}
