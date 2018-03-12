package com.martin.shiroTest3;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RestConfig extends ResourceConfig {
 public RestConfig() {
     register(ShiroRestController.class);
 }
 
 
 @Bean
 public Realm realm() {
     // use define account
     SimpleAccountRealm realm=new SimpleAccountRealm();
     realm.addAccount("martin", "123456", "admin","officer");
     realm.addAccount("admin", "123456", "admin1","officer1");
     return realm;
 }

 @Bean
 public ShiroFilterChainDefinition shiroFilterChainDefinition() {
     DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
     // use permissive to NOT require authentication, our controller Annotations will decide that
     chainDefinition.addPathDefinition("/**", "authcBasic");
     return chainDefinition;
 }
}
